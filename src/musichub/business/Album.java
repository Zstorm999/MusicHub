package musichub.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import musichub.main.Application;
import musichub.util.AudioToXML;

public class Album implements AudioToXML{ 
    private String title;
    private String artist;

    private int id;
    private Date date;

    private List<Song> list;

    private SimpleDateFormat dateFormat;


    public Album(){
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        list = new LinkedList<>();
    }

    public Album(String title, String artist, int id, Date date){
        this();

        this.title = title;
        this.artist = artist;
        this.id = id;
        this.date = date;

    }

    public int getLength(){
        int length = 0;
        for(Song elt : list){
            length += elt.getLength();
        }

        return length;
    }

    public void add(Song song){
        list.add(song);
    }

    public String toString(){
        return "Album: " + title + ", by: " + artist + ", published on " + date + "; total length: " + getLength() + "s.";
    }


    public void load(Map<String, List<String>> attributes){
        try{
            title = attributes.get("Title").get(0);
            artist = attributes.get("Artist").get(0);
            id = Integer.parseInt(attributes.get("ID").get(0));
            date = dateFormat.parse(attributes.get("Date").get(0));

            for (String id : attributes.get("Element")) {
                list.add(Application.getSongWithID(Integer.parseInt(id)));                
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
        catch(ParseException e){
            e.printStackTrace();
        }
        catch(ElementNotFoundException e){
            e.printStackTrace();
        }
    }

    public Map<String, List<String>> save(){
        HashMap<String, List<String>> attributes = new HashMap<>();

        attributes.put("Title", AudioToXML.toList(title));
        attributes.put("Artist", AudioToXML.toList(artist));
        attributes.put("ID", AudioToXML.toList(Integer.toString(id)));
        attributes.put("Date", AudioToXML.toList(dateFormat.format(date)));

        LinkedList<String> idList = new LinkedList<>();

        for(Song elt : list){
            idList.add(Integer.toString(elt.id));
        }

        attributes.put("Element", idList);

        return attributes;
    }
    
    /**
     * Returns the first album having a title passed as parameter in a list
     * @param title title to search for
     * @param list the list to search in
     * @return the album if found
     * @throws ElementNotFoundException if the album with given name was not found in the list
     */
    public static Album getAlbumWithTitle(String title, List<Album> list) throws ElementNotFoundException{
        for (Album album : list) {
            if(album.title.equals(title)) return album;
        }

        throw new ElementNotFoundException("Unable to find album with title: "+ title);
    }

}
