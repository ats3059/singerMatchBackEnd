package success.singermatch.domain.member.common;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TmpDatabase {

    // 임시 회원 저장소
    public Map<String, Member> memberStore = new HashMap<>();

}
