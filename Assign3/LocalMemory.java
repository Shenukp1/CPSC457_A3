import java.util.HashMap;

public class LocalMemory {

    // Stores variables and their values
    private HashMap<String, Integer> memory = new HashMap<>();

    // Loads the value of a variable from memory. Returns 0 if variable is not found.
    public int load(String variable) {
        System.out.println("Loading value for " + variable + ": " + memory.getOrDefault(variable, 0));
        return memory.getOrDefault(variable, 0);
    }

    // Stores the value for a variable in memory
    public void store(String variable, int value) {
        System.out.println("Storing value for " + variable + ": " + value);
        memory.put(variable, value);
    }
}
