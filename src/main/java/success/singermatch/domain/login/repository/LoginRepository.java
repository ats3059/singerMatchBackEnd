package success.singermatch.domain.login.repository;

import success.singermatch.domain.common.Member;
import success.singermatch.domain.login.dto.LoginForm;

public interface LoginRepository {

    Member findById(LoginForm form);

}
