/*
Name:Shenuk Perera
UCID:30086618
Name:
UCID:
Class purpose:
*/

public class DSM implements Runnable{


    private LocalMemory localMemory;
    private BroadcastAgent broadcastAgent;
    private BroadcastSystem broadcastSystem;//reference created to hold the address of the main broadcastsystem in Main to use it
    




    //we want to create a broadcastagen and a local memory for the process
    public DSM(BroadcastSystem broadcastSystem){
        this.localMemory = new LocalMemory();
        this.broadcastAgent = new BroadcastAgent(this,broadcastSystem,localMemory);
        this.broadcastSystem = broadcastSystem;
 
    }


    /*
     * What does this thread do: 
     * 
     */
    @Override
    public void run() {
        
    }



    //this is to load from Local memory
    public synchronized int load(int variable){
        System.out.println("DSM of Processor:" + dsmP_id + " read value: " + variable);//to check if the dsm is working with processor
        return localMemory.load(variable);
    }


   
    public synchronized void store(int variable, int value){

        localMemory.store(variable,value);

    }





}