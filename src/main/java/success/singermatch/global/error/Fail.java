package success.singermatch.global.error;
import lombok.Getter;

@Getter
public enum Fail {
    NOT_FOUND("해당하는 페이지를 찾지 못했습니다.",400,"T001"),
    BINDING_FAIL("bindFail",400,"T002"),
    MESSAGE_CONVERT_FAIL("convertFail",400,"T003");



    private final String reason;
    private final int statusCode;
    private final String errCode;


    Fail(String reason , int statusCode , String errCode){
        this.reason = reason;
        this.statusCode = statusCode;
        this.errCode = errCode;
    }




}
