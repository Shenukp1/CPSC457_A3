/*
Name:Shenuk Perera
UCID:30086618
Name:
UCID:
Class purpose: 
*/


public class Main {


    public static void main(String[] args) {



        //create the BroadcastSystem for all the dsms
        BroadcastSystem broadcastSystem = new BroadcastSystem();


        //create a TokenRing for all the proccessors
        Token token = new Token(30086618);
        TokenRing tokenRing = new TokenRing(token);

        
        //what creates the equal processor to dsms
        Processes[] processesArray = createDsmAndProcesses(5,broadcastSystem,tokenRing);

        


       
    }





     /*
        function create a dsm equal to the amount of processors/processes
        numEqual - number of processes you want to create

        TODO:ajust for tokens
     */
    public static Processes[] createDsmAndProcesses(int equalPtoD, BroadcastSystem broadcastSystem, TokenRing tokenRing) {

        

        int[] flags = new int[equalPtoD];//create an array to hold the flags of processes created 


        //assuming every processor is made in order of 0 to n-1
        for(int i=0;i<equalPtoD;i++){
            flags[i]= -1;//every flag starts as false
        }
        DSM[] dsmArray = new DSM[equalPtoD];//dsm being created
        for (int i = 0; i < equalPtoD; i++) {
            dsmArray[i] = new DSM(broadcastSystem);//creating a dsm with the same broadcastSystem 
        }

        //creating the processes and adding the dsms to them
        Processes[] processesArray = new Processes[equalPtoD];
        for (int i = 0; i < equalPtoD; i++) {
            processesArray[i] = new Processes(i, dsmArray[i], tokenRing,flags);
        }

        //then returning to access all the process 
        return processesArray;
    }

}