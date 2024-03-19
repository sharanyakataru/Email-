# Email System
The objective of this project is to create a program that controls an email system (much like a simplified version of Outlook), with the obvious exception that emails cannot be sent or received. However, this application will include all of the typical email functions, including the ability to add and remove emails as well as delete, create, and move emails to other folders. 

1. Email class:

private String to
stores the “to” field.
private String cc
stores the “cc” field.
private String bcc
stores the “bcc” field.
private String subject
stores the “subject” field.
private String body
stores all of the text in the email’s body.
private GregorianCalendar timestamp
time that this email was created.
In order to be able to save Email objects to a file, all objects in it must be serializeable, so this class must implement Serializable. 

2. Write a fully documented class named Folder. This class represents an email folder and will handle all of the logic for adding and removing emails.
It also has the following methods (including the usual constructor and accessor/mutators methods):

·         public void addEmail(Email email)

·         public Email removeEmail(int index)

·         public void sortBySubjectAscending()

·         public void sortBySubjectDescending()

·         public void sortByDateAscending()

·         public void sortByDateDescending()

3. Write a fully documented class named Mailbox. This class represents an email box and it will contain all of the folders and the remaining logic. 

4. When the program begins, it should see if the file "mailbox.obj" exists in the current directory. If so, it should initialize the mailbox with the data in this file using serialization. Otherwise, your program will start with an empty mailbox. Then, the program should provide the user with a menu with the following options.

A – Add folder

R – Remove folder

C – Compose email

F – View Folder

I – View Inbox

T – View Trash

E – Empty Trash

Q - Quit the program

Remember to save the contents of the mailbox to the file, "mailbox.obj".

There is no option to load or save a file in the menu. This is because you should automatically search for a file named "mailbox.obj" upon startup, and save to the file when the user wishes to quit the program.

Folder submenu:

M – Move email

D – Delete email

V – View email contents

SA – Sort by subject ascending

SD – Sort by subject descending

DA – Sort by date ascending

DD – Sort by date descending

R – Return to main menu

5. Include exception classes or additional classes as needed. 

