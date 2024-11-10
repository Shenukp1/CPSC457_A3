import java.util.concurrent.atomic.AtomicBoolean;

public class Processor implements Runnable {
    private final DSM dsm;
    private final int id;
    private final BroadcastSystem broadcastSystem;
    private final BroadcastAgent agent;
    private final AtomicBoolean[] flag;
    private static final int N = 10;  // Number of processors

    public Processor(int id, BroadcastSystem broadcastSystem) {
        this.id = id;
        this.dsm = new DSM();
        this.broadcastSystem = broadcastSystem;
        this.agent = new BroadcastAgent(id, dsm);
        this.flag = new AtomicBoolean[N];
        for (int i = 0; i < N; i++) {
            flag[i] = new AtomicBoolean(false);
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {  // Run for 5 rounds
                enterCriticalSection();
                System.out.println("Processor " + id + " is in the critical section.");
                Thread.sleep(1000);  // Simulate work in critical section
                exitCriticalSection();
            }
        } catch (InterruptedException e) {
            System.out.println("Processor " + id + " interrupted.");
        }
    }

    private void enterCriticalSection() throws InterruptedException {
        flag[id].set(true);  // Set the flag to indicate interest in entering the critical section
        System.out.println("Processor " + id + " is trying to enter the critical section.");
        dsm.store("flag_" + id, 1);  // Store flag locally and broadcast it
        broadcastSystem.broadcastUpdate("flag_" + id, 1);

        // Set the turn and broadcast it
        for (int j = 0; j < N; j++) {
            if (j != id) {
                dsm.store("turn", j);  // Set the turn and broadcast
                broadcastSystem.broadcastUpdate("turn", j);
            }
        }

        boolean myTurn = false;
        while (!myTurn) {
            boolean otherInCritical = false;
            for (int j = 0; j < N; j++) {
                if (j != id && flag[j].get()) {
                    otherInCritical = true;  // If any other processor is trying to enter
                    break;
                }
            }

            // Check if it's this processor's turn and no other processor is in the critical section
            if (dsm.load("turn") == id && !otherInCritical) {
                myTurn = true;
            } else {
                System.out.println("Processor " + id + " is waiting for its turn.");
                // Sleep for a random duration to simulate message propagation delay
                Thread.sleep(100 + (int)(Math.random() * 200));
            }
        }

        // Successfully entered the critical section
        System.out.println("Processor " + id + " has entered the critical section.");
    }

    private void exitCriticalSection() {
        flag[id].set(false);  // Reset flag
        dsm.store("flag_" + id, 0);
        broadcastSystem.broadcastUpdate("flag_" + id, 0);

        System.out.println("Processor " + id + " is exiting the critical section.");
    }
}
