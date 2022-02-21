package success.singermatch.domain.member.signup.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import success.singermatch.domain.member.common.Member;
import success.singermatch.domain.member.signup.repository.MemberRepository;
import success.singermatch.domain.member.signup.service.MemberService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;


    @PostMapping("/add")
    public String add(@RequestBody Member member) throws Exception {

        memberService.save(member);

        return "ok";
    }

    @GetMapping("/findAll")
    public List<Member> findAll() {
        return memberService.findAll();
    }


    @GetMapping("/check")
    public String checkIdDuplication(String userId, Model model) {
        Optional<Member> byId = memberService.checkIdDuplication(userId);
        String msg = "";
        if (byId.isPresent())
            return byId.get().getUserId() + "는 이미 사용중인 아이디 입니다.";
        else
            return "사용가능한 아이디 입니다.";
    }


}
