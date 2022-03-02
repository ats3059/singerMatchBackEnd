package success.singermatch.domain.member.login.service;

import success.singermatch.domain.member.common.Member;
import success.singermatch.domain.member.login.dto.LoginForm;

public interface LoginService {

    Member login(LoginForm form) throws Exception;

}
