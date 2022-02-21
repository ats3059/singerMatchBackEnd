package success.singermatch.domain.member.login.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import success.singermatch.domain.member.common.TmpDatabase;
import success.singermatch.domain.member.common.Member;
import success.singermatch.domain.member.login.dto.LoginForm;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemoryLoginRepository implements LoginRepository {

    private final TmpDatabase tmpDB;

    /**
     * 사용자 로그인시 ID 존재여부 체크
     */
    @Override
    public Member findById(LoginForm form) {
        return tmpDB.memberStore.get(form.getUserId());
    }
}
