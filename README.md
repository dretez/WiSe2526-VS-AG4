# Verteilte Systeme Praktikumsaufgabe 4
As part of this task, replication is to be added to the data store from task 1.
Communication between the client and server should continue to be encapsulated via remote procedure calls (RPC).
The task is to be completed in teams of two. For internship groups with an odd number of participants, there will be one team of three.

The datastore interface remains identical to task 2 with the following methods:
* void write(int index, String data)
* String read(int index) throws NoSuchElementException

It should be possible to start any number of datastore servers. A list of all available
servers is transferred to the client at startup.
“Write” calls should be executed by the client stub on all servers. The method
terminates as soon as all available servers have confirmed the process. A call to the “read”
method should be executed on one of the servers using the Round Robin Method.

* The programming language used can be freely selected. One option would be
  implementation in Java. The method signatures must be adapted to the
  programming language accordingly.
* Errors during transmission between the client and server must be encapsulated as exceptions (depending on the programming language).
  The client must be able to handle these exceptions and continue processing.
* The stubs should not be generated automatically, but implemented manually.
  This means that they would have to be adapted every time the interface is changed.
* Communication should run over the network. It must be possible to run client and
  server applications on different computers.
* Communication should be conducted using a standardized human-readable
  message format, such as JSON or XML.
* No external libraries may be used for implementation. In particular,
  no RPC libraries or ready-made protocols are permitted.
  Libraries may only be used for marshalling and unmarshalling.

1) Implement the datastore server and the client stub in the programming language of your choice.
2) Measure the duration of write operations depending on the number of replicas with 1 to 4 servers.
3) If any server fails, the client should be affected as little as possible.
   Test the error case. What impact does the failure of a server have on the duration
   of read and write accesses?
4) Adapt the description of distribution transparency from Task 1 to the extended
   implementation.

A report must be submitted in the form of a text document containing all relevant design
decisions (e.g., choice of programming language) and the results of the tasks. The
source code must be explainable at the time of submission. The implementation must be
executable on the pool computers, via the network if necessary.

**The assignment should be completed during the fourth internship session and presented at the end of the session.
The latest submission date is January 25, 2026, by email.** 
