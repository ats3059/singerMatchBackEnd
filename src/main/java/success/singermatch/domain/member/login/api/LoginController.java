package success.singermatch.domain.member.login.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import success.singermatch.domain.member.common.MemberResponse;
import success.singermatch.domain.member.common.MemberStatus;
import success.singermatch.domain.member.common.SessionConst;
import success.singermatch.domain.member.common.Member;
import success.singermatch.domain.member.login.dto.LoginForm;
import success.singermatch.domain.member.login.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    /**
     * 사용자 로그인 처리
     * @param form
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public ResponseEntity<MemberResponse> login(@RequestBody LoginForm form, HttpServletRequest request) throws Exception {

        Member loginMember = loginService.login(form);

        // 세션에 로그인 회원 정보 보관
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return ResponseEntity.ok()
                .body(new MemberResponse(MemberStatus.SUCCESS.getReason(), "ok"));
    }


    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }



//      세션이 들어있는지 확인
//    @GetMapping("/session-info")
//    public String sessionInfo(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            return "세션 정보가 없습니다.";
//        }
//
//        // 세션 데이터 출력
//        session.getAttributeNames().asIterator()
//                .forEachRemaining(name -> System.out.println("session name = " + name + ", value = " + session.getAttribute(name)));
//
//        System.out.println("sessionId = " + session.getId());
//        System.out.println("maxInactiveInterval = " + session.getMaxInactiveInterval());
//        System.out.println("creationTime = " + new Date(session.getCreationTime()));
//        System.out.println("lastAccessedTime = " + new Date(session.getLastAccessedTime()));
//        System.out.println("isNew = " + session.isNew());
//        return "ok";
//    }





}
