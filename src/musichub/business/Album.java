package musichub.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import musichub.util.AudioToXML;

public class Album implements AudioToXML{ 
    //TODO: attributes

    public Album(){

    }

    //TODO: implement constructor with attributes

    //TODO: implement toString method
    public String toString(){
        return "";
    }

    public void load(Map<String, List<String>> attributes){
        //TODO: implement load method
    }

    public Map<String, List<String>> save(){
        HashMap<String, List<String>> attributes = new HashMap<>();

        //TODO: implement save method

        return attributes;
    }

    //TODO: implement all other methods
    
}
