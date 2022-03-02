package success.singermatch.domain.member.login.repository;

import success.singermatch.domain.member.common.Member;
import success.singermatch.domain.member.login.dto.LoginForm;

public interface LoginRepository {

    Member findById(LoginForm form);

}
