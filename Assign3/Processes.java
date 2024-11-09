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

    //contians the flags
    private boolean[] flags;

    public Processes(int id,DSM dsm, TokenRing tokenRing, int[] flags){
        this.id = id;
        this.dsm = dsm;
        this.tokenRing = tokenRing;

        //creating a tokenRingAgent
        tokenRingAgent = new TokenRingAgent(tokenRing);

        //dsm now has the id of the processor
        dsm.setProcessorID(id);

    }


    /*
    What this Thread does:
        1.Sets it own flag true to indicate it wants to enter CS
        2. write turn
     */
    @Override
    public void run(){

        System.out.println("Processor " + id + " is running.");

       
        
        
        //RACE CONDITION
        while(true){
            
         

        }
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