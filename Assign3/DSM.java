/*
Name:Shenuk Perera
UCID:30086618
Name:
UCID:
Class purpose: to constantly update local memory of processes it reciveves from broadcast messages

    1. to update the the local memory of the process
    2. to load from the local memory of the process
    3. to broadcast A CHANGE(singular) to all the processors if the process has a token
        a) does this by sending a broadcast to the BroadCastSystem
            1. system takes the broadCastAgents of other processors and makes them store that data in those processors

    Figure out:
        1. do we need the process to be constantly loading and storing something? - YES, i think
*/

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
