public class TokenRingAgent implements Runnable {
    private int id;
    private TokenRing tokenRing;
    private TokenDSM tokenDSM;
    private boolean hasToken;
    private boolean isDone;

    public TokenRingAgent(int id, TokenRing tokenRing, TokenDSM tokenDSM) {
        this.id = id;
        this.tokenRing = tokenRing;
        this.tokenDSM = tokenDSM;
        this.hasToken = false;
        this.isDone = false;
    }

    public int getId() {
        return id;
    }

    // Method to receive the token
    public synchronized void receiveToken() {
        this.hasToken = true;
        notify();  // Wake up the thread to enter the critical section
    }

    // Method to pass the token to the next agent
    public synchronized void passTokenToSuccessor() {
        this.hasToken = false;
        tokenRing.passTokenToNextAgent();  // Pass the token to the next agent in the ring
        notifyAll();  // Notify all waiting agents (threads) that the token has been passed
    }

    public boolean hasToken() {
        return hasToken;
    }

    @Override
    public void run() {
        while (!isDone) {
            // Wait for the token
            synchronized (this) {
                while (!hasToken) {
                    try {
                        wait();  // Wait until the token is received
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

            // Enter the critical section
            System.out.println("Processor " + id + " has the token and is entering the critical section.");
            tokenDSM.store("var" + id, "value" + id, this);  // Pass the current agent to the store method

            // Exit the critical section
            System.out.println("Processor " + id + " is exiting the critical section.");
            isDone = true;

            // Pass the token to the next agent in the ring
            passTokenToSuccessor();
        }
    }
}
