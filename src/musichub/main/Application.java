package musichub.main;

import musichub.util.AudioXML;

import java.util.LinkedList;

import musichub.business.AudioBook;
import musichub.business.AudioElement;
import musichub.business.ElementNotFoundException;
import musichub.business.Playlist;
import musichub.business.Song;


public class Application {

    private static AudioXML<Playlist> playlistsXML;
    private static AudioXML<Song> songsXML;
    private static AudioXML<AudioBook> audioBooksXML;
    //TODO: AudioXML for albums

    private static LinkedList<Playlist> playlists;
    private static LinkedList<Song> songs;
    private static LinkedList<AudioBook> audioBooks;
    //TODO: LinkedList for albums

    

    public Application(){
        if(playlistsXML == null) playlistsXML = new AudioXML<>("./files/playlists.xml", Playlist.class);
        if(songsXML == null) songsXML = new AudioXML<>("./files/elements.xml", Song.class);
        if(audioBooksXML == null) audioBooksXML = new AudioXML<>("./files/elements.xml", AudioBook.class);

        playlists = new LinkedList<>();
        songs = new LinkedList<>();
        audioBooks = new LinkedList<>();

    }

    public void loadAll(){
        
        //songs.add(new Song("Test", Genres.ROCK, "test as well", 120, 0, "nothing"));
        //songs.add(new Song("Test2", Genres.HIPHOP, "test as well", 254, 1, "nothing too"));

        //audioBooks.add(new AudioBook("Moi", Languages.FRENCH, Categories.NOVEL, "Tordan", 15, 0, "nothing yet"));
        //audioBooks.add(new AudioBook("El'", Languages.ENGLISH, Categories.YOUTH, "Well...", 7, 1, "work in progress"));

        songsXML.loadXML(songs);
        audioBooksXML.loadXML(audioBooks);

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
        playlistsXML.saveXML(playlists, "playlists", true);

        songsXML.saveXML(songs, "audio", true);
        audioBooksXML.saveXML(audioBooks, "audio", false);
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
    public static AudioElement getSongWithID(int id) throws ElementNotFoundException{
        for(Song song : songs){
            if(song.getID() == id) return song;
        }

        throw new ElementNotFoundException("There is no Song or AudioBook with the corresponding ID: " + Integer.toString(id));
    }
}
