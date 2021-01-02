package musichub.main.consoleui;

public class ConsoleUserDisplay {
    
    public static void sayHello(){
        System.out.println("Welcome to MusicHub, a place to store and organize all you musical files !");
    }

    public static void displayMenu(){
        System.out.println("Press one of these keys to continue :");
        System.out.println("1 : add a song to MusicHub");
        System.out.println("2 : add an album to MusicHub");
        System.out.println("3 : add a song to an album");
        System.out.println("4 : add an audio book to MusicHub");
        System.out.println("5 : create a new playlist from songs and audio books on MusicHub");
        System.out.println("6 : delete a playlist");
        System.out.println("7 : save playlists, albums, songs and audio books");
        System.out.println("8 : help concerning those commands");
        System.out.println("9 : quit MusicHub");
    }

    public static void sayGoodbye(){
        System.out.println("See you next time !");
    }

}
