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

    public String ArtistToString(){
        return artist;
    }
    public String GenreToString(){
        return gen.toString();
    }

    public Map<String, List<String>> save(){
        //TODO: implement save method
        return null;
    }

    public void load(Map<String, List<String>> arguments){
        //TODO: implement load method
        
    }
}
