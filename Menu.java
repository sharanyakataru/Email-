/**This is the Menu class which provides the user with a menu with options
 * @author sharanyakataru
 * email: sharanya.kataru@stonybrook.edu
 * SBU ID: 114850472
 * Recitation: R30 sec:06
 */
import java.io.*;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.LinkedList;
public class Menu {
    public static void main(String[] args) {
     Mailbox mailbox = loadMailbox();
     Folder folder = new Folder();
     Email email = new Email();
     GregorianCalendar timeStamp = new GregorianCalendar();


        System.out.println("Previous save not found, starting with an empty mailbox.");
        System.out.println("Mailbox:");
        System.out.println("--------");
        System.out.println("Inbox");
        System.out.println("Trash");

        boolean flag = true;


        while(flag){
            System.out.println("A – Add folder\n" +
                    "R – Remove folder\n" +
                    "C – Compose email\n" +
                    "F – Open folder\n" +
                    "I – Open Inbox\n" +
                    "T – Open Trash\n" +
                    "E - Empty Trash\n" +
                    "Q – Quit");
            Scanner input = new Scanner(System.in);
            System.out.println("Enter a user option: ");
            String letter = input.nextLine();
            letter = letter.toUpperCase();

            switch(letter){
                case "A":
                    try{
                        System.out.println("Enter folder name: ");
                        String folder_input = input.nextLine();
                        Folder folder_new = new Folder(folder_input);
                        mailbox.addFolder(folder_new);
                        displayMailbox(mailbox);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "R":
                    try{
                        System.out.println("Enter name of folder to remove: ");
                        String remove = input.nextLine();
                        mailbox.deleteFolder(remove);
                        System.out.println("Folder " + remove + " removed.");

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "C":
                    try{
                        System.out.println("Enter recipient (To): ");
                        String to = input.nextLine();
                        System.out.println("Enter carbon copy recipients (CC): ");
                        String cc = input.nextLine();
                        System.out.println("Enter blind carbon copy recipients (BCC): ");
                        String bcc = input.nextLine();
                        System.out.println("Enter subject line: ");
                        String subject = input.nextLine();
                        System.out.println("Enter body: ");
                        String body = input.nextLine();


                        Email newEmail = new Email(to,cc,bcc,subject,body, timeStamp);
                        mailbox.getInbox().addEmail(newEmail);
                        System.out.println("Email successfully added to Inbox.");

                        displayMailbox(mailbox);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "F":
                    try{
                        System.out.println("Enter folder that needs to be viewed: ");
                        String view = input.nextLine();
                        Folder selected = mailbox.getFolder(view);

                        if(selected != null){
                            System.out.println(selected.getName() + ":");
                            viewFolder(selected);
                        }else{
                            System.out.println("Folder not found");
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "I":
                    try{
                        Folder inbox = mailbox.getInbox();
                        System.out.println("Inbox");

                        if(inbox != null){
                            System.out.println("Index | Time             | Subject");
                            System.out.println("-----------------------------------");
                            LinkedList<Email> emails = inbox.getEmails();
                            for (int i = 0; i < emails.size(); i++) {
                               email = emails.get(i);
                               System.out.printf("%5d | %s | %s%n", i + 1, email.getTimeStampFormat(), email.getSubject());
                            }
                        }else{
                            System.out.println("Inbox is empty.");
                        }
                        viewFolder(inbox);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "T":
                    try {
                        System.out.println("Trash: ");
                        viewFolder(mailbox.getTrash());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "E":
                    try{
                        mailbox.clearTrash();
                        System.out.println("Trash folder is now empty.");

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "Q":
                    try{
                        flag = false;
                        saveMailbox(mailbox);
                        System.out.println("Program successfully exited and mailbox saved.");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }


            }

        }



    }

    public static void viewFolder(Folder folder) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("M - Move email\n" +
                    "D - Delete email\n" +
                    "V - View email contents\n" +
                    "SA -Sort by subject ascending order\n" +
                    "SD - Sort by subject descending order\n" +
                    "DA - Sort by date ascending\n" +
                    "DD - Sort by Date Descending\n" +
                    "R - Return to main menu");
            System.out.println("Choose an option: ");
            String option = input.nextLine();
            option = option.toUpperCase();

            switch (option) {

                case "M":
                    try {
                        System.out.print("Enter the index of the email to move: ");
                        int index = Integer.parseInt(input.nextLine());

                        if (index >= 1 && index <= folder.getEmails().size()) {
                            Email move = folder.getEmails().get(index - 1);
                            String emailSubject = move.getSubject();

                            System.out.println("Select a folder to move '" + emailSubject + "' to: ");
                            String moving = input.nextLine();

                            Mailbox mailbox = new Mailbox();
                            Folder target = mailbox.getFolder(moving);
                            if (target == null) {
                                System.out.println("Invalid target folder");
                            } else {
                                mailbox.moveEmail(move, target);
                                System.out.println("Email moved to " + target.getName());
                            }
                        } else {
                            System.out.println("Invalid email index.");
                        }
                            } catch (NumberFormatException e) {
                                throw new RuntimeException(e);
                            }

                        break;

                case "D":
                    try {
                        Mailbox mailbox = new Mailbox();
                        System.out.println("Enter email index: ");
                        int index = Integer.parseInt(input.nextLine());
                        Email email = folder.getEmails().get(index);
                        mailbox.deleteEmail(email);
                        System.out.println(email + " has successfully been moved to trash.");


                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "V":
                    try {
                        System.out.println("Enter email index: ");
                        int index = Integer.parseInt(input.nextLine());

                        if(index >= 0 && index < folder.getEmails().size()){
                            Email email = folder.getEmails().get(index);
                            System.out.println("To: " + email.getTo());
                            System.out.println("CC: " + email.getCc());
                            System.out.println("BCC: " +email.getBcc());
                            System.out.println("Subject: " + email.getSubject());
                            System.out.println(email.getBody());
                            System.out.println(email.getTimeStamp());
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "SA":
                    try {
                        System.out.println("Sorted by Subject Ascending order: ");
                        folder.sortBySubjectAscending();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "SD":
                    try {
                        System.out.println("Sorted by Subject Descending order: ");
                        folder.sortBySubjectDescending();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "DA":
                    try {
                        System.out.println("Sorted by Date Ascending order: ");
                        folder.sortByDateAscending();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "DD":
                    try {
                        System.out.println("Sorted by Date Descending order: ");
                        folder.sortByDateDescending();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "R":
                    try {
                        return;

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                default:
                    System.out.println("Invalid option.");

            }
        }
    }

    private static final String Mailbox_Filename = "mailbox.obj";
    private static Mailbox loadMailbox() {
        Mailbox mailbox = null;
        File mailboxFile = new File (Mailbox_Filename);
        if(mailboxFile.exists()){
            try{
                FileInputStream input_file = new FileInputStream("mailbox.obj");
                ObjectInputStream input_object = new ObjectInputStream(input_file);
                mailbox = (Mailbox) input_object.readObject();
                input_file.close();
        } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
                
            }else{
            mailbox = new Mailbox();
        }
        return mailbox;
            
    }

    private static void saveMailbox(Mailbox mailbox){
        try{
            FileOutputStream output_file = new FileOutputStream("mailbox.obj");
            ObjectOutputStream output_object = new ObjectOutputStream(output_file);
            output_object.writeObject(mailbox);
            output_object.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayMailbox(Mailbox mailbox){
        System.out.println("Mailbox:");
        System.out.println("--------");
        Folder[] folders = mailbox.getFolders().toArray(new Folder[0]);
        for (int i = 0; i < folders.length; i++) {
            Folder folder = folders[i];
            System.out.println(folder.getName());
        }

    }


}