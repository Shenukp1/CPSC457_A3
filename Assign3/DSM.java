import java.util.HashMap;
import java.util.Map;

public class DSM {
    private final Map<String, Integer> memory = new HashMap<>();

    // Synchronized method to load a value from DSM
    public synchronized int load(String key) {
        return memory.getOrDefault(key, -1); // Default value if key is not found
    }

    // Synchronized method to store a value in DSM
    public synchronized void store(String key, int value) {
        memory.put(key, value);
    }
}
