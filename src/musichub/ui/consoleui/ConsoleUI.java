package musichub.ui.consoleui;

import musichub.business.*;
import musichub.main.Application;
import musichub.ui.UserLoopApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the class that handle the application and its functions
 * @see Application
 * @see ConsoleUserDisplay
 * @see musichub.main.MusicHub
 * @see Song
 * @see AudioBook
 * @see AudioElement
 * @see Album
 * @see Playlist
 * @see Genres
 * @see Languages
 * @see Categories
 * @author Thomas Archembeau, Eléonore Vaissaire
 */
public class ConsoleUI extends UserLoopApplication {
    
    private Application app;
    private boolean mustEnd;

    private Scanner scan;

    /**
     * Creates a new object of type ConsoleApplication
     */
    public ConsoleUI(){
        app = new Application();
        scan = new Scanner(System.in);
    }

    public boolean mustEnd(){
        return mustEnd;
    }


    public void init(){
        app.loadAll();
        ConsoleUserDisplay.sayHello();
    }


    public void update(){

        ConsoleUserDisplay.displayMenu();

        String choice;



        choice = scan.nextLine();
        switch(choice){
            case"1":
                addSong();
                break;
            case"2":
                addAlbum();
                break;
            case"3":
                addSongToAlbum();
                break;
            case"4":
                addAudioBook();
                break;
            case"5":
                createPlaylist();
                break;
            case"6":
                deletePlaylist();
                break;
            case"7":
                app.saveAll();
                System.out.println("Everything has been saved with success !");
                break;
            case"8":
                help();
                break;
            case"9":
                displayAlbumByDate();
                break;
            case"10":
                displayAudioBooksByAuthor();
                break;
            case"11":
                displayPlaylists();
                break;
            case"12":
                displaySongsIntoAlbum();
                break;
            case"13":

                displaySongsOfAlbumGenres();
                break;
            case"14":
                mustEnd = true;
                break;
            default:
                System.out.println("This value is not recognised as a valid choice");
        }
    }

    public void end(){
        app.saveAll();
        ConsoleUserDisplay.sayGoodbye();

        scan.close();
    }


    private void addSong() throws IllegalStateException{
        String songTitle, artistName, content;
        int length, genreChoice;
        Genres genre;

        System.out.println("Enter the title of the song : ");
        songTitle = scan.nextLine();
        System.out.println("Enter the artist's name : ");
        artistName = scan.nextLine();
        System.out.println("Enter the length (in seconds) of the song : ");
        length = scan.nextInt();
        System.out.println("Press a key to determine the genre : 1.Jazz 2.Classic 3.Hip hop 4.Rock 5.Pop 6.Rap");
        genreChoice = scan.nextInt();
        switch(genreChoice){
            case 1:
                genre = Genres.JAZZ;
                break;
            case 2:
                genre = Genres.CLASSIC;
                break;
            case 3:
                genre = Genres.HIPHOP;
                break;
            case 4:
                genre = Genres.ROCK;
                break;
            case 5:
                genre = Genres.POP;
                break;
            case 6:
                genre = Genres.RAP;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + genreChoice);
        }

        scan.nextLine(); //empty buffer
        System.out.println("Enter the path to your song : ");
        content = scan.nextLine(); //Not taken into account


        app.addSong(artistName, genre, songTitle, length, content);
    }

    private void addAudioBook(){
        String bookTitle, artistName, content;
        int length, langChoice, catChoice;
        Languages lang;
        Categories cat;

        System.out.println("Enter the title of the book : ");
        bookTitle = scan.nextLine();
        System.out.println("Enter the author's name : ");
        artistName = scan.nextLine();
        System.out.println("Enter the length (in seconds) of the book : ");
        length = scan.nextInt();
        System.out.println("Press a key to determine the language : 1.French 2.English 3.Italian 4.Spanish 5.German");
        langChoice = scan.nextInt();
        switch (langChoice){
            case 1:
                lang = Languages.FRENCH;
                break;
            case 2:
                lang = Languages.ENGLISH;
                break;
            case 3:
                lang = Languages.ITALIAN;
                break;
            case 4:
                lang = Languages.SPANISH;
                break;
            case 5:
                lang = Languages.GERMAN;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + langChoice);
        }
        System.out.println("Press a key to determine the category : 1.Youth 2.Novel 3.Theater 4.Speech 5.Documentary");
        catChoice = scan.nextInt();
        switch (catChoice){
            case 1:
                cat = Categories.YOUTH;
                break;
            case 2:
                cat = Categories.NOVEL;
                break;
            case 3:
                cat = Categories.THEATER;
                break;
            case 4:
                cat = Categories.SPEECH;
                break;
            case 5:
                cat = Categories.DOCUMENTARY;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + catChoice);
        }

        scan.nextLine(); //empty buffer
        System.out.println("Enter the path to the associated file : ");
        content = scan.nextLine(); //not taken into account

        app.addAudioBook(artistName, lang, cat, bookTitle, length, content);
    }

    private void addAlbum(){
        String albumTitle, artistName;
        Date newDate;

        String date;
        System.out.println("Enter the title of the album :");
        albumTitle = scan.nextLine();
        System.out.println("Enter the artist's name : ");
        artistName = scan.nextLine();
        System.out.println("Enter the date associated to the album (format dd/MM/yyyy) :");
        date = scan.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


        try {
            newDate = format.parse(date);
        }
        catch (ParseException e){
            System.out.println(e.getMessage());
            return;
        }

        app.addAlbum(albumTitle, artistName, newDate);
    }

    private void addSongToAlbum(){
        String songTitle, albumTitle;

        System.out.println("Enter the title of the song you wish to add : ");
        songTitle = scan.nextLine();
        System.out.println("Enter the title of the album where you wish to add the song : ");
        albumTitle = scan.nextLine();

        try {
            app.addSongToAlbum(songTitle, albumTitle);
        }
        catch (ElementNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    private void createPlaylist(){
        String playlistName;
        System.out.println("Enter the name of the new playlist : ");
        playlistName = scan.nextLine();
        app.createPlaylist(playlistName);
    }

    private void deletePlaylist(){
        String playlistName;
        System.out.println("Enter the name of the playlist you wish to delete : ");
        playlistName = scan.nextLine();
        try {
            app.deletePlaylist(playlistName);
        }
        catch (ElementNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    private void help(){
        System.out.println("1. Add a Song to MusicHub \nThis function add a song to the program.\nIt will ask you some details that you will need to give in order to save it.");
        System.out.println("\n2. Add an Album to MusicHub \nThis function add an album to the program.\nIt will need from you to answer some details about the album in order to save it.");
        System.out.println("\n3. Add a song to an album \nThis function will add a song to an album, where both of them need to already exist in the program.\nThe program will ask for the names of the song and the album.");
        System.out.println("\n4. Add an Audio Book to MusicHub \nThis function add an audio book to the program.\nIt will ask you for different details that you will need to answer.");
        System.out.println("\n5. Creation of a new Playlist\nThis function will create a new playlist and will ask you to add some elements.\nYou will need to know the name of the elements you wish to add");
        System.out.println("\n6. Deletion of a Playlist\nThis function will delete a playlist.\nYou will need to know the name of the playlist you wish to delete.");
        System.out.println("\n7. Saving\nIn order to keep all the data even after leaving the program, this function will save them in xml files.");
        System.out.println("\n9. Displaying the list of albums, ordered by date\nThis function will display at the console a list of albums, with all their information, ordered by the date of creation. ");
        System.out.println("\n10. Displaying the list of audio books, ordered by author\nThis function will display at the console a list of audio books, with all their information, ordered by author.");
        System.out.println("\n11. Displaying the list of playlists\nThis function will display at the console a list of all the playlists with their information.");
        System.out.println("\n12. Displaying the list of songs in a given album\nThis function will display at the console the list of songs and their information, of the album chosen.\nThe program will ask the name of the album.");
        System.out.println("\n13. Displaying the list of songs in a given album, ordered by genre\nThis function will display at the console the list of songs and their information, of the album chosen, ordered by songs' genre.\nThe program will ask the name of the album.");
    }


    private void displayAudioBooksByAuthor(){
        List<AudioBook> list = app.getAudioBooks();
        Collections.sort(list);
        for(AudioBook book : list){
            System.out.println(book);
        }
    }

    private void displayPlaylists(){
        for(Playlist playlist : app.getPlaylists()){
            System.out.println(playlist);
        }
    }

    private void displaySongsOfAlbumGenres(){
        Album album;
        String albumTitle;
        System.out.println("Enter the name of the album to display :");
        albumTitle = scan.nextLine();

        try {
            album = Album.getAlbumWithTitle(albumTitle, app.getAlbums());
        }
        catch (ElementNotFoundException e){
            System.out.println(e.getMessage());
            return;
        }

        List<Song> list = album.getSongs();
        Collections.sort(list);
        for(Song song : list){
            System.out.println(song);
        }
    }

    private void displayAlbumByDate(){
        List<Album> albums = app.getAlbums();
        Collections.sort(albums);
        for(Album album : albums){
            System.out.println(album);
        }
    }


    private void displaySongsIntoAlbum(){
        String albumTitle;
        Album alb;

        System.out.println("Enter the name of the album to display :");
        albumTitle = scan.nextLine();

        try {
            alb = Album.getAlbumWithTitle(albumTitle, app.getAlbums());
        }
        catch(ElementNotFoundException e){
            System.out.println(e.getMessage());
            return;
        }

        for(Song song : alb.getSongs()){
            System.out.println(song);
        }


    }


}
