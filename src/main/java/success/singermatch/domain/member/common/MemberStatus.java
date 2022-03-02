package success.singermatch.domain.member.common;

import lombok.Getter;

/**
 * 로그인 및 회원가입 시도 상태값
 */
@Getter
public enum MemberStatus {

    SUCCESS("success", 200, "SU001")                 // 성공
    , USER_NOT_FOUND("아이디가 존재하지 않습니다.", 400, "SU002")        // ID 잘못 입력
    , PASSWORD_NOT_MATCH("비밀번호가 올바르지 않습니다.", 400, "SU003")    // 비밀번호 잘못 입력
    , ID_ALREADY_EXIST("이미 사용중인 아이디 입니다.", 400, "SU004");      // ID 중복체크

    private final String reason;
    private final int statusCode;
    private final String errCode;

    MemberStatus(String reason, int statusCode, String errCode) {
        this.reason = reason;
        this.statusCode = statusCode;
        this.errCode = errCode;
    }

}
