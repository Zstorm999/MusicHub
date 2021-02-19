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

/**
 * Represents an album. <br>
 * An album contains songs.
 * @see Song
 * @see Genres
 * @author Thomas Archambeau, Eléonore Vaissaire
 */
public class Album implements AudioToXML, Comparable<Album>, IHasAnID{
    private String title;
    private String artist;

    private int id = -1;
    private Date date;

    //TODO: convert this to a list of integers (referring to the id)
    private List<Song> list;

    private SimpleDateFormat dateFormat;


    /**
     * A constructor needed for the date and its format
     */
    public Album(){
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        list = new LinkedList<>();
    }

    /**
     * Creates a new album
     * @param title the title
     * @param artist the artist
     * @param date the date of creation
     */
    public Album(String title, String artist, Date date){
        this();

        this.title = title;
        this.artist = artist;
        this.date = date;

    }

    /**
     * Copy constructor
     * @param other another album
     */
    public Album(Album other){
        this();

        this.date = other.date;
        this.artist = other.artist;
        this.title = other.title;

        this.list = other.list;
    }


    /**
     * Compares the current album and another one, returns 1 or -1
     * @param album the album to compare
     * @return 1 if the album to compare is created before the current one, -1 otherwise
     */
    public int compareTo(Album album){
        if(this.date.after(album.date)){
            return -1;
        }
        else {
            return 1;
        }
    }

    /**
     * Returns the album's id
     * @return album's id
     */
    public int getID(){
        return id;
    }

    public void setID(int newID) throws IDAlreadySetException {

        if( id != -1){
            throw new IDAlreadySetException("This Album already has an ID! Why are you trying to resetting it ?");
        }

        this.id = newID;
    }

    /**
     * Returns the total length in seconds of the album
     * @return the total length in seconds of the album
     */
    public int getLength(){
        int length = 0;
        for(Song elt : list){
            length += elt.getLength();
        }

        return length;
    }

    /**
     * Returns the album's title
     * @return the alum's title
     */
    public String getTitle(){
        return title;
    }

    /**
     * Add a song to the album
     * @param song the song to add
     */
    public void add(Song song){
        list.add(song);
    }

    /**
     * Returns a complete string with all the attributes
     * @return a complete string with all the attributes
     */
    public String toString(){
        return "Album: " + title + ", by: " + artist + ", published on " + date + "; total length: " + getLength() + "s.";
    }

    /**
     * Returns a list of all the songs in the album
     * @return a list of all the songs in the album
     */
    public List<Song> getSongs(){
        return list;
    }

    /**
     * Returns the date of creation
     * @return the date of creation
     */
    public Date getDate(){
        return date;
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
        catch(IndexOutOfBoundsException | ElementNotFoundException | ParseException | NumberFormatException e){
            e.printStackTrace();
        } catch(NullPointerException e){
            //just ignore
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
