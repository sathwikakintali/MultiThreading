import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // Create a CountDownLatch with a count of 3
        CountDownLatch latch = new CountDownLatch(3);

        // Create and start three threads
        new Thread(new Workker(latch), "Thread1").start();
        new Thread(new Workker(latch), "Thread2").start();
        new Thread(new Workker(latch), "Thread3").start();

        // Wait for all threads to finish
        latch.await();

        System.out.println("All threads have finished.");
    }
}

class Workker implements Runnable {
    private CountDownLatch latch;

    public Workker(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is running.");
            Thread.sleep(1000); // Simulate work with sleep
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Decrement the count of the latch
            latch.countDown();
        }
    }
}
