package co.com.ies.pruebas.redisson;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationPropierties {

    private String redisurl;

    public String getRedisurl() {
        return redisurl;
    }

    public void setRedisurl(String redisurl) {
        this.redisurl = redisurl;
    }
}
