import java.util.concurrent.CopyOnWriteArrayList;

public class SynchronousListDemo {
    private CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    public synchronized void add(String element) {
        list.add(element);
    }

    public synchronized String get(int index) {
        return list.get(index);
    }

    public static void main(String[] args) {
        SynchronousListDemo demo = new SynchronousListDemo();

        // Add some elements to the list
        demo.add("element1");
        demo.add("element2");

        // Get the elements
        System.out.println(demo.get(0));  // Output: element1
        System.out.println(demo.get(1));  // Output: element2
    }
}
