package success.singermatch.global.util;

import java.util.UUID;

public class StringUtil {

    public static String generateRandomStr() {
        return UUID.randomUUID().toString();
    }
}
