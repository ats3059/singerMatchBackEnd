package success.singermatch.domain.member.signup.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import success.singermatch.domain.member.common.TmpDatabase;
import success.singermatch.domain.member.common.Member;

import java.util.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemoryMemberRepository implements MemberRepository {

    private final TmpDatabase tmpDB;


    // 회원가입
    @Override
    public Member save(Member member) {
        tmpDB.memberStore.put(member.getUserId(), member);
        return member;
    }

    // 회원목록 찾기
    @Override
    public List<Member> findAll() {
        return new ArrayList<Member>(tmpDB.memberStore.values());
    }

    // 아이디 중복 체크
    @Override
    public Optional<Member> checkIdDuplication(String userId) {
        return findAll().stream()
                .filter((m) -> m.getUserId().equals(userId))
                .findFirst();
    }
}
