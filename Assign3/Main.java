/*
Name:Shenuk Perera
UCID:30086618
Name:
UCID:
Class purpose: 
*/


public class Main {




    public static void main(String[] args) {


        

        int numProccesses = 5;//allows you to change how many processes are created
        
        Processes[] processesArray = createDsmAndProcesses(numProccesses);//what creates the equal processor to dsms

        for (int i = 0; i < processesArray.length; i++) {
            Processes process = processesArray[i];
            new Thread(process).start();
            
            
        }
        
        




       
    }






    public static void critical(Processes p) {




        System.out.println("Processes["+p.getID()+"] enters CS");
        try {
             Thread.sleep(1000);
        }
        catch (InterruptedException e) {}
        System.out.println("Processes["+p.getID()+"] exits CS");
    }



     /*
        function create a dsm equal to the amount of processors/processes
        numEqual - number of processes you want to create
     */
    public static Processes[] createDsmAndProcesses(int numProccesses) {

        BroadcastSystem broadcastSystem = new BroadcastSystem();//create the BroadcastSystem for all the dsms
        //create a TokenRing for all the proccessors
        Token token = new Token(30086618);
        TokenRing tokenRing = new TokenRing(token);

        CriticalSection cs = new CriticalSection();
        
        int[] flags = new int[numProccesses];//create an array to hold the flags of processes created 


        //assuming every processor is made in order of 0 to n-1
        for(int i=0;i<numProccesses;i++){
            flags[i]= -1;//every flag starts as false
        }
        DSM[] dsmArray = new DSM[numProccesses];//dsm being created
        for (int i = 0; i < numProccesses; i++) {
            dsmArray[i] = new DSM(broadcastSystem);//creating a dsm with the same broadcastSystem 
        }

        //creating the processes and adding the dsms to them
        Processes[] processesArray = new Processes[numProccesses];
        for (int i = 0; i < numProccesses; i++) {
            processesArray[i] = new Processes(i,dsmArray[i],tokenRing,cs,flags,numProccesses);
        }

        //then returning to access all the process 
        return processesArray;
    }

}