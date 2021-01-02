package musichub.business;

import java.util.Map;

import musichub.util.AudioToXML;

import java.util.HashMap;
import java.util.List;

/**
 * Represents a song
 * @see AudioBook
 * @see AudioElement
 * @see Genres
 * @author Thomas Archambeau, Eléonore Vaissaire
 */
public class Song extends AudioElement{
    private String artist;
    private Genres gen;

    /**
     * Empty contructor, does nothing (but needed for XML storage)
     */
    public Song(){
        
    }

    /**
     * Creates a new song
     * @param artist the artist
     * @param gen the genre
     * @param title the title
     * @param length the length in seconds
     * @param id the id
     * @param content the file associated to this song
     */
    public Song(String artist, Genres gen, String title, int length, int id, String content){
        super(title, length, id, content);
        this.artist = artist;
        this.gen = gen;
    }

    /**
     * Returns the artist 
     * @return the artist
     */
    public String getArtist(){
        return artist;
    }

    /**
     * Returns the genre of the song
     * @return the genre of the song
     */
    public Genres getGen(){
        return gen;
    }

    public int getLength(){
        return this.length;
    }

    public String toString(){
        String result = null;
        result = artist + " " + gen + " " + title + " " + length + " " + id + " " + content;
        return result;
    }

    public Map<String, List<String>> save(){
        
        HashMap<String, List<String>> attributes = new HashMap<>();

        attributes.put("Artist", AudioToXML.toList(artist));
        attributes.put("Genre", AudioToXML.toList(gen.toString()));
        attributes.put("Title", AudioToXML.toList(title));
        attributes.put("Length", AudioToXML.toList(Integer.toString(length)));
        attributes.put("ID", AudioToXML.toList(Integer.toString(id)));
        attributes.put("Content", AudioToXML.toList(content));
        

        return attributes;
    }

    public void load(Map<String, List<String>> arguments){

        try{
            artist = arguments.get("Artist").get(0);
            gen = Genres.valueOf(arguments.get("Genre").get(0).toUpperCase().replaceAll("\\s", ""));
            title = arguments.get("Title").get(0);
            length = Integer.parseInt(arguments.get("Length").get(0));
            id = Integer.parseInt(arguments.get("ID").get(0));
            content = arguments.get("Content").get(0);

        }
        catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        catch(NumberFormatException e){
            e.printStackTrace();
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
        
    }

}
