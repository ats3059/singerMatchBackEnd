package success.singermatch.global.error;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {
    private String message;
    private int status;
    private List<FieldError> errors;
    private String code;

    public ErrorResponse(final String message, final int status, final String code) {
        this.message = message;
        this.status = status;
        this.code = code;
        errors = new ArrayList<>();
    }

    public ErrorResponse(final String message, final int status, final String code, BindingResult bindingResult, MessageSource messageSource) {
        this.message = message;
        this.status = status;
        this.code = code;
        this.errors = FieldError.of(bindingResult,messageSource);
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        private FieldError(final String field, final String value, final String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        private static List<FieldError> of(final String field, final String value, final String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(final BindingResult bindingResult,MessageSource messageSource) {
                final List<org.springframework.validation.FieldError> list = bindingResult.getFieldErrors();
                return list.stream()
                        .map(error -> new FieldError(
                                error.getField()
                                , error.getRejectedValue() == null ? "" : error.getRejectedValue().toString()
                                , messageSource.getMessage(error, Locale.KOREAN)))
                        .collect(Collectors.toList());
        }
    }


}
