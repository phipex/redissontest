package co.com.ies.pruebas.redisson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RedissonApplication {

    private final BlockServiceImpl blockService;


    public RedissonApplication(BlockServiceImpl blockService) {
        this.blockService = blockService;
    }


    public static void main(String[] args) {

        //SpringApplication.run(RedissonApplication.class, args);

        ApplicationContext context
                = new AnnotationConfigApplicationContext(RedissonApplication.class);

        RedissonApplication p = context.getBean(RedissonApplication.class);
        p.start2();
    }



    private void start2() {
        final String nameLock = "dos";
        final boolean lock = blockService.lock(nameLock);
        System.out.println("lock = " + lock);

        if (lock){
            System.out.println("============= primero");
        } else {
            System.out.println("============= segundo");
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            final boolean forceUnlock = blockService.forceUnlock(nameLock);
            System.out.println("forceUnlock = " + forceUnlock);
        }

    }

    private void start() {
        final String nameLock = "dos";

        boolean unoIsBlocked = blockService.isLocked(nameLock);
        System.out.println("1 unoIsBlocked = " + unoIsBlocked);

        final boolean lock = blockService.lock(nameLock);
        System.out.println("lock = " + lock);

        unoIsBlocked = blockService.isLocked(nameLock);
        System.out.println("2 unoIsBlocked = " + unoIsBlocked);

        final boolean lock2 = blockService.lock(nameLock);
        System.out.println("lock2 = " + lock2);
        try {
            blockService.unLock(nameLock);
        } catch (IllegalMonitorStateException imse) {
            imse.printStackTrace();
            blockService.forceUnlock(nameLock);
        } catch (Exception e){
            e.printStackTrace();
        }

        boolean lockedAfter = blockService.isLocked(nameLock);
        System.out.println("3 lockedAfter = " + lockedAfter);

    }

}
