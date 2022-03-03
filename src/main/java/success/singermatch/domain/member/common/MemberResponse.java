package success.singermatch.domain.member.common;

import lombok.*;


/**
 * 로그인 및 회원가입 시도 결과
 */
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {

    private String message;

    private String status;
}
