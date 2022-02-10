package success.singermatch.member;

import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashSaltTest {

    private static final int SALT_SIZE = 16;


    @Test
    void hashTest() throws Exception {
        String id = "jinsb1995";
        String password = "123456";
        String SALT = getSalt();
        String hashedPassword = Hashing(password.getBytes(), SALT);

        System.out.println("hashedPassword = " + hashedPassword);
        System.out.println("salt = " + SALT);

        //7f31c808e8823846d2af6a8884cc0ab4f9c8cc256af2e980e90549fc69bec555

    }

    // 비밀번호 해싱
    String Hashing(byte[] password, String salt) throws Exception {

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // key-stretching
        for (int i = 0; i < 10000; i++) {
            String saltedPassword = byteToString(password) + salt;  // 원본password와 Salt를 합쳐 새로운 문자를 생성한다.
            md.update(saltedPassword.getBytes());                   // saltedPassword의 문자열을 해싱하여 md에 담아둔다.
            password = md.digest();                                 // md객체로 digest를 얻어 password를 갱신한다.
        }
        return byteToString(password);
    }


    //
    String getSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] saltArr = new byte[SALT_SIZE];
        sr.nextBytes(saltArr);   // saltArr사이즈에 랜덤한 byte를 채워 넣는다.

        return byteToString(saltArr);
    }

    String byteToString(byte[] saltArr) {
        StringBuilder sb = new StringBuilder();
        for (byte a : saltArr) {
            sb.append(String.format("%02x", a));   // byte를 문자열로
        }
        return sb.toString();
    }
}
