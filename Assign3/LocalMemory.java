import java.util.HashMap;

public class LocalMemory{
    //TODO:might need to change the datatypes of the hashmap
    //basically we are saying variable i = value.
    //we change variable i
    private HashMap<String, Integer> leMemory = new HashMap<>();

    //we need to load(x)
    public int load(String variable){
        //if the variable is not found, we send a 0 for now
        //TODO:maybe not allow to load
        return leMemory.getOrDefault(variable,0);
    }


    //we need to store(x,v) - store value in x
    public void store(String variable, int value){
        leMemory.put(variable,value);
    }


}