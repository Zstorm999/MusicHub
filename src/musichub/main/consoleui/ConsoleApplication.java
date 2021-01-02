package musichub.main.consoleui;

import musichub.business.*;
import musichub.main.Application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ConsoleApplication {
    
    private Application app;
    private boolean mustEnd;

    /**
     * Creates a new object of type ConsoleApplication
     */
    public ConsoleApplication(){
        app = new Application();
    }

    /**
     * Returns a boolean that indicates if the program has to stop
     * @return a boolean that indicates if the program has to stop
     */
    public boolean mustEnd(){
        return mustEnd;
    }

    /**
     * Put here everything that should be done on the application's startup
     */
    public void init(){
        app.loadAll();
        ConsoleUserDisplay.sayHello();
    }

    /**
     * Method that handle all the possible functions and the user's choice
     * @throws ElementNotFoundException
     * @throws ParseException
     */
    public void update() throws ElementNotFoundException, ParseException {

        ConsoleUserDisplay.displayMenu();

        Scanner scan = new Scanner(System.in);
        String choice;

        String songTitle, albumTitle, playlistName, artistName, content, bookTitle;
        int length, genreChoice, langChoice, catChoice, id;
        Genres genre;
        Languages lang;
        Categories cat;

        choice = scan.nextLine();
        switch(choice){
            case"1":

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

                id = app.createNewId(1);

                Song newSong = new Song(artistName, genre, songTitle, length, id, content);
                app.addSong(newSong);
                break;

            case"2":
                String date;
                System.out.println("Enter the title of the album :");
                albumTitle = scan.nextLine();
                System.out.println("Enter the artist's name : ");
                artistName = scan.nextLine();
                System.out.println("Enter the date associated to the album (format dd/MM/yyyy) :");
                date = scan.nextLine();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date newDate = format.parse(date);

                id = app.createNewId(2);

                Album newAlbum = new Album(albumTitle, artistName, id, newDate);
                app.addAlbum(newAlbum);
                break;

            case"3":
                System.out.println("Enter the title of the song you wish to add : ");
                songTitle = scan.nextLine();
                System.out.println("Enter the title of the album where you wish to add the song : ");
                albumTitle = scan.nextLine();
                app.addSongToAlbum(songTitle, albumTitle);
                break;

            case"4":
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

                id = app.createNewId(1);

                AudioBook newBook = new AudioBook(artistName, lang, cat, bookTitle, length, id, content);
                app.addAudioBook(newBook);
                break;

            case"5":
                System.out.println("Enter the name of the new playlist : ");
                playlistName = scan.nextLine();
                app.createPlaylist(playlistName);
                break;

            case"6":
                System.out.println("Enter the name of the playlist you wish to delete : ");
                playlistName = scan.nextLine();
                app.deletePlaylist(playlistName);
                break;

            case"7":
                app.saveAll();
                System.out.println("Everything has been saved with success !");
                break;

            case"8":
                app.help();
                break;

            case"9":
                app.displayAlbumByDate();
                break;
            case"10":
                app.displayAudioBooksByAuthor();
                break;
            case"11":
                app.displayPlaylists();
                break;
            case"12":
                System.out.println("Enter the name of the album to display :");
                albumTitle = scan.nextLine();
                Album alb = Album.getAlbumWithTitle(albumTitle, app.getAlbums());
                app.displaySongsIntoAlbum(alb);
                break;
            case"13":
                System.out.println("Enter the name of the album to display :");
                albumTitle = scan.nextLine();
                Album anotherOne = Album.getAlbumWithTitle(albumTitle, app.getAlbums());
                app.displaySongsOfAlbumGenres(anotherOne);
                break;
            case"14":
                mustEnd = true;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }

    /**
     * Put here everything that should be done just before the application's end
     */
    public void end(){
        app.saveAll();
        ConsoleUserDisplay.sayGoodbye();
    }

}
