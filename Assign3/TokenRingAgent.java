public class TokenRingAgent implements Runnable {

    private int t_id;// unique identifier for the token


    /*
    false means the agent is not on the ring
    TODO:check if this is true ^
    */
    private boolean active = false;

    /*
    creating a reference to possibly use it more than once
        - having multiple token rings at once
    */
    TokenRing tokenRing; 

    private int p_id;//processes id

    private int pp_id; //processes predecssor(tokenRingAgent) id 

    private int ps_id; //processes succesor id


    private int token_id;//id of the token


    //the constructor
    public TokenRingAgent(TokenRing tokenRing){
        this.tokenRing = tokenRing;

    }

    //get the id of the process on the ring
    public int getPID(){
        if

    }

    @Override
    public void run(){
        //RACE CONDITION
        while(true){

        }
    }

    public void int recieveToken(){

    }

    public void sendToken(Token t)




}