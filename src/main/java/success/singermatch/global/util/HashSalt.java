package success.singermatch.global.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.SecureRandom;

@Component
public class HashSalt {

    private static final int SALT_SIZE = 16;

    public String hashing(byte[] password, String salt) throws Exception {

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        for (int i = 0; i < 10000; i++) {
            String saltedPassword = byteToString(password) + salt;  // 원본 password와 salt를 합쳐 새로운 문자를 생성한다.
            md.update(saltedPassword.getBytes());             // saltedPassword의 문자열을 해싱하여 md에 담아둔다.
            password = md.digest();                           // md객체로 digest를 얻어 password를 갱신한다.
        }

        return byteToString(password);
    }

    // password에 추가할 salt를 생성한다.
    public String getSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] saltArr = new byte[SALT_SIZE];
        // saltArr사이즈에 랜덤한 byte를 채워 넣는다.
        sr.nextBytes(saltArr);

        return byteToString(saltArr);
    }

    // byte를 String으로 변환한다.
    private String byteToString(byte[] byteArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : byteArr) {
            sb.append(String.format("%02x", b));      // byte를 문자열로
        }
        return sb.toString();
    }


}
