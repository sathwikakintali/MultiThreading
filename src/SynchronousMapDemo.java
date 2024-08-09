import java.util.concurrent.ConcurrentHashMap;

public class SynchronousMapDemo {
    private ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    public synchronized void put(String key, String value) {
        map.put(key, value);
    }

    public synchronized String get(String key) {
        return map.get(key);
    }

    public static void main(String[] args) {
        SynchronousMapDemo demo = new SynchronousMapDemo();

        // Put some values in the map
        demo.put("key1", "value1");
        demo.put("key2", "value2");

        // Get the values
        System.out.println(demo.get("key1"));  // Output: value1
        System.out.println(demo.get("key2"));  // Output: value2
    }
}
