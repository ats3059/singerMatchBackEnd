package success.singermatch.domain.tmp1.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TmpController {

    @GetMapping("/tmp")
    public String tmp() {
        log.info("여기는 임시 콘트롤러 입니다.");
        return "OK";
    }
}
