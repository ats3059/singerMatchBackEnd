package success.singermatch.config;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
public class JasyptTest {

    @Value("${spring.mail.password}")
    private String realPassword;

    @Test
    void jasyptTest() {
        // 실제 비밀번호
//        String originalPassword = "xmfjkgwskeqgqrgg";
        String originalPassword = "jinsb1995@gmail.com";
//        String encryptKey = System.getProperty("jasypt.encryptor.password");    // Key_SingerMatch
        String encryptKey = "Key_SingerMatch";    // Key_SingerMatch


        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        // 암.복호화 시 사용할 Key 세팅
        config.setPassword(encryptKey);
        // start default setting
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        // end default setting

        encryptor.setConfig(config);

        System.out.println("datasource.password 에 들어갈 암호화 된 비밀번호");
        String encPassword = encryptor.encrypt(originalPassword);
        System.out.println("암호화 = " + encPassword);
        String decPassword = encryptor.decrypt(encPassword);
        System.out.println("복호화 = " + decPassword);

    }


    @Test
    void asd() {
        System.out.println(realPassword);
    }

}
