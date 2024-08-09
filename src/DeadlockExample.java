class SharedResource {
    synchronized void test1(SharedResource s2) {
        System.out.println("test1-begin");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        s2.test2(this);
        System.out.println("test1-end");
    }

    synchronized void test2(SharedResource s1) {
        System.out.println("test2-begin");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        s1.test1(this);
        System.out.println("test2-end");
    }
}

public class DeadlockExample {
    public static void main(String[] args) {
        final SharedResource s1 = new SharedResource();
        final SharedResource s2 = new SharedResource();

        Thread t1 = new Thread(() -> s1.test1(s2));
        Thread t2 = new Thread(() -> s2.test2(s1));

        t1.start();
        t2.start();
    }
}
