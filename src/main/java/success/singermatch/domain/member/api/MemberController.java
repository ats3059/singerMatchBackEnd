package success.singermatch.domain.member.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import success.singermatch.domain.member.dto.Member;
import success.singermatch.domain.member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberRepository memberRepository;

    @PostMapping("/add")
    public String add(Member member) {
        memberRepository.save(member);
        return "ok";
    }

    @PostMapping("/findAll")
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @PostMapping("/check")
    public String checkId(String userId) {
        Optional<Member> byId = memberRepository.checkId(userId);
        if (byId.isPresent())
            return byId.get().getUserId() + "는 이미 사용중입니다.";
        else
            return "사용가능한 아이디 입니다.";
    }

}
