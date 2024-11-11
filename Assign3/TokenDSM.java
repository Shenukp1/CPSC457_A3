import java.util.HashMap;
import java.util.Map;

public class TokenDSM {
    private Map<String, String> memory = new HashMap<>();

    // Synchronized store method, which waits until the calling agent has the token
    public synchronized void store(String var, String value, TokenRingAgent agent) {
        // Wait until the agent has the token
        while (!agent.hasToken()) {  // Check if the agent has the token
            try {
                wait();  // Wait for the token
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // Once the agent has the token, store the value in the memory
        memory.put(var, value);
        System.out.println("Stored " + value + " in " + var);
    }

    // Load a value from the memory
    public synchronized String load(String var) {
        return memory.get(var);
    }

    // Notify waiting threads (agents) that the token has been passed
    public synchronized void notifyTokenPassed() {
        notifyAll();
    }
}
