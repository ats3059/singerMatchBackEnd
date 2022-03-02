package success.singermatch.domain.member.common;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Member {

    @NotBlank
    private String userId;

    @NotBlank
    // 정규식
    private String password;

//    private String passwordCheck;   ??

    @NotBlank
    private String username;

    private String email;

    private String phone;

    private String address;

    private String salt;

    private String hashingPassword;

    private MemberStatus memberStatus = MemberStatus.SUCCESS;

}
