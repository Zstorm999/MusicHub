package musichub.business;

public enum Categories{
    YOUTH("Youth"), NOVEL("Novel"), THEATER("Theater"), SPEECH("Speech"), DOCUMENTARY("Documentary");

    private final String str;

    private Categories(String str){
        this.str = str;
    }

    public String toString(){
        return str;
    }
}