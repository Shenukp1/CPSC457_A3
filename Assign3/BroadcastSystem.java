/*
Name:Shenuk Perera
UCID:30086618
Name:
UCID:
Class purpose:to make all the DSM update the localMemory of all the processors from a single broadcast recived
    1.recieves a broadcast from a process(that has a token) broadCastAgent
    2.then takes that broadcast and send it back to all the broadCastAgents in the system,
    to tell the DSM to modify all the local memory of the processors
*/
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
