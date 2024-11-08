JAVA FILES AND THERE PURPOSE:
    Main.java
        Purpose: 
            1. Intiate classes such a Proccess, DSM, Token, TokenRingAgent.

    Processes.java
        Purpose:
            1. Sets its own flag[i] = to the level it is wants to compete at.
                1.1. it also reads all the other flag of the processes to chec
            2. change its turn to say it wants to compete at the level it is currently at
                2.1 IF NOT there turn, then processes will not do NOP
                2.2 IF there turn, then process will do NOP
                    2.2.1 will break out of loop when the processes they were against turns its flag false
                    
            
    DSM.java
       Purpose: 
    LocalMemory.java
        Purpose:
    BroadcastAgent.java
        Purpose:
    BroadcastSystem.java
        Purpose:
    Token.java
        Purpose:
    TokenRingAgent.java
        Purpose:




MAIN THOUGHT PROCESSES
    1. We create n amount of processes in the Main.java
        1.1. Each processes will have its own instance of a DSM - if you create 5 processes you need to create 5 DSM and put that into the processes
            1.1.2 Each DSM will create its own BroadcastAgent and LocalMemory
            1.1.3 Each BroadcastAgent will broadcast to the BroadcastSystem that it wants to change the localMemory
                  of all the other processes
            1.1.4 sending of changing LocalMemory of all the processes can only happen if the processes has a
                  a Token
            1.1.5 Each BroadcastAgent also recieves changes from the BroadcastSystem
            1.1.6 Changes recieved from BroadcastAgent will be sent to all the other BroadcastAgent to apply
                  changes to localMemory of processes

        1.2. Each Proccess will be able to read if the other processes can enter(shared array)


