package musichub.business;

import musichub.main.Application;
import musichub.util.AudioToXML;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Playlist implements AudioToXML{
    
    private String name;
    private int id;

    private List<AudioElement> list;

    //this is mandatory for the AudioToXML interface to work correctly
    public Playlist(){
    }

    public Playlist(String name, int id){
        this.name = name;
        this.id = id;

        list = new LinkedList<>();
    }

    public Map<String, List<String>> save(){
        HashMap<String, List<String>> attributes = new HashMap<>();

        attributes.put("Name", AudioToXML.toList(name));
        attributes.put("ID", AudioToXML.toList(Integer.toString(id)));

        LinkedList<String> idList = new LinkedList<>();

        for(AudioElement elt : list){
            idList.add(Integer.toString(elt.id));
        }
        attributes.put("Element", idList);

        return attributes;
    }


    public void load(Map<String, List<String>> attributes){
        try{
            name = attributes.get("Name").get(0);
            id = Integer.parseInt(attributes.get("ID").get(0));

            list = new LinkedList<>();

            List<String> idList = attributes.get("Element");
            for(String id : idList){
                AudioElement elt = Application.getElementWithID(Integer.parseInt(id));
                list.add(elt);
            }
        }
        catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        catch(NumberFormatException e){
            e.printStackTrace();
        }
        catch(NullPointerException e){
            //just ignore
        }
        catch(ElementNotFoundException e){
            e.printStackTrace();
        }
    }

    public String toString(){
        return "Playlist "+ name + ", id "+ id;
    }

    public void add(Song elt){

        if(elt == null) System.out.println("Element is null");
        try{
            list.add(elt);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<AudioElement> getElementsList(){
        return list;
    }

    /**
     * Returns the first playlist having a name passed as parameter in a list
     * @param title name to search for
     * @param list the list to search in
     * @return the element if found
     * @throws ElementNotFoundException if the playlist with given name was not found in the list
     */
    public static Playlist getPlaylistWithTitle(String name, List<Playlist> list) throws ElementNotFoundException{
        for(Playlist elt : list){
            if(elt.name.equals(name)) return elt;
        }

        throw new ElementNotFoundException("Unable to find Playlist with name: " + name + " in the list given");
    }

}
