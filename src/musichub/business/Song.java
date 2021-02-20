package musichub.business;

import java.util.Map;

import musichub.util.IAudioToXML;

import java.util.HashMap;
import java.util.List;

/**
 * Represents a song
 * @see AudioBook
 * @see AudioElement
 * @see Genres
 * @author Thomas Archambeau, Eléonore Vaissaire
 */
public class Song extends AudioElement implements Comparable<Song>{
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
     * @param content the file associated to this song
     */
    public Song(String artist, Genres gen, String title, int length,  String content){
        super(title, length, content);
        this.artist = artist;
        this.gen = gen;
    }

    /**
     * Copy constructor
     * @param other another song
     */
    public Song(Song other){
        super(other);
        this.artist = other.artist;
        this.gen = other.gen;

    }


    /**
     * Compares a song by Genre
     * @return the comparison between the genres, based on their representative strings
     */
    public int compareTo(Song song) {
        return this.gen.toString().compareTo(song.gen.toString());
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

    /**
     * Returns the total length in seconds
     * @return the total length in seconds
     */
    public int getLength(){
        return this.length;
    }

    /**
     * Returns a complete string with the attributes of the song
     * @return a complete string with the attributes of the song
     */
    public String toString(){
        return artist + ", " + gen + ", " + title + ", " + length + "s, " + content + "; ";

    }

    public Map<String, List<String>> save(){
        
        HashMap<String, List<String>> attributes = new HashMap<>();

        attributes.put("Artist", IAudioToXML.toList(artist));
        attributes.put("Genre", IAudioToXML.toList(gen.toString()));
        attributes.put("Title", IAudioToXML.toList(title));
        attributes.put("Length", IAudioToXML.toList(Integer.toString(length)));
        attributes.put("ID", IAudioToXML.toList(Integer.toString(id)));
        attributes.put("Content", IAudioToXML.toList(content));
        

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
        catch(IndexOutOfBoundsException | NullPointerException | NumberFormatException e){
            e.printStackTrace();
        }

    }

}
