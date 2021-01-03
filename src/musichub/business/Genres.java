package musichub.business;

/**
 * All possible music genres
 * @see Song
 * @author Thomas Archambeau, Eléonore Vaissaire
 */
public enum Genres {
    JAZZ("Jazz"), CLASSIC("Classic"), HIPHOP("Hip Hop"), ROCK("Rock"), POP("Pop"), RAP("Rap");

    private String str;

    
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
