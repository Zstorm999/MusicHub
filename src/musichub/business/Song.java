package musichub.business;

import java.util.Map;
import java.util.List;

public class Song extends AudioElement{
    private String artist;
    private Genres gen;

    public Song(String artist, Genres gen, String title, int length, int id, String content){
        super(title, length, id, content);
        this.artist = artist;
        this.gen = gen;
    }

    public String artistToString(){
        return artist;
    }
    public String genreToString(){
        return gen.toString();
    }
    public String toString(){
        String result = null;
        result = artist + " " + gen + " " + title + " " + length + " " + id + " " + content;
        return result;
    }

    public Map<String, List<String>> save(){
        //TODO: implement save method
        return null;
    }

    public void load(Map<String, List<String>> arguments){
        //TODO: implement load method
        
    }

}
