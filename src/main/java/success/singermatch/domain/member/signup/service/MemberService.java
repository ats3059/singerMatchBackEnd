package success.singermatch.domain.member.signup.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import success.singermatch.domain.member.common.Member;
import success.singermatch.domain.member.error.IdDuplicationException;
import success.singermatch.domain.member.signup.repository.MemberRepository;
import success.singermatch.global.util.HashSalt;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final HashSalt hashSalt;

    // 회원가입
    public Member save(Member member) throws Exception {

        boolean checkId = this.checkIdDuplication(member.getUserId());
        if (checkId) throw new IdDuplicationException("이미 사용중인 아이디 입니다.");

        // password를 hashing 처리한다.
        String salt = hashSalt.getSalt();
        String hashing = hashSalt.hashing(member.getPassword().getBytes(), salt);

        member.setSalt(salt);
        member.setHashingPassword(hashing);

        return memberRepository.save(member);
    }

    // 회원 목록
    public List<Member> findAll() {
        return memberRepository.findAll();
    }


    // 아이디 중복 체크
    public boolean checkIdDuplication(String userId) {
        return memberRepository.checkIdDuplication(userId);
    }


    // 로그인
//    public Member login(String loginId, String password) {
//        return memberRepository.checkIdDuplication(loginId)
//                .filter(m -> m.getPassword().equals(password))
//                .orElse(null);
//    }
}
