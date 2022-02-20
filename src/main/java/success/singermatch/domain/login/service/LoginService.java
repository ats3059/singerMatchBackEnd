package success.singermatch.domain.login.service;

import success.singermatch.domain.common.Member;
import success.singermatch.domain.login.dto.LoginForm;

public interface LoginService {

    Member login(LoginForm form) throws Exception;

}
