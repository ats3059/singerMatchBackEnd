package success.singermatch.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import success.singermatch.domain.member.dto.HashingPassword;
import success.singermatch.domain.member.dto.Member;
import success.singermatch.domain.member.repository.MemberRepository;
import success.singermatch.global.util.HashSalt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final HashSalt hashSalt;

    public Member save(Member member) throws Exception {
        String salt = hashSalt.getSalt();
        String hashing = hashSalt.hashing(member.getPassword().getBytes(), salt);

        HashingPassword hp = new HashingPassword();
        hp.setSalt(salt);
        hp.setHashingPassword(hashing);
        member.setHashingPassword(hp);
        System.out.println(member.getHashingPassword());

        return memberRepository.save(member);
    }

    public List<Member> findAll() {
        for (Member m : memberRepository.findAll())
            System.out.println(m);
        return memberRepository.findAll();
    }


    public Optional<Member> checkId(String userId) {
        return memberRepository.checkId(userId);
    }
}
