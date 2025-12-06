
package com.MResendizProgramacionNCapas.Configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfigProperties {
    private String secretKey;
    private Long jwtTtl;

    public String getSecretKey() {
        return secretKey;
    }

    public Long getJwtTtl() {
        return jwtTtl;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setJwtTtl(Long jwtTtl) {
        this.jwtTtl = jwtTtl;
    }
}
