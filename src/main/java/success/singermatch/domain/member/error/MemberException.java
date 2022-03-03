package success.singermatch.domain.member.error;


import lombok.Getter;
import lombok.NoArgsConstructor;
import success.singermatch.domain.member.common.MemberStatus;

/**
 * 회원가입 및 로그인 시 발생하는 에러
 */
@Getter
@NoArgsConstructor
public class MemberException extends Exception {

    MemberStatus memberStatus;

    public MemberException(String msg) {
        super(msg);
    }

    public MemberException(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

}
