/*
Name:Shenuk Perera
UCID:30086618
Name:
UCID:
Class purpose:
*/

import java.util.ArrayList;
import java.util.List;

public class TokenRing{

//creating that ring
private List<TokenRingAgent> tokenRingAgents;


//we need to know which token ring agent has the token
private int tokenHolderIndex = 0;


//Token being create
Token token;
 



//constructor
public TokenRing(Token token){
    this.token = token;
    this.tokenRingAgents = new ArrayList<>();//creating a "ring" using a list
}

//registering tokenRingAgent function with the TokenRing
public void registerAgent(TokenRingAgent tra){
    tokenRingAgents.add(tra);
}

//the token ring is responsible for passing the ring around
public void startPassingToken(){
    //initalizing a token
}






}