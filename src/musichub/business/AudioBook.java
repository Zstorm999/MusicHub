package musichub.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import musichub.util.IAudioToXML;

/**
 * Represents an audio book
 * @see Song
 * @see AudioElement
 * @see Categories
 * @see Languages
 * @author Thomas Archambeau, Eléonore Vaissaire
 */
public class AudioBook extends AudioElement implements Comparable<AudioBook>{
    private String author;
    private Languages language;
    private Categories category;

    /**
     * Empty constructor, does nothing (but needed for XML storage)
     */
    public AudioBook(){

    }

    /**
     * Creates a new AudioBook
     * @param author the author
     * @param language the language
     * @param category the category
     * @param title the title
     * @param length the length in seconds
     * @param content the file associated to this book
     */
    public AudioBook(String author, Languages language, Categories category, String title, int length, String content){
        super(title, length, content);
        this.author = author;
        this.language = language;
        this.category = category;
    }

    /**
     * Copy constructor
     * @param other another book
     */
    public AudioBook(AudioBook other){
        super(other);
        this.author = other.author;
        this.language = other.language;
        this.category = other.category;
    }

    /**
     * Returns the author of the book
     * @return the author of the book
     */
    public String getAuthor(){
        return author;
    }

    /**
     * Returns the language of the book
     * @return the language of the book
     */
    public Languages getLanguage(){
        return language;
    }

    /**
     * Returns the category of the book
     * @return the category of the book
     */
    public Categories getCategory(){ return category; }

    /**
     * Returns a complete string with all the attributes
     * @return a complete string with all the attributes
     */
    public String toString(){
        return title;
    }

    
    public Map<String, List<String>> save(){
        HashMap<String, List<String>> attributes = new HashMap<>();

        attributes.put("Author", IAudioToXML.toList(author));
        attributes.put("Language", IAudioToXML.toList(language.toString()));
        attributes.put("Category", IAudioToXML.toList(category.toString()));
        attributes.put("Title", IAudioToXML.toList(title));
        attributes.put("Length", IAudioToXML.toList(Integer.toString(length)));
        attributes.put("ID", IAudioToXML.toList(Integer.toString(id)));
        attributes.put("Content", IAudioToXML.toList(content));

        return attributes;
    }

    
    public void load(Map<String, List<String>> attributes){
        try{
            author = attributes.get("Author").get(0);
            language = Languages.valueOf(attributes.get("Language").get(0).toUpperCase());
            category = Categories.valueOf(attributes.get("Category").get(0).toUpperCase());
            title = attributes.get("Title").get(0);
            length = Integer.parseInt(attributes.get("Length").get(0));
            id = Integer.parseInt(attributes.get("ID").get(0));
            content = attributes.get("Content").get(0);

        }
        catch(IndexOutOfBoundsException | NullPointerException | NumberFormatException e){
            e.printStackTrace();
        }

    }

    /**
     * Compare the audioBook to another (by author)
     * @param book the book to compare (by author)
     * @return the result of the comparison of author of both books
     */
    public int compareTo(AudioBook book) {
        return this.author.compareTo(book.author);
    }
}
