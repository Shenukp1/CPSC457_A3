/*
Name:Shenuk Perera
UCID:30086618
Name:
UCID:
Class purpose:
*/

public class DSM implements Runnable{


    private LocalMemory LocalMemory;
    private BroadcastAgent broadcastAgent;
    private BroadcastSystem broadcastSystem;//reference created to hold the address of the main broadcastsystem in Main to use it
    


    private int dsmP_id;

    //we want to create a broadcastagen and a local memory for the process
    public DSM(BroadcastSystem broadcastSystem){
        this.LocalMemory = new LocalMemory();
        this.broadcastAgent = new BroadcastAgent(this,broadcastSystem);
        this.broadcastSystem = broadcastSystem;
    }


    /*
     * What does this thread do: constantly taking input from a broadcast and writting to the processors local memory
     */
    @Override
    public void run() {
        
    }



    //this is to load from Local memory
    public synchronized int load(String variable){

        System.out.println("DSM of Processor:" + dsmP_id + " read value: " + variable);//to check if the dsm is working with processor

        return LocalMemory.load(variable);
    }


    //this is to store to local memory and
    public synchronized void store(String variable, int value){

        //always storing to the local memory of the process
        LocalMemory.store(variable,value);

        //then here we need to check if it has a token to broadcast that change
        
    }

    



     //allows the dsm to have the id of the processor
    public void setProcessorID(int id){
        dsmP_id = id;
    }

    public int getProccesorID(){
        return dsmP_id;
    }



}