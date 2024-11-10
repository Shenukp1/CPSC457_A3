/*
Name:Shenuk Perera
UCID:30086618
Name:
UCID:
Class purpose: 

*/

import java.util.List;

public class Processes implements Runnable {

    private int id;//id of the processes

    //classes the process creates
    private DSM dsm;
    private TokenRing tokenRing;
    private TokenRingAgent tokenRingAgent;

    
    private int[] flags;//contians the flags it self and others

    private int[] turn; // only can check its own turn

    private int n; //each process will know how many processes are created

    public Processes(int id,DSM dsm, TokenRing tokenRing, int[] flags, int n){
        this.id = id;
        this.dsm = dsm;
        this.tokenRing = tokenRing;
        this.n = n;
        this.flags = flags;

        this.turn = new int[n-2];//turn for each level
        
        tokenRingAgent = new TokenRingAgent(tokenRing);//creating a tokenRingAgent

        
        dsm.setProcessorID(id);//dsm now has the id of the processor

    }


    /*
     * What does this thread do:
     *  1. waits for the token
     *  2. send a dsm operation(turn var change?)
     *  3. releases the token
     */
    @Override
    public void run(){

    }
    



    //gets the id of the processor
    public int getID(){
        return id;
    }

    //sets the id of the processor
    public void setID(int change){
        id = change;
    }

    //OTHER FUNCTIONS USED BELOW THIS POINT

    //converting a int to a string
    public String convert(int num){
        String str = String.valueOf(num);
        return str;
        
    }








}