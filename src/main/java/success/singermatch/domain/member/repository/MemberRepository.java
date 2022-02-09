package success.singermatch.domain.member.repository;

import success.singermatch.domain.member.dto.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    List<Member> findAll();

    Optional<Member> checkId(String userId);
}
