package co.com.ies.pruebas.redisson;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedissonLockService {

    private final Optional<RedissonClient> getRedissonClient;

    public RedissonLockService(Optional<RedissonClient> getRedissonClient) {
        this.getRedissonClient = getRedissonClient;
    }

    public boolean isReddisConfigured(){
        final boolean present = getRedissonClient.isPresent();
        System.out.println("Redis is present = " + present);
        return present;

    }

    public boolean isLocked(String lockName) {

        RedissonClient client = getRedissonClient.orElseThrow();
        RLock lock = client.getLock(lockName);
        return lock.isLocked();
    }

    public boolean lock(String lockName) {
        RedissonClient client = getRedissonClient.orElseThrow();
        RLock lock = client.getLock(lockName);
        return lock.tryLock();
    }

    public void unLock(String lockName) {
        RedissonClient client = getRedissonClient.orElseThrow();
        RLock lock = client.getLock(lockName);
        lock.unlock();
    }

    public boolean forceUnlock(String lockName) {
        RedissonClient client = getRedissonClient.orElseThrow();
        RLock lock = client.getLock(lockName);
        return lock.forceUnlock();
    }
}
