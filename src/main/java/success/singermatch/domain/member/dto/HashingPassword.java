package success.singermatch.domain.member.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class HashingPassword {

    private String salt;

    private String hashingPassword;
}
