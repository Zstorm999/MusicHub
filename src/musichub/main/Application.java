package musichub.main;

import musichub.business.*;
import musichub.main.consoleui.ConsoleUserDisplay;
import musichub.util.AudioXML;

import java.util.*;


/**
 * Represents the application and all its possible functions, that compose MusicHub
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
public class Application {

    private static AudioXML<Song> songsXML;
    private static AudioXML<AudioBook> audioBooksXML;
    private static AudioXML<Album> albumsXML;
    private static AudioXML<Playlist> playlistsXML;

    
    private static LinkedList<Song> songs;
    private static LinkedList<AudioBook> audioBooks;
    private static LinkedList<Album> albums;
    private static LinkedList<Playlist> playlists;


    /**
     * Creates a new Application object, and creates the associated xml files
     */
    public Application(){
        if(songsXML == null) songsXML = new AudioXML<>("./files/elements.xml", Song.class);
        if(audioBooksXML == null) audioBooksXML = new AudioXML<>("./files/elements.xml", AudioBook.class);
        if(albums == null) albumsXML = new AudioXML<>("./files/albums.xml", Album.class);
        if(playlistsXML == null) playlistsXML = new AudioXML<>("./files/playlists.xml", Playlist.class);

        if(songs == null) songs = new LinkedList<>();
        if(audioBooks == null) audioBooks = new LinkedList<>();
        if(playlists == null) playlists = new LinkedList<>();
        if(albums == null) albums = new LinkedList<>();

    }

    /**
     * Loads in the different lists all the data from xml files, at the starting of the program
     */
    public void loadAll(){
        songsXML.loadXML(songs);
        audioBooksXML.loadXML(audioBooks);

        albumsXML.loadXML(albums);
        playlistsXML.loadXML(playlists);

    }

    /**
     * Saves all the data in the xml files, in order to not loose them
     */
    public void saveAll(){
        songsXML.saveXML(songs, "audio", true);
        //audiobooks are the only ones to not overwrite, because they are stored in the same file as songs
        audioBooksXML.saveXML(audioBooks, "audio", false);

        albumsXML.saveXML(albums, "albums", true);
        playlistsXML.saveXML(playlists, "playlists", true);
    }

    /**
     * Returns the element (song or audiobook) with the corresponding id
     * @param id the id to search for
     * @return the element if found
     * @throws ElementNotFoundException if there is no element with such an id
     */
    public static AudioElement getElementWithID(int id) throws ElementNotFoundException{
        for(Song song : songs){
            if(song.getID() == id) return song;
        }

        for(AudioBook book : audioBooks){
            if(book.getID() == id) return book;
        }

        throw new ElementNotFoundException("There is no Song or AudioBook with the corresponding ID: " + Integer.toString(id));
    }

    /**
     * Returns the song with the corresponding id
     * @param id the id to search for
     * @return the song if found
     * @throws ElementNotFoundException if there is no song with such an id
     */
    public static Song getSongWithID(int id) throws ElementNotFoundException{
        for(Song song : songs){
            if(song.getID() == id) return song;
        }

        throw new ElementNotFoundException("There is no Song or AudioBook with the corresponding ID: " + Integer.toString(id));
    }

    /**
     * Returns a list of albums
     * @return a list of albums
     */
    public List<Album> getAlbums(){
        return albums;
    }

    /**
     * Generates and returns a new ID for a given element
     * @param elm the element for which to generate an ID, 1 is a song or an audio book, 2 is an album
     * @return an new ID
     */
    public int createNewId(int elm){
        int id = 0;
        //if the element is a song or a book
        if(elm == 1) {
            for (Song song : songs) {
                if (id <= song.getID()) {
                    id = song.getID();
                }
            }
            for(AudioBook book : audioBooks){
                if(id <= book.getID()){
                    id = book.getID();
                }
            }
        }
        //if the element is an album
        else if(elm == 2){
            for(Album album : albums){
                if(id <= album.getId()){
                    id = album.getId();
                }
            }
        }
        id++;
        return id;
    }

    /**
     * Add an new audio book to the list audioBooks
     * @param book the book to add
     */
    public void addAudioBook(AudioBook book){
        audioBooks.add(book);
    }

    /**
     * Add a new song to the list songs
     * @param song the song to add
     */
    public void addSong(Song song){
        songs.add(song);
    }

    /**
     * Add a new album to the list albums
     * @param album the album to add
     */
    public void addAlbum(Album album){
        albums.add(album);
    }

    /**
     * Add a song to an album, both already existing
     * @param songTitle the song's title
     * @param albumTitle the album's title
     * @throws ElementNotFoundException if one these elements does not exist
     */
    public void addSongToAlbum(String songTitle, String albumTitle) throws ElementNotFoundException{
        Song tmpSong = null;
        Album tmpAlbum = null;

        for (Song song : songs) {
            if (songTitle.equals(song.getTitle())) {
                tmpSong = song;
            }
        }
        for (Album album : albums) {
            if (albumTitle.equals(album.getTitle())) {
                tmpAlbum = album;
            }
        }
        //tmpAlbum.getAlbumWithTitle(albumTitle, albums);
        assert tmpAlbum != null;
        tmpAlbum.add(tmpSong);
    }

    /**
     * Creates a new playlist from a name and elements asked to the user
     * @param name the name of the new playlist
     */
    public void createPlaylist(String name){
        int id = 0;
        int choice;
        String songTitle, bookTitle, newChoice;
        Scanner scan = new Scanner(System.in);
        for(Playlist pl : playlists){
            if(id <= pl.getId()){
                id = pl.getId();
            }
        }
        id++;
        Playlist newPl = new Playlist(name, id);
        playlists.add(newPl);

        do {
            System.out.println("Do you want to add a song or an audio book ? Press a key : 1.Song 2.Audio book");
            choice = scan.nextInt();
            switch (choice){
                case 1:
                    scan.nextLine();
                    System.out.println("Please enter the title of the song :");
                    songTitle = scan.nextLine();
                    for(Song song : songs){
                        if(songTitle.equals(song.getTitle())){
                            newPl.add(song);
                        }
                    }
                    break;
                case 2:
                    scan.nextLine();
                    System.out.println("Please enter the title of the book :");
                    bookTitle = scan.nextLine();
                    for(AudioBook book : audioBooks){
                        if(bookTitle.equals(book.getTitle())){
                            newPl.add(book);
                        }
                    }
                    break;
                default :
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
            System.out.println("Do you want to add another element ? [y/n]");
            newChoice = scan.nextLine();
        }while(newChoice.equals("y"));
    }

    /**
     * Deletes a playlist, knowing its name
     * @param name the name of the playlist to delete
     * @throws ElementNotFoundException if the playlist does not exist
     */
    public void deletePlaylist(String name) throws ElementNotFoundException{
        Playlist tmp = null;
        for(Playlist pl : playlists){
            if(name.equals(pl.getName())){
                tmp = pl;
            }
        }
        if(tmp != null){ playlists.remove(tmp);}
    }

    /**
     * Displays a list of songs from an album
     * @param album the album to display its songs
     */
    public void displaySongsIntoAlbum(Album album){
        for(Song song : album.getSongs()){
            System.out.println(song);
        }
    }

    /**
     * Displays a list of albums, ordered by date
     */
    public void displayAlbumByDate(){
        Collections.sort(albums);
        for(Album album : albums){
            System.out.println(album);
        }
    }

    /**
     * Displays a list of songs from an album, ordered by genre
     * @param album the album to display its songs
     */
    public void displaySongsOfAlbumGenres(Album album){
        List<Song> list = album.getSongs();
        Collections.sort(list);
        for(Song song : list){
            System.out.println(song);
        }
    }

    /**
     * Displays a list of all the playlists
     */
    public void displayPlaylists(){
        for(Playlist playlist : playlists){
            System.out.println(playlist);
        }
    }

    /**
     * Displays a list of all the audio books
     */
    public void displayAudioBooksByAuthor(){
        List<AudioBook> list = audioBooks;
        Collections.sort(list);
        for(AudioBook book : list){
            System.out.println(book);
        }
    }

    /**
     * Displays some indications at the console to help the user to understand the functions
     */
    public void help(){
        System.out.println("1. Add a Song to MusicHub \nThis function add a song to the program.\nIt will ask you some details that you will need to give in order to save it.");
        System.out.println("\n2. Add an Album to Musichub \nThis function add an album to the program.\nIt will need from you to answer some details about the album in order to save it.");
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
}
