package success.singermatch.member;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FindTest2 {


    @Test
    void FindUserIdTest() {
        Map<String, String> store = new HashMap<>();
        store.put("1", "1");

        System.out.println(Optional.ofNullable(store.get("2")));

    }
}
