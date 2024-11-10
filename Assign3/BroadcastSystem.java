import java.util.ArrayList;
import java.util.List;

public class BroadcastSystem implements Runnable {
    private DSM dsm;
    private List<BroadcastAgent> agents;

    public BroadcastSystem(DSM dsm) {
        this.dsm = dsm;
        this.agents = new ArrayList<>();
    }

    // Add a BroadcastAgent to the system
    public void addBroadcastAgent(BroadcastAgent agent) {
        agents.add(agent);
    }

    // Broadcast an update to all agents
    public synchronized void broadcastUpdate(String key, int value) {
        System.out.println("BroadcastSystem: Broadcasting update for " + key + " with value " + value);
        for (BroadcastAgent a : agents) {
            a.receiveUpdate(key, value); // Notify all agents
        }
    }

    @Override
    public void run() {
        while (true) {
            for (BroadcastAgent agent : agents) {
                agent.broadcast();
            }
            try {
                Thread.sleep(100); // Simulate delay between broadcasts
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
