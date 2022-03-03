package success.singermatch.domain.member.error;


import lombok.Getter;
import lombok.NoArgsConstructor;
import success.singermatch.domain.member.common.MemberStatus;

@Getter
@NoArgsConstructor
public class PasswordNotMatchException extends Exception {

    MemberStatus memberStatus;

    public PasswordNotMatchException(String msg) {
        super(msg);
    }

    public PasswordNotMatchException(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

}
