/**This is the Email class which contains information about each email
 * @author sharanyakataru
 * email: sharanya.kataru@stonybrook.edu
 * SBU ID: 114850472
 * Recitation: R30 sec:06
 */
import java.text.SimpleDateFormat;
import java.io.Serializable;
import java.util.GregorianCalendar;

public class Email implements Serializable {
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String body;
    private GregorianCalendar timeStamp;

    /**
     * @param to
     * stores the to field
     * @param cc
     * stores the cc field
     * @param bcc
     * stores the bcc field
     * @param subject
     * stores the subject field
     * @param body
     * stored the body field, all the text in the emails body
     * @param timeStamp
     * time the email was created
     */
    public Email(String to, String cc, String bcc, String subject, String body, GregorianCalendar timeStamp){
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
        this.timeStamp = timeStamp;
    }

    public Email() {

    }

    /**
     * @return
     * the to field of the email
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to
     * stores the to field
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return
     * the cc field of the email
     */
    public String getCc() {
        return cc;
    }

    /**
     * @param cc
     * stores the cc field
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * @return
     * the bcc field of the email
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * @param bcc
     * stores the bcc field
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    /**
     * @return
     * the subject of the email
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     * stores the subject field of the email
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return
     * the body text of the email
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body
     * stores the body of the email
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return
     * the time the email is created
     */
    public GregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    /**
     * @return
     * the time stamp formatted
     */
    public  String getTimeStampFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mma M/d/yyyy");
        return dateFormat.format(timeStamp.getTime());
    }

    /**
     * @param timeStamp
     * stores the time the email was created
     */
    public void setTimeStamp(GregorianCalendar timeStamp) {
        this.timeStamp = timeStamp;
    }

}
