package org.FaneFonseka.PageChangeChecker;

import org.junit.Before;
import org.junit.Test;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Fane on 12/04/2017.
 */
public class AlertEmailerTest {

    private Wiser wiser;

    @Before
    public void setup(){

        wiser = new Wiser();
        wiser.setPort(2500); // Default is 25
        wiser.start();

    }


    @Test
    public void whenWebpageHasChangedEmailAlertIsSent() throws IOException, MessagingException {

        StubPageGenerator stubPageGenerator = new StubPageGenerator();
        PageChangeChecker pageChangeChecker = new PageChangeChecker(stubPageGenerator);
        AlertEmailer alertEmailer = new AlertEmailer(pageChangeChecker);

        alertEmailer.setFrom("fane@klodge.com");
        alertEmailer.setTo("fane@klodge.com");

        alertEmailer.emailOnPageChange();

        List<WiserMessage> messages = wiser.getMessages();

        WiserMessage wiserMessage = messages.get(0);

        MimeMessage mimeMessage = wiserMessage.getMimeMessage();

        System.out.println(mimeMessage.getContent());

    }


}

