package success.singermatch.global.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Component;
import success.singermatch.global.common.MailProperties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailUtils {

    private final MailProperties mailProperties;

    private final JavaMailSender mailSender;

    /**
     *  메일 발송
     *
     */
    public boolean send() {

        String to = "jinsb1995@gmail.com";

        String sendFrom = mailProperties.getFromMail();

        String subject = "제목입니다.";

        StringBuilder content = new StringBuilder();
        content.append("<html>");
        content.append("<body>");
        content.append("<p>Hello</p>");
        content.append("<b>Hello</b>");
        content.append("</body>");
        content.append("</html>");

        // 테스트만 하면 됨

        try {
            MimeMessage mm = mailSender.createMimeMessage();
            mm.setRecipient(Message.RecipientType.TO, setAddress(to));
            mm.setFrom(setAddress(sendFrom));
            mm.setSubject(subject);

            // 내용에 html을 담기 위한 작업
            setMailContent(content.toString(), mm);

            mailSender.send(mm);
        } catch(Exception e) {
            log.info(e.getMessage());
        }

        return true;
    }

    // 내용에 html을 담기 위한 작업
    private void setMailContent(String content, MimeMessage mm) throws MessagingException {
        Multipart mPart = new MimeMultipart();
        MimeBodyPart mBody = new MimeBodyPart();

        mBody.setText(content, "UTF-8", "html");
        mPart.addBodyPart(mBody);

        mm.setContent(mPart);
    }

    // 단건 - 수신, 발신자를 Address객체로 반환시켜준다.
    private InternetAddress setAddress(String mail) throws Exception {
        return new InternetAddress(mail);
    }

    // 다건 - 수신, 발신자를 Address객체로 반환시켜준다.
    private InternetAddress[] setAddresses(String[] mails) throws Exception {
        InternetAddress[] mailsArr = new InternetAddress[mails.length];

        for (int i = 0; i < mailsArr.length; i++)
            mailsArr[i] = new InternetAddress(mails[i]);

        return mailsArr;
    }

}
