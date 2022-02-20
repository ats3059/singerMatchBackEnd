package success.singermatch.domain.login.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * 사용자가 로그인할때 입력한 값을 받는다.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LoginForm {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

}
