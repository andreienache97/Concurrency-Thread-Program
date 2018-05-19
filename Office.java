// Name: Andrei-Ionut Enache

// Start the Office class

public class Office {	
  // ***************************************
  // Main method          
  // **************************************
  public static void main(String args[]) {
    //************************************************
    // Create a new instance of Tray, 
    // Create a new instance of Lounge
    // 4 new instances of Secretary to be the producer threads and 
    // 2 new instances of Manager to be the consumer thread, 
    // all with appropriate working speeds         
    //************************************************
    Tray t = new Tray();
    Lounge L = new Lounge();
    Secretary Secretary1 = new Secretary(t, "Secretary A", 1,12);
    Secretary Secretary2 = new Secretary(t, "Secretary B", 2, 8);
    Secretary Secretary3 = new Secretary(t, "Secretary C", 6, 4);
    Secretary Secretary4 = new Secretary(t, "Secretary D", 6, 4);
    Manager ManagerA = new Manager(t, L, "Junior", 2, 20, 19);
    Manager ManagerB = new Manager(t, L, "Senior", 4, 60, 9);
    // Start the secretary and mangaer threads running by calling the start method on them all
    Secretary1.start();
    Secretary2.start();
    Secretary3.start();
    Secretary4.start();
    ManagerA.start();
    ManagerB.start();

    }
  }
 //************************************************
 // Declare the secretary class with tray, secretary names, 
 // time it takes to type a letter, number of letters typed and 
 // boolean field, called active, set to true whilst the number of letters typed 
 // hasn't reached the required amount, which is 7 letters for each secretary
 //************************************************
  class Secretary extends Thread {
         
    private Tray t;
    private String secretaryName;
    private int typingSpeed;                     
    private int numberProduced;
    private int numberRequired;
    private boolean active = true;
    //*****************************************************************
    // Constructor for Secretary object made up of tray, secretary name, 
    // time it takes that secretary to type a letter and number of letters produced
    // (initialised to 0)
    //*****************************************************************
    public Secretary(Tray t1, String name, int typingTime, int totalNeeded){
      t = t1;
      secretaryName = name;
      typingSpeed = typingTime;
      numberRequired = totalNeeded;
      numberProduced = 0;
      }
      //****************************************************************************
      // Method to run the secretary threads, looping until required number 
      // of letters have been produced. Puts thread to sleep for the required 
      // amount of time when it's busy doing its task. When the task has been 
      // done, increment the number of letters produced, print out that a letter 
      // has been typed and how many letters that secretary has now produced  
      //****************************************************************************
      public void run() {
        while (active) {
          try  {
            System.out.println(secretaryName + " is typing letter "+(numberProduced+1));
            sleep(typingSpeed*1000);
            }
           catch (InterruptedException e) { }
	   numberProduced++;
           System.out.println(secretaryName + " has typed a letter, number now typed = "  + (numberProduced));
	   // **********************************************************
	   // Method call to put the typed letter into the tray and 
	   // print a message to state this
           //**********************************************************
           t.put(secretaryName);
           //System.out.println(secretaryName + " has added the letter to the tray");
           //**********************************************************
	   // Conditional to check if the required number of letters has been
	   // produced, and if it has then stop the typing process by 
	   // calling the stopTyping method which sets the active field to
	   // false. If required number has not been produced then the 
	   // typing process will continue
           //**********************************************************
           if (numberProduced == numberRequired) {
             System.out.println(secretaryName + " has typed and filed" +
                  " all "+ numberRequired + " letters and has now finished work for today" );
             stopTyping();
             }
           }
         }                        
         public void stopTyping() {active = false; }
  }
//*********************************************************************
// Declare the Manager class with fields for the tray, the manager's
// speed, the number of letters signed and boolean field called active
// set to true  whilst number of letters signed hasn't reached the 
// required amount which is 28 letters
//*********************************************************************
  class Manager extends Thread {
    private Tray t;
    private Lounge L;
    private String MangrName;
    private int RecoveryTime;
    private int signingSpeed;
    private int numberSigned;
    private int TotalWorkLoad;
    private boolean active = true;
    //*********************************************************************
    // Constructor for manager object made up of tray, rest lounge, name, time it takes
    // to sign a letter, time it takes to recover from signing, and number of letters expected to sign
    //*********************************************************************
    public Manager(Tray t1, Lounge L1, String named, int signingTime, int downTime, int LettersToSign) {
      t = t1; L = L1; MangrName = named;
      RecoveryTime = downTime;
      signingSpeed = signingTime;
      numberSigned = 0;
      TotalWorkLoad = LettersToSign;
      
      }
      //*********************************************************************
      // Method to run the manager thread, looping until required number of
      // letters have have been signed, putting the thread to sleep for the
      // required amount of time when it's busy doing its task, then incrementing
      // the number of letters signed when a letter from the tray has been signed
      //*********************************************************************
      public void run() {
        while (active) {
        //*********************************************************************
	// Method call to get the letters typed out of the tray and 
        // print a message to state this, with the number now signed
        //*********************************************************************
        t.get(MangrName);
        try  {
           System.out.println(MangrName+" manager is signing letter "+(numberSigned+1));
           sleep(signingSpeed*1000);
           //System.out.println(MangrName+" manager has now signed = "+ (numberSigned+1)+" letters");
           }
           catch (InterruptedException e) {}
        numberSigned++;
        if (((numberSigned%7)==0) && (numberSigned<TotalWorkLoad))
          {
              System.out.println(MangrName+" manager has signed "+numberSigned+" letters and feels overworked "+
                                  " is going to Lounge to recover");
              L.Enter(MangrName);
              try {
              sleep(RecoveryTime*1000);
              } catch (InterruptedException e) {}
              L.Leave(MangrName);
          System.out.println(MangrName+" manager has returned from break");
          }
        //*********************************************************************
	// Conditional to check if the required number of letters have been 
	// signed, and if they have then stop the signing process, (and 
	// thus the whole process) by calling the stopSigning method 
	// which sets the active field to false. If required number has
	// not been signed then the signing process will continue
        //*********************************************************************
        if (numberSigned == TotalWorkLoad) {
          System.out.println(MangrName+" manager has removed and signed full quota of" +
            "letters and gone home (via pub)");
          stopSigning(); 
          }
        }
     }
     public void stopSigning() {active = false;}
  }
  //********************************************************************
  // Declare Tray class with fields for number of letters in the tray
  // and the maximum number of letters allowed in the tray at one time,
  // which is 5
  //********************************************************************
  class Tray {
    private int numOfLetters;
    private final int maxNumLetters = 5;
    // Constructor for tray object made up of number of letters 
    public Tray() {
      numOfLetters = 0; 
      }
      //********************************************************************
      // Synchronized method to put the typed letters into the tray
      // when there is enough room. The thread calling the method gains 
      // exclusive control of the lock as it's a synchronized method. 
      // If the tray is full then release the lock to allow the consumer 
      // thread to access the tray to sign and remove a letter
      //********************************************************************
      public synchronized void put(String SecretaryName) {
        while (numOfLetters == maxNumLetters) {
          System.out.println("Tray full. "+SecretaryName+" must wait "
             + "until a letter has been removed before adding another");
        try{
          wait();
          }
        catch (InterruptedException e) {};
        }   
        numOfLetters++;
        System.out.println("A letter has been added by " +SecretaryName+
                           " to the tray. Tray now contains " + numOfLetters + " letters");
        notifyAll();      
        }
        // **********************************************************************
	// Synchronized method to get the typed letters from the tray.  
	// The thread calling the method gains exclusive control of the
	// lock, as it's a synchronized method. If there aren't any letters
        // in the tray then the lock is released to allow a producer thread 
        // to access the tray to deposit a letter in it
        // **********************************************************************
        public synchronized void get(String ManagerName) {
          while (numOfLetters == 0) {
	    System.out.println("Tray empty. "+ManagerName+" manager must wait until a "
                  + "letter has been put in the tray before trying to sign one");
	    try{
              wait();
              }
	      catch (InterruptedException e) {};
            }   
          numOfLetters--;
          System.out.println("A letter has been removed" +
                            " from the tray by "+ManagerName+" manager. Tray now contains " + numOfLetters+" letters");
          notifyAll();    
    	  }
   }
  class Lounge {
    private boolean Occupied;
    public Lounge() {
      Occupied=false; 
      }
      //********************************************************************
      // Synchronized method to controlling access to Lounge: only one Manager at a time
      // can access Lounge.
      // The thread calling the method gains 
      // exclusive control of the lock as it's a synchronized method. 
      //********************************************************************
      public synchronized void Enter(String NameOf) {
        while (Occupied) {
          System.out.println("Lounge is occupied; "+NameOf+" manager must wait");
        try{
          wait();
          }
        catch (InterruptedException e) {};
        }   
        Occupied = true;
        System.out.println(NameOf+" manager is in Lounge recovering");
        notifyAll();      
        }
        // **********************************************************************
	// Synchronized method to signal the lock countrolling access to Lounge is released.
        // **********************************************************************
        public synchronized void Leave(String NameOf) {
          System.out.println(NameOf+" manager has left the lounge");
          Occupied=false;
          notifyAll();    
    	  }
    }
// }
	
     
// End of program
