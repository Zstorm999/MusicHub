package musichub.main.consoleui;

/**
 * Represents a class that only displays some messages at the console for the user's comfort
 * @author Thomass Archambeau, Eléonore Vaissaire
 */
public class ConsoleUserDisplay {

    /**
     * Displays at the console a welcoming message when starting the program
     */
    public static void sayHello(){
        System.out.println("Welcome to MusicHub, a place to store and organize all you musical files !");
    }

    /**
     * Displays the menu at the console
     */
    public static void displayMenu(){
        System.out.println("\nPress one of these keys to continue :");
        System.out.println("1 : add a song to MusicHub");
        System.out.println("2 : add an album to MusicHub");
        System.out.println("3 : add a song to an album");
        System.out.println("4 : add an audio book to MusicHub");
        System.out.println("5 : create a new playlist from songs and audio books on MusicHub");
        System.out.println("6 : delete a playlist");
        System.out.println("7 : save playlists, albums, songs and audio books");
        System.out.println("8 : help concerning those commands");
        System.out.println("9 : display the list of albums sorted by date");
        System.out.println("10 : display the list of Audio books ordered by author");
        System.out.println("11 : display the list of Playlists");
        System.out.println("12 : display the list of songs in a given album");
        System.out.println("13 : display the list of songs in a given album ordered by genre");
        System.out.println("14 : quit MusicHub");
    }

    /**
     * Displays a message of "good bye" when ending the program
     */
    public static void sayGoodbye(){
        System.out.println("See you next time !");
    }

}
