package musichub.business;

public enum Genres {
    JAZZ("Jazz"), CLASSIC("Classic"), HIPHOP("Hip Hop"), ROCK("Rock"), POP("Pop"), RAP("Rap");

    private String str;

    private Genres(String str){
        this.str = str;
    }

    public String toString(){
        return str;
    }
}
