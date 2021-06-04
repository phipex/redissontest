package co.com.ies.pruebas.redisson;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonClientConfiguration {

    private static final String NO_CONFIG = "none";

    private RedissonClient redissonClient;

    @Autowired
    private ApplicationPropierties applicationPropierties;

    @Bean
    public RedissonClient getRedissonClient(){
        System.out.println("redisUrl = " + getRedisUrl());
        if (redisIsNotConfigured()){
            return null;
        }

        if (redissonClient == null) {
            Config config = new Config();
            // use single Redis server
            //config.useSingleServer().setAddress("redis://127.0.0.1:6379");
            config.useSingleServer().setAddress(getRedisUrl());
            redissonClient = Redisson.create(config);
        }
        return redissonClient;
    }

    private String getRedisUrl(){
        //return applicationPropierties.getRedisurl();
        return "redis://127.0.0.1:6379";
    }

    private boolean redisIsNotConfigured() {
        String redisUrl = getRedisUrl();
        System.out.println("redisIsNotConfigured::redisUrl = " + redisUrl);
        return NO_CONFIG.equals(redisUrl) || redisUrl == null;
    }

}
