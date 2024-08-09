class Worker {
    private String name;
    private boolean active;

    public Worker(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public synchronized void work(Worker otherWorker) {
        while (active) {
            if (otherWorker.isActive()) {
                System.out.println(getName() + " : handing over the resource to the worker: "+otherWorker.getName());
                active = false;
                otherWorker.active = true;
            }
            else {
                System.out.println(getName() + ": working on the common resource");
                active = true;
                otherWorker.active = false;
            }
        }
    }
}

public class LivelockExample {
    public static void main(String[] args) {
        final Worker worker1 = new Worker("Worker 1 ", true);
        final Worker worker2 = new Worker("Worker 2", false);

        new Thread(() -> worker1.work(worker2)).start();
        new Thread(() -> worker2.work(worker1)).start();
    }
}
