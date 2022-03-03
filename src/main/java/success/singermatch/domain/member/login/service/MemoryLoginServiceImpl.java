package success.singermatch.domain.member.login.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import success.singermatch.domain.member.common.MemberStatus;
import success.singermatch.domain.member.error.MemberException;
import success.singermatch.domain.member.login.dto.LoginForm;
import success.singermatch.domain.member.common.Member;
import success.singermatch.domain.member.login.repository.LoginRepository;
import success.singermatch.domain.member.error.PasswordNotMatchException;
import success.singermatch.domain.member.error.UserNotFoundException;
import success.singermatch.global.util.HashSalt;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemoryLoginServiceImpl implements LoginService {

    private final HashSalt hashSalt;

    private final LoginRepository loginRepository;


    @Override
    public Member login(LoginForm form) throws Exception {

        Member member = loginRepository.findById(form);
        // 아이디가 존재하지 않음
        if (member == null) throw new MemberException(MemberStatus.USER_NOT_FOUND);

        // 입력받은 password를 hashSalt로 암호화 한다.
        String password = form.getPassword();
        String salt = member.getSalt();
        String hashedPassword = hashSalt.hashing(password.getBytes(), salt);

        // 비밀번호가 맞지 않음
        if (!hashedPassword.equals(member.getHashingPassword()))
            throw new MemberException(MemberStatus.PASSWORD_NOT_MATCH);

        return member;
    }
}
