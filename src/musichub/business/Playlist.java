package musichub.business;

import musichub.main.Application;
import musichub.util.AudioToXML;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a pllaylist, gathering a list of songs or audio books
 * @see Song
 * @see AudioBook
 * @see AudioElement
 * @author Thomass Archambeau, Eléonore Vaissaire
 */
public class Playlist implements AudioToXML{
    
    private String name;
    private int id;

    private List<AudioElement> list;

    /**
     * Empty constructor, mandatory for the AudioToXML interface to work correctly
     */
    public Playlist(){
        list = new LinkedList<>();
    }

    /**
     * Creates a new playlist
     * @param name name
     * @param id id
     */
    public Playlist(String name, int id){
        this.name = name;
        this.id = id;

        list = new LinkedList<>();
    }

    /**
     * Returns a list of AudioElement
     * @return a list of AudioElement
     */
    public List<AudioElement> getElementsList(){
        return list;
    }

    /**
     * Returns the name of the playlist
     * @return the name of the playlist
     */
    public String getName(){ return name;}

    /**
     * Returns the playlist's id
     * @return the playlist's id
     */
    public int getId(){ return id;}

    /**
     * Add a new audio element to the playlist
     * @param elt the element to add
     */
    public void add(AudioElement elt){
        try{
            list.add(elt);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Returns a complete string with the attributes of the playlist
     * @return a complete string with the attributes of the playlist
     */
    public String toString(){
        return "Playlist : "+ name + ", Elements " + list.toString(); }

    public void load(Map<String, List<String>> attributes){
        try{
            name = attributes.get("Name").get(0);
            id = Integer.parseInt(attributes.get("ID").get(0));

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

    

    /**
     * Returns the first playlist having a name passed as parameter in a list
     * @param name name to search for
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
