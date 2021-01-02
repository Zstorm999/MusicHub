package musichub.business;

/**
 * All possible music genres
 * @see Song
 * @author Thomas Archambeau, Eléonore Vaissaire
 */
public enum Genres {
    JAZZ("Jazz"), CLASSIC("Classic"), HIPHOP("Hip Hop"), ROCK("Rock"), POP("Pop"), RAP("Rap");

    private String str;

    /**
     * Associates a string to a genre
     * @param str the string to associate
     */
    private Genres(String str){
        this.str = str;
    }

    /**
     * Returns the associated string
     * @return the associated string
     */
    public String toString(){
        return str;
    }
}
