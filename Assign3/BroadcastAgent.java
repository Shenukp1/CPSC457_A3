/*
Name:Shenuk Perera
UCID:30086618
Name:
UCID:
Class purpose: 
*/


public class BroadcastAgent implements Runnable{

    private DSM dsm;//so it has access to the DSM that created it
    private BroadcastSystem broadcastSystem;//it has access to the BCS(broadCastSystem) that is put into

    private LocalMemory localMemory;
    
    private int b_id;//TODO: use later to identify the broadcaster?

    

    //constructor
    public BroadcastAgent(DSM dsm, BroadcastSystem broadcastSystem,LocalMemory localMemory){
        this.dsm = dsm;
        this.broadcastSystem = broadcastSystem;
        this.localMemory = localMemory;
        broadcastSystem.addBroadcastAgent(this);//the BroadCastAgent that was made now is apart of the BroadCastSystem
        
        this.b_id = dsm.getProccesorID();//testing if i can access the ID of processor
    }


    //In the thread, the broadcastAgent is constantly checking for broadcast from broadcastSystem
    @Override
    public void run() {

        while(true){

        }
    
    }


    /*
     * Function used by DSM to update all proccess 
     */
    public void broadcast(int variable, int value){

        System.out.println("BroadcastAgent of Proccesor:"+b_id+" Sending update for variable " + variable + " with value " + value);

        //random delay for sending a broadcast to the broadcastsystem
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //this - means that it take a ref of the one that sends it so we dont send it back to the same broadcaster
        broadcastSystem.broadcastUpdate(this, variable, value);
    }




    //Receive the broadcast and update local memory
    public void receive(int variable, int value) {
        System.out.println("BroadcastAgent: Received broadcast for variable " + variable + " with value " + value);
        
    }



   
    
}