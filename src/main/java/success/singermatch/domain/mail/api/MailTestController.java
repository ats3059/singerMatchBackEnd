package success.singermatch.domain.mail.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import success.singermatch.global.util.MailUtils;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MailTestController {

    private final MailUtils mailUtils;

    @GetMapping("/tmp")
    public String tmp() {
        log.info("여기는 임시 콘트롤러 입니다.");
        return "OK";
    }

    @GetMapping("/mailTest")
    public String mailTest(String name) {
        log.info("메일 테스트");

        // 메일에 넣어줄 값
        Map<String, String> values = new HashMap<>();
        values.put("name", "배진성");
        // to는 들어온 값
        mailUtils.send("가입을 환영합니다.", "jinsb1995@gmail.com", "welcome", values);

        return "OK";
    }
    
    @GetMapping("/matchTest")
    public String matchTest() {
        log.info("매칭 테스트");

        // 메일에 넣어줄 값
        Map<String, String> values = new HashMap<>();
        values.put("from", "배진성");
        values.put("to", "안태선");
        // to는 들어온 값
        mailUtils.send("좋아요 안내", "jinsb1995@gmail.com", "match", values);

        return "OK";
    }
}
