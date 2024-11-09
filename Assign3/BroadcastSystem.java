/*
Name:Shenuk Perera
UCID:30086618
Name:
UCID:
Class purpose:
*/

import java.util.ArrayList;
import java.util.List;

public class BroadcastSystem implements Runnable {
    private List<BroadcastAgent> broadcastAgents;//list of all the broadCastAgents that are in the processors


    //constructor which creates the array or the "network for the broadcast"
    public BroadcastSystem(){
        this.broadcastAgents = new ArrayList<>();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    public void broadcastUpdate(BroadcastAgent broadcaster, String variable, int value){

        //need delay for receiving message on this side? or broadCastAgent side?

        System.out.println("Broadcasting update: " + variable + " set to " + value);

        //delay for sending messages
        try{
           Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //now we send all the updates to the broadcastAgents
        for(BroadcastAgent broadcastAgent : broadcastAgents){
            //want to make sure we dont we dont send the broad
            if(broadcastAgent !=broadcaster){
                broadcastAgent.receive(variable, value);
            }
        }
        


    }

    //A method to add the broadCastAgent to the BroadcastSystem
    public void addBroadcastAgent(BroadcastAgent bca){
        broadcastAgents.add(bca);

    }

    



}