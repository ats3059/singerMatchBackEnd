package success.singermatch.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import success.singermatch.domain.common.Member;
import success.singermatch.domain.member.repository.MemberRepository;
import success.singermatch.global.util.HashSalt;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final HashSalt hashSalt;

    // 회원가입
    public Member save(Member member) throws Exception {
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
    public Optional<Member> checkIdDuplication(String userId) {
        return memberRepository.checkIdDuplication(userId);
    }


    // 로그인
    public Member login(String loginId, String password) {
        return memberRepository.checkIdDuplication(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
