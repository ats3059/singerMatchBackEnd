package success.singermatch.domain.member.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import success.singermatch.domain.member.dto.Member;
import success.singermatch.domain.member.service.MemberService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

//    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @PostMapping("/add")
    public String add(Member member) throws Exception {
        memberService.save(member);
        return "ok";
//        return "redirect:/api/member/login";
    }

    @GetMapping("/findAll")
    public List<Member> findAll() {
        return memberService.findAll();
    }


    @GetMapping("/check")
    public String checkId(String userId) {
        Optional<Member> byId = memberService.checkId(userId);
        if (byId.isPresent())
            return byId.get().getUserId() + "는 이미 사용중인 아이디 입니다.";
        else
            return "사용가능한 아이디 입니다.";
    }

}
