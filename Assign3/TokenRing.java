public class TokenRing {
    private TokenRingAgent[] agents;
    private int numProcessors;
    private int currentTokenHolder;

    public TokenRing(int numProcessors) {
        this.numProcessors = numProcessors;
        this.agents = new TokenRingAgent[numProcessors];
        this.currentTokenHolder = 0;  // Initially, processor 0 holds the token
    }

    public void addAgent(TokenRingAgent agent) {
        agents[agent.getId()] = agent;
    }

    public synchronized void passTokenToNextAgent() {
        // Pass the token to the next agent in the ring
        currentTokenHolder = (currentTokenHolder + 1) % numProcessors;
        agents[currentTokenHolder].receiveToken();  // Give the token to the next processor
    }

    public int getCurrentTokenHolder() {
        return currentTokenHolder;
    }
}
