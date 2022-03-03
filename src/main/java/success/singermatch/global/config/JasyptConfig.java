package success.singermatch.global.config;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 프로퍼티 파일에 노출되는 정보(비밀번호, 주소 등..)를 암호화
 */
@Slf4j
@Configuration
public class JasyptConfig {
/*
    1.   ***.properties 이용
    @Value("${jasypt.encryptor.password}")
    private String encryptKey;

    2. VM Option
    System.getProperty("jasypt.encryptor.password");
 */

    private String encryptKey = System.getProperty("jasypt.encryptor.password");

    private final static String ALGORITHM = "PBEWithMD5AndDES";

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword(encryptKey);    // 실제 비밀번호를 암/복호화 할 때 사용하는 Key

        // start default setting
        config.setAlgorithm(ALGORITHM);                 // 암/복호화 알고리즘
        config.setKeyObtentionIterations("1000");       // 반복할 해싱 횟수
        config.setPoolSize("1");                        // 인스턴스 pool
        config.setProviderName("SunJCE");               // 제조사?
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");      // salt 생성 클래스
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");            //
        config.setStringOutputType("base64");           // encoding 방식
        // end default setting

        encryptor.setConfig(config);

        return encryptor;
    }


}
