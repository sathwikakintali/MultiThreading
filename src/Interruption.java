class WorkerThreadd extends Thread {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Simulate some work by having the thread sleep
                Thread.sleep(1000);
                System.out.println("Worker thread is doing work...");
            } catch (InterruptedException e) {
                // If the thread is interrupted during sleep, it will throw an InterruptedException
                // We handle this exception and break out of the loop, effectively ending the thread's run method
                System.out.println("Worker thread was interrupted during sleep, terminating...");
                break;
            }
        }
    }
}

public class Interruption {
    public static void main(String[] args) throws InterruptedException {
        WorkerThreadd workerThread = new WorkerThreadd();
        workerThread.start();

        // Let the worker thread do some work
        Thread.sleep(5000);

        // Interrupt the worker thread, this will cause an InterruptedException to be thrown in the worker thread
        workerThread.interrupt();
    }
}
