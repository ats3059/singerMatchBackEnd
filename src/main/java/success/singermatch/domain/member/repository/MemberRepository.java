package success.singermatch.domain.member.repository;

import success.singermatch.domain.common.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    List<Member> findAll();

    Optional<Member> checkIdDuplication(String userId);
}
