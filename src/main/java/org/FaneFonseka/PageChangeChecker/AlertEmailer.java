package org.FaneFonseka.PageChangeChecker;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Fane on 12/04/2017.
 */
public class AlertEmailer {
    private final PageChangeChecker pageChangeChecker;
    private String from;
    private String to;
    private MimeMessage message;

    public AlertEmailer(PageChangeChecker pageChangeChecker) {

        this.pageChangeChecker = pageChangeChecker;
    }

    public void setUpEmail() throws MessagingException {

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "localhost");
        Session session = Session.getDefaultInstance(properties);

        message = new MimeMessage(session);
        message.setFrom(new InternetAddress("fane@klodge.com"));
        message.addRecipient(Message.RecipientType.TO,new InternetAddress("fane@klodge.com"));
        message.setSubject("your website has changed");
        message.setText("Hi, /n /n Your website has changed! n/ n/ Thanks n/ n/ MailBot");

        Transport.send(message);
        System.out.println("message sent successfully....");


    }

    public void emailOnPageChange() throws IOException, MessagingException {

        if(pageChangeChecker.webPageHasChanged()){

            Transport.send(message);
            System.out.println("message sent successfully....");

        }

    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public static void main(String[] args) throws IOException, MessagingException {

        ContentFetcher contentFetcher = new ContentFetcher() {
            public String getHTMLBody() throws IOException {
                return null;
            }
        };
        PageChangeChecker pageChangeChecker = new PageChangeChecker(contentFetcher);
        AlertEmailer alertEmailer = new AlertEmailer(pageChangeChecker);

        alertEmailer.setUpEmail();



    }
}
