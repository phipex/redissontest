package co.com.ies.pruebas.redisson;

import org.springframework.stereotype.Service;

@Service
public class BlockServiceImpl implements BloksService {

    final RedissonLockService redissonLockService;

    public BlockServiceImpl(RedissonLockService redissonLockService) {
        this.redissonLockService = redissonLockService;
    }


    @Override
    public boolean isLocked(String lockName) {
        if (!redissonLockService.isReddisConfigured()){
            return false;
        }

        return redissonLockService.isLocked(lockName);
    }

    @Override
    public boolean lock(String lockName) {
        if (!redissonLockService.isReddisConfigured()){
            return true;
        }
        return redissonLockService.lock(lockName);
    }

    @Override
    public boolean forceUnlock(String lockName) {
        if (!redissonLockService.isReddisConfigured()){
            return false;
        }
        return redissonLockService.forceUnlock(lockName);
    }

    @Override
    public void unLock(String lockName) {
        if (!redissonLockService.isReddisConfigured()){
            return;
        }
        redissonLockService.unLock(lockName);
    }
}
