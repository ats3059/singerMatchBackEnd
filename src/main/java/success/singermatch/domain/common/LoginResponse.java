package success.singermatch.domain.common;

import lombok.*;


/**
 * 로그인 시도 결과
 */
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class LoginResponse {

    private String message;

    private String status;
}
