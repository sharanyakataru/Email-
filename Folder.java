/**This is the Folder class which represents the email folder
 * @author sharanyakataru
 * email: sharanya.kataru@stonybrook.edu
 * SBU ID: 114850472
 * Recitation: R30 sec:06
 */
import java.util.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Folder implements Serializable {
    private LinkedList<Email> emails;
   private PriorityQueue<Email> sortedEmails;
    private String name;
    private String currentSortingMethod;


    /**
     * @param name
     * name of the folder
     */
    public Folder (String name){
     this.name = name;
     this.currentSortingMethod = "dateDescending";
     this. emails = new LinkedList<>();
     this.sortedEmails = new PriorityQueue<>(new Comparator<Email>() {
      @Override
      public int compare(Email email1, Email email2) {
       return email2.getTimeStamp().compareTo(email1.getTimeStamp());
      }
     });
    }


    public Folder() {
        this.currentSortingMethod = "dateDescending";
        this. emails = new LinkedList<>();
        this.sortedEmails = new PriorityQueue<>(new Comparator<Email>() {
            @Override
            public int compare(Email email1, Email email2) {
                return email2.getTimeStamp().compareTo(email1.getTimeStamp());
            }
        });

    }

    /**
     * @return
     * the name of the folder
     */
    public String getName(){
     return name;
    }

    /**
     * @param name
     * stores the name of the folder
     */
    public  void setName(String name){
     this.name = name;
    }

    /**
     * @return
     * all the emails in the folder
     */
    public LinkedList<Email> getEmails() {
      return emails;
    }

    /**
     * @param emails
     * stores all the emails in the folder
     */
    public void setEmails(LinkedList<Email> emails) {
       this.emails = emails;
    }

    /**
     * @return
     * the current sorting method
     */
     public String getCurrentSortingMethod() {
        return currentSortingMethod;
    }

    /**
     * @param currentSortingMethod
     * stores the current sorting method
     */
     public void setCurrentSortingMethod(String currentSortingMethod) {
        this.currentSortingMethod = currentSortingMethod;
    }

    /** Adds email to the folder
     * @param email
     * email that is being added to the folder
     */
 public void addEmail(Email email){
     emails.add(email);
     sortedEmails.add(email);
    }

    /** Removes email from folder
     * @param email
     * email that is being removed
     * @return
     * email that is removed
     */
    public Email removeEmail(Email email){
     if(email != null){
         if(emails.remove(email)){
             sortedEmails.remove(email);
         }
     }
        return email;
    }

    /** Sorts the emails alphabetically by subject in ascending order.
     *
     */
    public void sortBySubjectAscending(){
     Collections.sort(emails, Comparator.comparing(Email::getSubject));
     currentSortingMethod = "subjectAscending";
    }

    /** Sorts the emails alphabetically by subject in descending order.
     *
     */
    public void sortBySubjectDescending(){
     Collections.sort(emails, Comparator.comparing(Email::getSubject).reversed());
     currentSortingMethod = "subjectDescending";
    }

    /**Sorts the emails by date in ascending order.
     *
     */
    public void sortByDateAscending(){
     Collections.sort(emails, Comparator.comparing(Email::getTimeStamp));
     currentSortingMethod = "dateAscending";
    }

    /**Sorts the emails by date in descending order.
     *
     */
    public void sortByDateDescending(){
     Collections.sort(emails, Comparator.comparing(Email::getTimeStamp).reversed());
     currentSortingMethod = "dateDescending";

    }

    /**
     * @return
     * the name of the email
     */

    @Override
    public String toString() {
        return getName();
    }
}
