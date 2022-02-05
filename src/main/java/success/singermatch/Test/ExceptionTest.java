package success.singermatch.Test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api1")
public class ExceptionTest {

    @PostMapping("/test")
    public String test(@Validated @RequestBody TestObject testObject){
        System.out.println(testObject);
        return "success";
    }

    @Getter
    @Setter
    @ToString
    static class TestObject{
        @NotNull
        @Length(min = 2, max = 10)
        private String name;
        @NotNull
        private Integer age;
    }

}
