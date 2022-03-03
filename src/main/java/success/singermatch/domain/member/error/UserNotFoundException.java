package success.singermatch.domain.member.error;


import lombok.Getter;
import lombok.NoArgsConstructor;
import success.singermatch.domain.member.common.MemberStatus;

@NoArgsConstructor
public class UserNotFoundException extends Exception {

    MemberStatus memberStatus;

    public UserNotFoundException(String msg) {
        super(msg);
    }

    public UserNotFoundException(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    public MemberStatus getMemberStatus() {
        return this.memberStatus;
    }

}
