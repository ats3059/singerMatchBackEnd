package success.singermatch.global.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("spring.mail")
/*
    application.properties의 application.mail 부분을 여기와 맵핑시켜준다.
    Bean으로 등록 되어 있어야함
    원래는 @EnableConfigurationProperties을 추가해야하지만 boot가 알아서 처리해주기 때문에 Bean으로 등록만 하면 된다.
 */
public class MailProperties {

    // SMTP 서버
    private String host;

    // SMTP 계정
    private String username;

    // SMTP 계정 비밀번호
    private String password;

    // SMTP 포트번호
    private int port;

    // 메일 연결자 (계정에 @gmail.com 등 과 같은 주소가 없을때를 위한 기본값)
    private String supplier;

    // 발신자 메일
    private String fromMail;



}

