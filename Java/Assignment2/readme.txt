Assignment 2
=========================
Michael Bagnato (3237663)
Cameron Myers  (3167356)

Classes
=========================
MessageBoardServer: Server program (Written by Cameron Myers and Michael Bagnato)
Assign2:  Interface (Written by Michael Bagnato)
MessageBoardClient: Client program (Written by Michael Bagnato)
LoginDialog: Login screen (Written by Cameron Myers)
SetMessageListener: Shows a when a label is clicked (Written by Michael Bagnato)
LoginListener: Action Listener (Written by Cameron Myers)
SetTopicListener: Recieves text from the JFrame (Written by Michael Bagnato)
TopicDialogListener: Displays topic creation dialog (Written by Cameron Myers)
RegisterListener: Action Listener (Written by Cameron Myers)
CloseListener: Closes the program (Written by Michael Bagnato)
ShowLogoutDialogListener: Displays logout dialog (Written by Cameron Myers)
TopicListener: Action Listener (Written by Cameron Myers)
ShowLoginDialogListener: Displays login dialog (Written by Cameron Myers and Michael Bagnato)
ShowRegisterDialogListener:  Displays registration dialog (Written by Cameron Myers)
MessageBoardFrame: Displays the GUI (Written by Cameron Myers and Michael Bagnato)
MessageSubjectPanel: Displays the subjects (Written by Michael Bagnato)
RegisterDialog: Registration screen (Written by Cameron Myers)
TopicDialog: Registration screen (Written by Cameron Myers)
TopicPanel: Displays a list of topics (Written by Michael Bagnato)

Database: The database is hosted on Michael Bagnato's SQLplus account at RMIT. 
	  Tables are: INTRODUCTION, MESSAGE, TOPIC, USERS.


Protocol
=========================
In order of execution:
	1) Server loads and checks for client.
	2) Client loads, looks for server, connects to it and loads the GUI frame.
	3) Server acknowledges client connections.
	4) Server connects to the database.
	5) Client,retrieves list of topics from the database via the server.
	6) Client and Server wait for user input. Either through registering, logging on or viewing what topics are currently available to view.

Most of the classes require the user to select or input information. As a result once started the order of execution changes depending on the user.
When information is required the program will follow the standard format of obtaining the information from the user, sending it to the server to verify with the database.
Once verified a 'successful' message should be displayed.
The Client and Server connect to each other utilising RMI in order to establish an ongoing connection. The server connects to an outside database that is hosted on the RMIT SQLplus
server. It does this through the OJDBC, establishing a connection to a specific host/connection allowing the program to modify the tables and data held in that database located on that server.

Tests
=========================
22/04/2011 - Basic Server and Client were tested.
29/04/2011 - GUI was written and implemented with non-functioning buttons successfully.
06/05/2011 - Connection to and from the Database was tested along with information retrieval.
13/05/2011 - Most of the GUI works, complete with welcome message.
20/05/2011 - Login successfully created a user and added them to the database, Users can login successfully, Clicking on a topic label will bring up the relevant information about the topic.

Bugs
=========================
- User can be created and input into the database as long as the name has not been used before. (usure as to why).
- Currently does not allow the creation of an administrator. All current users are registered as a 'User'.
- Live chat is currently not implemented.
- Due to unknown reasons Cameron Myers was unable to run the program on eclipse. This was remedied in the final week by bypassing eclipse altogether.
  As a result most of the code written by Cameron Myers was given to Michael Bagnato and modified.

Running
=========================
The program should run fine on Yallara. 
No database preperation is required as the information to connect to the database is supplied within the code.
rmiregistry will need to be running before attempting to connect the server with the client.
If it is being run through eclipse you will need to supply your own version of 'ojdbc14.jar'.
In order to run one argument is required, "rmi://" + args[0] + ":1099/MessageBoardServer"), localhost is recomended.

