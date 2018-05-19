# Concurrency-Thread-Program

Details
2 managers of a company employ 4 Secretaries who are of varying ability,
but who all work extremely fast (i.e. the Secretaries).
Secretary A is the most experienced and is capable of typing up a letter
in 1 second.
Secretary B is less experienced and is capable of typing up a letter in 2
seconds.
Secretaries C and D are junior Secretaries and capable of typing up a
letter in 6 seconds.
Re
ecting these dierences, in a single working day Secretary A will type
up 12 letters, Secretary B will type 8 letters while both Secretary C and
Secretary D will be responsible for completing 4 letters each: a total of 28
letters in all.
When a Secretary has typed up a letter it is placed in a letter tray
pending its removal and signing. Either of the two managers may take
letters to sign.
The tray can hold a maximum of 5 letters at a time. The tray's limited
capacity sometimes causes the various workers to be delayed. For example,
if the tray is full after a letter has been typed, the Secretaries must wait
until a manager makes a space available before they can add another letter.
Similarly, the managers must wait for at least one letter to appear in the
tray before taking it out to sign.
A further, potential, source of delay arises since, in keeping with contem-
porary workplace practice, the physical and intellectual strain of appending
a mark at the end of a letter results in each manager having to take a break
every 7 letters in order to recuperate, regardless of whether the letter tray
contains correspondence or otherwise.
1
To facilitate such recovery the company has provided (for the sole use of
managerial employees) a luxiouriously appointed Executive Lounge, but in
recognition of the fact that the overall environment is one in which work is
meant to be carried out, at most one manager may occupy this at a time.
If the other nds its use essential then they must wait until it is free. Of
course, while one manager is waiting to use the lounge and the other is
occupying it, it is still the case that no productive work is being carried out
on the part of either: pending letters will be not be removed from the tray.
Just as the Secretarial pool covers a range of dierent experience, so too,
the managers are dierentiated: in the amount of time taken to dispatch a
letter, the amount of time needed to recover from their eorts, and in the
total number of letters each is expected to deal with in a working day. Thus,
the junior manager A needs 2 seconds to add their mark to a letter and is
allowed 20 seconds in the Lounge to recover. This manager is expected to
deal with 19 letters in a day. In contrast, the senior manager B requires
4 seconds to dispatch a letter and (re
ecting seniority) may spend up to
60 seconds recuperating in the Lounge. This manager is only expected to
deal with 9 letters.1
In your program, use six threads to represent the four Workers and
two managers, so that they can work in parallel.
You will also need to declare a tray object that will be accessed by all
six threads: the producers (Secretaries) and consumers (managers). In ad-
dition a Lounge object accessed only by manager threads is needed. It must
be ensured that all communication is properly synchronised to avoid inde-
terminacy and deadlock. In particular, implemented in a such a way that:
a. A Secretary cannnot add a letter to the Tray while it contains 5 let-
ters.
b. A manager cannot remove a letter from the Tray while it contains 0
letters, i.e it is empty.
c. A manager cannot enter the Executive Lounge while the other man-
ager is using it.
While a Secretary is busy typing a letter the associated thread should
be suspended for the relevant time. Similarly while a manager is appending
their monogram to the letter (i.e \signing" it) or recuperating in the lounge
the relevant thread should be sent to sleep for the required time period. All
of these can be achieved with a call to:
Thread.sleep(m);
where m is the number of milliseconds for which the thread should sus-
pend.
