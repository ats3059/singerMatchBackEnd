package success.singermatch.domain.member.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import success.singermatch.domain.member.dto.HashingPassword;
import success.singermatch.domain.member.dto.Member;

import java.util.*;

@Slf4j
@Repository
public class MemoryMemberRepository implements MemberRepository {

    private final Map<String, Member> store = new HashMap<>();
    private final Map<String, HashingPassword> hashSaltStore = new HashMap<>();

    @Override
    public Member save(Member member) {
        store.put(member.getUserId(), member);
        hashSaltStore.put(member.getUserId(), member.getHashingPassword());
        return member;
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<Member>(store.values());
    }

    @Override
    public Optional<Member> checkId(String userId) {
        return findAll().stream()
                .filter((m) -> m.getUserId().equals(userId))
                .findFirst();
    }
}
