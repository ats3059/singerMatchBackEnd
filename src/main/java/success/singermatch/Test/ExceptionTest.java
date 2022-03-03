package success.singermatch.Test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api1")
public class ExceptionTest {


    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @PostMapping("/test")
    public String test(@Validated @RequestBody TestObject testObject) {
        return "success";
    }

    @GetMapping("/test2")
    public String asd() {
        System.out.println(username);
        System.out.println(password);
        return "username: " + username + "        password:" + password;
    }

    @Getter
    @Setter
    @ToString
    static class TestObject{
        @NotNull
        @Length(min = 2, max = 10)
        private String name;
        @NotNull
        @Max(100)
        private Integer age;
    }

}
