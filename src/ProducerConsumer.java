import java.util.concurrent.Semaphore;
import java.util.LinkedList;
import java.util.Queue;

class Producer implements Runnable {
    private final Semaphore semaphore;
    private final Queue<Integer> queue;

    public Producer(Semaphore semaphore, Queue<Integer> queue) {
        this.semaphore = semaphore;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                semaphore.acquire();
                queue.add(i);
                System.out.println("Produced: " + i);
                semaphore.release();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private final Semaphore semaphore;
    private final Queue<Integer> queue;

    public Consumer(Semaphore semaphore, Queue<Integer> queue) {
        this.semaphore = semaphore;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                semaphore.acquire();
                System.out.println("Consumed: " + queue.remove());
                semaphore.release();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        Queue<Integer> queue = new LinkedList<>();

        Thread producerThread = new Thread(new Producer(semaphore, queue));
        Thread consumerThread = new Thread(new Consumer(semaphore, queue));

        producerThread.start();
        consumerThread.start();
    }
}
