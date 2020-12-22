package musichub.business;

import musichub.util.AudioToXML;

import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Playlist implements AudioToXML{
    
    private String name;
    private int id;

    //this is mandatory for the AudioToXML interface to work correctly
    public Playlist(){

    }

    public Playlist(String name, int id){
        this.name = name;
        this.id = id;
    }

    public Map<String, List<String>> save(){
        HashMap<String, List<String>> attributes = new HashMap<>();

        attributes.put("name", Arrays.asList(new String[] {name} ));
        attributes.put("id", Arrays.asList(new String[] {Integer.toString(id)} ));

        return attributes;
    }


    public void load(Map<String, List<String>> attributes){
        try{
            name = attributes.get("name").get(0);
            id = Integer.parseInt(attributes.get("id").get(0));
        }
        catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }

        System.out.println(this);
    }

    public String toString(){
        return "Playlist "+ name + ", id "+ id;
    }

}
