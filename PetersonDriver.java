public class PetersonDriver {
    public static void main(String[] args) {
        int numProcessors = 10;  // Set the number of processors

        // Initialize the BroadcastSystem with a new DSM
        DSM dsm = new DSM();
        BroadcastSystem broadcastSystem = new BroadcastSystem(dsm);

        // Initialize the flags and turn in the DSM
        for (int i = 0; i < numProcessors; i++) {
            dsm.store("flag_" + i, 0);  // Initially, no one is in the critical section
        }
        dsm.store("turn", 0);  // Initially, set the turn to processor 0

        // Create processors (agents) and add them to the BroadcastSystem
        BroadcastAgent[] agents = new BroadcastAgent[numProcessors];
        for (int i = 0; i < numProcessors; i++) {
            agents[i] = new BroadcastAgent(i, dsm);  // Pass id here
            broadcastSystem.addBroadcastAgent(agents[i]);  // Add the agent to the system
        }

        // Start each agent in a separate thread
        for (BroadcastAgent agent : agents) {
            new Thread(agent).start();
        }

        // Start the BroadcastSystem in its own thread
        new Thread(broadcastSystem).start();
    }
}
