import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher extends Thread {
    private Semaphore semaphore;
    private ReentrantLock leftChopstick, rightChopstick;
    private String name;

    public Philosopher(String name, Semaphore semaphore, ReentrantLock leftChopstick, ReentrantLock rightChopstick) {
        this.name = name;
        this.semaphore = semaphore;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    public void run() {
        try {
            while (true) {
                think();
                if (semaphore.tryAcquire()) {
                    if (leftChopstick.tryLock()) {
                        if (rightChopstick.tryLock()) {
                            eat();
                            rightChopstick.unlock();
                        }
                        leftChopstick.unlock();
                    }
                    semaphore.release();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void think() throws InterruptedException {
        System.out.println(name + " is thinking");
        Thread.sleep(1000);
    }

    private void eat() throws InterruptedException {
        System.out.println(name + " is eating");
        Thread.sleep(1000);
    }
}

public class DinningPhilospher {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        ReentrantLock[] chopsticks = new ReentrantLock[5];
        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new ReentrantLock();
        }
        Philosopher[] philosophers = new Philosopher[5];
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new Philosopher("Philosopher " + (i + 1), semaphore, chopsticks[i], chopsticks[(i + 1) % 5]);
            philosophers[i].start();
        }
    }
}
