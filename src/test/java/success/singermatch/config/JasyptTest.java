package success.singermatch.config;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;

public class JasyptTest {

    @Test
    void jasyptTest() {
        // 실제 비밀번호
        String originalPassword = "xmfjkgwskeqgqrgg";

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        // 암.복호화 시 사용할 Key 세팅
        config.setPassword("Key_SingerMatch");

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

        System.out.println("datasource.password 에 들어갈 암호화 된 password");
        System.out.println("password = " + encryptor.encrypt(originalPassword));

    }
}
