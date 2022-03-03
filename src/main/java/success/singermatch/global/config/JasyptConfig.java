package success.singermatch.global.config;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class JasyptConfig {
/*  1.   ***.properties 파일로 관리하려면 이 방법
    @Value("${jasypt.encryptor.password}")
    private String encryptKey;
 */

//  2. VM Option을 이용하려면 이 방법
    private String encryptKey = System.getProperty("jasypt.encryptor.password");

//    @Value("${spring.mail.password}")
//    String password;

    private final static String ALGORITHM = "PBEWithMD5AndDES";

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        log.info("비밀번호 암호화 할 때 사용한 Key = {}", encryptor);
        config.setPassword(encryptKey);    // 실제 password에 대입할 key

        // start default setting
        config.setAlgorithm(ALGORITHM);
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        // end default setting

        encryptor.setConfig(config);

//        log.info("encryptKey ==== {}", encryptKey);
//        log.info("password ==== {}", encryptor.decrypt(password));

        return encryptor;
    }


}
