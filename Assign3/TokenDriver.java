public class TokenDriver {
    public static void main(String[] args) {
        int numProcessors = 10;  // Set the number of processors

        // Initialize the TokenRing and TokenDSM
        TokenDSM tokenDSM = new TokenDSM();
        TokenRing tokenRing = new TokenRing(numProcessors);

        // Create processors (agents) and add them to the TokenRing
        TokenRingAgent[] agents = new TokenRingAgent[numProcessors];
        for (int i = 0; i < numProcessors; i++) {
            agents[i] = new TokenRingAgent(i, tokenRing, tokenDSM);
            tokenRing.addAgent(agents[i]);  // Add the agent to the ring
        }

        // Give the first agent the token
        agents[0].receiveToken();

        // Start each agent in a separate thread
        for (TokenRingAgent agent : agents) {
            new Thread(agent).start();
        }
    }
}
