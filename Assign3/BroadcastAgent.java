public class BroadcastAgent implements Runnable {
    private DSM dsm;
    private int id;

    // Constructor now accepts an id and DSM instance
    public BroadcastAgent(int id, DSM dsm) {
        this.id = id;
        this.dsm = dsm;
    }

    // Method to receive updates from the BroadcastSystem
    public void receiveUpdate(String key, int value) {
        System.out.println("BroadcastAgent " + id + ": Received " + key + " = " + value);
        // Store the received value in DSM
        dsm.store(key, value);
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Indicate that the agent wants to enter its critical section
                dsm.store("flag_" + id, 1);

                // Set the turn to the current agent, so others know it's their turn
                int currentTurn = dsm.load("turn");
                dsm.store("turn", id);  // Set the turn to the current agent

                // Wait until it's this agent's turn, and no other agent wants to enter
                while (dsm.load("flag_" + currentTurn) == 1 && dsm.load("turn") == id) {
                    Thread.sleep(10); // Wait for the turn or flag to change
                }

                // Once the conditions are met, the agent enters the critical section
                System.out.println("BroadcastAgent " + id + " entering critical section.");
                
                // Simulate doing some work in the critical section
                Thread.sleep(100);  // Simulate the time spent in critical section

                // Leave the critical section and reset the flag
                dsm.store("flag_" + id, 0);
                System.out.println("BroadcastAgent " + id + " leaving critical section.");

                // After completing critical section work, the agent can broadcast
                broadcast();

                // Simulate a small delay to prevent the agent from continuously spinning
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Method to broadcast data (placeholder for broadcasting logic)
    public void broadcast() {
        // Simulate broadcasting (you can extend this with your broadcasting logic)
        System.out.println("BroadcastAgent " + id + " broadcasting...");
    }
}
