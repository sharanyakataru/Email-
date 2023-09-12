/**This is the Mailbox class which represents an email box, containing all the folders
 * @author sharanyakataru
 * email: sharanya.kataru@stonybrook.edu
 * SBU ID: 114850472
 * Recitation: R30 sec:06
 */
import java.util.*;
import java.io.*;

public class Mailbox implements Serializable {
    private Folder inbox;
    private Folder trash;
    private ArrayList<Folder> folders;
    public static Mailbox mailbox;


    public Mailbox(){
        this.inbox = new Folder("Inbox");
        this.trash = new Folder("Trash");
        this.folders = new ArrayList<>();
        folders.add(inbox);
        folders.add(trash);
        mailbox = this;
    }

    /**
     * @return
     * the inbox folder, which cannot be deleted or renamed
     */
    public Folder getInbox() {
        return inbox;
    }

    /**
     * @param inbox
     * stores the special inbox folder
     */
    public void setInbox(Folder inbox) {
        this.inbox = inbox;
    }

    /**
     * @return
     * the trash folder, which cannot be deleted or renamed
     */
    public Folder getTrash() {
        return trash;
    }

    /**
     * @param trash
     * stores the special trash folder
     */
    public void setTrash(Folder trash) {
        this.trash = trash;
    }

    /**
     * @return
     * all the folders in the mailbox
     */
    public ArrayList<Folder> getFolders() {
        return folders;
    }

    /**
     * @param folders
     * stores the custom folders contained in the mailbox
     */
    public void setFolders(ArrayList<Folder> folders) {
        this.folders = folders;
    }

    /**Adds given folder to the list of folders
     * @param folder
     * folder to be added to the inbox
     */
    public void addFolder(Folder folder){
        if(!folderExists(folder.getName())){
            folders.add(folder);
        }else{
            System.out.println("Folder already exists: " + folder.getName());
        }

    }

    /**Removes the given folder from the list of folders
     * @param name
     * name of the folder o be deleted
     */
    public void deleteFolder(String name){
        if(name.equals("Inbox") || name.equals("Trash")){
            System.out.println("Cannot delete mandatory folders.");
            return;
        }
        Iterator<Folder> iterator = folders.iterator();
        while (iterator.hasNext()){
            Folder folder = iterator.next();
            if(folder.getName().equals(name)){
                iterator.remove();
            }
        }

    }


    public void composeEmail(String to, String cc, String bcc, String subject, String body, GregorianCalendar timestamp){
        Email email = new Email(to, cc, bcc, subject, body, timestamp);
        inbox.addEmail(email);
    }

    /**Deleted email by moving it to trash
     * @param email
     * email that needs to be deleted
     */
    public void deleteEmail(Email email){
        if(email == null){
            System.out.println("Invalid email.");
            return;
        }

        Folder current = folderWithEmail(email);
        if(current != null){
            current.removeEmail(email);
            trash.addEmail(email);

        }
    }

    /**removes all emails from the trash folder
     *
     */
    public void clearTrash() {
        int numFolders = folders.size();
        for (int i = 0; i < numFolders; i++) {
            Folder folder = folders.get(i);
            if ("Trash".equals(folder.getName())) {
                folder.getEmails().clear();
                System.out.println("Trash folder is empty now.");
            }
        }
    }

    /**Moves the given email to the given folder
     * @param email
     * email that needs to be moved
     * @param target
     * the target folder the email needs to be moved to
     */
    public void moveEmail(Email email, Folder target){
        Folder current = folderWithEmail(email);
        if(current != null){
            current.removeEmail(email);
            target.addEmail(email);
            System.out.println("Email successfully moved to " + target.getName());
        }else{
            System.out.println("Email not found in any folder.");
        }

        }


    /**Returns folder by folder name
     * @param name
     * name of the folder
     * @return
     * the folder based on the folder name
     */
    public Folder getFolder(String name){
        int numFolders = folders.size();
        for (int i = 0; i < numFolders; i++){
            Folder folder = folders.get(i);
            if(folder.getName().equals(name)){
                return folder;
            }
        }
        return null;
    }

    /**Finds the folder with the specific email
     * @param email
     * email to search for
     * @return
     * the folder with the email
     */
    private Folder folderWithEmail(Email email){
        for(Folder folder: folders){
            for(Email e: folder.getEmails()){
                if(e.equals(email)){
                    return folder;
                }
            }
        }
        return null;
    }


    /**Checks if the folder exists
     * @param name
     * name of the folder
     * @return
     * tue if the folder exists, false otherwise
     */
    private boolean folderExists(String name){
        for (Folder folder: folders){
            if(folder.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
