package musichub.business;

import java.util.List;
import java.util.Map;

public class AudioBook extends AudioElement{
    private String author;
    private Languages language;
    private Categories category;

    public AudioBook(String author, Languages language, Categories category, String title, int length, int id, String content){
        super(title, length, id, content);
        this.author = author;
        this.language = language;
        this.category = category;
    }

    public String authorToString(){
        return author;
    }
    public String languageToString(){
        return language.toString();
    }
    public String categoryToString(){
        return category.toString();
    }
    public String toString(){
        String result = author + " " + language + " " + category + " " + title + " " + length + " " + id + " " + content;
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
