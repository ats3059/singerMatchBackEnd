package success.singermatch.global.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import success.singermatch.global.common.MailProperties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailUtils {

    private final MailProperties mailProperties;

    private final JavaMailSender mailSender;

    private final SpringTemplateEngine templateEngine;

    /**
     *  메일 발송
     * @Param title 메일 제목
     * @Param to 수신자
     * @Param templateName 이메일 템플릿 명
     * @Param values 이메일에 들어갈 값
     */
    public void send(String title, String to, String templateName, Map<String, String> values) {

        String mailContent = createMailTemplate(templateName, values);

        try {
            MimeMessage mm = mailSender.createMimeMessage();
            
            mm.setRecipient(Message.RecipientType.TO, setAddress(to));
            mm.setFrom(setAddress(mailProperties.getFromMail()));

            mm.setSubject(title);
            // 내용에 html을 담기 위한 작업
            setMailContent(mailContent, mm);

            mailSender.send(mm);
        } catch(Exception e) {
            log.info(e.getMessage());
        }
    }




    /**
     * 단건 - 수신, 발신자를 Address객체로 반환시켜준다.
     * @Param mail 수신자 메일
     * usage - mm.setRecipients(Message.RecipientType.TO, setAddress(to));
     */
    private InternetAddress setAddress(String mail) throws Exception {
        return new InternetAddress(mail);
    }

    /**
     * 다건 - 수신, 발신자를 Address객체로 반환시켜준다.
     * @Param mails 수신자 메일 배열
     * usage - mm.setRecipients(Message.RecipientType.TO, setAddresses(to));
     */
    private InternetAddress[] setAddress(String[] mails) throws Exception {
        InternetAddress[] mailsArr = new InternetAddress[mails.length];

        for (int i = 0; i < mailsArr.length; i++)
            mailsArr[i] = new InternetAddress(mails[i]);

        return mailsArr;
    }


    // 메일 템플릿에 들어갈 값 세팅
    private String createMailTemplate(String templateName, Map<String, String> values) {
        Context context = new Context();
        values.forEach((key, value) -> {
            log.info("key = {}, value = {}", key, value);
            context.setVariable(key, value);
        });

        return templateEngine.process(templateName, context);
    }


    // 내용에 html을 담기 위한 작업
    private void setMailContent(String content, MimeMessage mm) throws MessagingException {
        Multipart mPart = new MimeMultipart();
        MimeBodyPart mBody = new MimeBodyPart();

        mBody.setText(content, "UTF-8", "html");
        mPart.addBodyPart(mBody);

        mm.setContent(mPart);
    }
}



//        StringBuilder content = new StringBuilder();
//        content.append("<html>");
//        content.append("<body>");
//        content.append("<p>안태선님이 배진성님의 게시물에 좋아요를 누르셨습니다!</p><br />");
//        content.append("<b>http://localhost:9800</b>");
//        content.append("</body>");
//        content.append("</html>");
//        return content.toString();