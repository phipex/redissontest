package co.com.ies.pruebas.redisson;

public interface BloksService {

    boolean isLocked(String lockName);

    boolean lock(String lockName);

    boolean forceUnlock(String lockName);

    void unLock(String lockName);
}
