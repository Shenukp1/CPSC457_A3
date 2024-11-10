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
  


    
    private volatile int[] flags;//contians the flags it self and others

    private int n; //each process will know how many processes are created

    private CriticalSection cs;

    private Thread dsmThread;
    

    public Processes(int id,DSM dsm, TokenRing tokenRing, CriticalSection cs, int[] flags, int n){
        this.id = id;
        this.dsm = dsm;
        this.dsmThread = new Thread(dsm);
        this.flags = flags;
        this.n = n;// number of processes there are
        this.cs = cs;
        


    }


    /*
     * What does this thread do:
     * 
     */
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) { 
            System.out.println("Processor " + id + " works");
            iWantToEnterCS();
            System.out.println("Processor " + id + " is in CS.");
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            exitCS();
            System.out.println("Processor " + id + " has left CS.");
        }
    }

    public void iWantToEnterCS() {
        System.out.println("Processes: Processor " + id + " want to enter CS.");
        for (int k = 0; k < n - 1; k++) {
            flags[id] = k; //flag[i] = k, says process id want to enter CS at level k
            dsm.store(k, id); // turn[k] = i, says that it is id turn at level k 
            System.out.println("Processes: we have done storing!");
            
            while (checkAboveLevel(k) && dsm.load(k) == id) {
                Thread.yield();
                //System.out.println("Processes: Processor " + id + " is stuck!"); 
            }
            System.out.println("Processes: Freedom for "+ id);
        }
        cs.enter(id); 
    }

    public void exitCS() {
        cs.exit(id); 
        flags[id] = -1; 
    }

    private boolean checkAboveLevel(int level) {
        for (int j = 0; j < n; j++) {
            System.out.println("Processes: ID: "+id);
            System.out.println("Processes: Flag: "+flags[j]);
            if ((j != id) && (flags[j] > level)) {

                System.out.println("Processes: id: " + id + " j: "+j);
                return true;
            }
        }
        System.out.println("Processes: Processor " + id + " level <");
        return false;
    }

    public void startDsmThread() {
        dsmThread.start();
        dsm.startBroadcastThread();
    }
    public void joinDsmThread() throws InterruptedException {
		dsmThread.join();
        dsm.joinBroadcastThread();
        
	}



    //gets the id of the processor
    public int getID(){
        return id;
    }

    //sets the id of the processor
    public void setID(int change){
        id = change;
    }


	








}