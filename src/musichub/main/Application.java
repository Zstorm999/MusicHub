package musichub.main;

import musichub.util.AudioXML;

import java.util.LinkedList;

import musichub.business.Album;
import musichub.business.AudioBook;
import musichub.business.AudioElement;
import musichub.business.ElementNotFoundException;
import musichub.business.Playlist;
import musichub.business.Song;


public class Application {

    private static AudioXML<Song> songsXML;
    private static AudioXML<AudioBook> audioBooksXML;
    private static AudioXML<Album> albumsXML;
    private static AudioXML<Playlist> playlistsXML;

    
    private static LinkedList<Song> songs;
    private static LinkedList<AudioBook> audioBooks;
    private static LinkedList<Album> albums;
    private static LinkedList<Playlist> playlists;

    

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

    public void loadAll(){
        
        //songs.add(new Song("Test", Genres.ROCK, "test as well", 120, 0, "nothing"));
        //songs.add(new Song("Test2", Genres.HIPHOP, "test as well", 254, 1, "nothing too"));

        //audioBooks.add(new AudioBook("Moi", Languages.FRENCH, Categories.NOVEL, "Tordan", 15, 0, "nothing yet"));
        //audioBooks.add(new AudioBook("El'", Languages.ENGLISH, Categories.YOUTH, "Well...", 7, 1, "work in progress"));

        songsXML.loadXML(songs);
        audioBooksXML.loadXML(audioBooks);

        albumsXML.loadXML(albums);
        playlistsXML.loadXML(playlists);


        playlists.add(new Playlist("Test", 0));

        try{
            Playlist.getPlaylistWithTitle("Test", playlists).add(songs.get(0));
            Playlist.getPlaylistWithTitle("Test", playlists).add(songs.get(1));
        }
        catch(ElementNotFoundException e){
            e.printStackTrace();
        }


    }

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
}
