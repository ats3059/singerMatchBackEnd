package success.singermatch.domain.common;

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
    private String password;

    @NotBlank
    private String username;

    private String email;

    private String phone;

    private String address;

    private String salt;

    private String hashingPassword;

    private LoginStatus loginStatus;

}
