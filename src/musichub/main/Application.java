package musichub.main;

import musichub.util.AudioXML;

import java.util.LinkedList;

import musichub.business.AudioBook;
import musichub.business.Playlist;
import musichub.business.Song;


public class Application {

    AudioXML<Playlist> playlistsXML;
    AudioXML<Song> songsXML;
    AudioXML<AudioBook> audioBooksXML;
    //TODO: AudioXML for albums

    LinkedList<Playlist> playlists;
    LinkedList<Song> songs;
    LinkedList<AudioBook> audioBooks;
    //TODO: LinkedList for albums

    public Application(){
        playlistsXML = new AudioXML<>("./files/playlists.xml", Playlist.class);
        
        songsXML = new AudioXML<>("./files/???", Song.class);
        audioBooksXML = new AudioXML<>("./files/???", AudioBook.class);

        playlists = new LinkedList<>();
        songs = new LinkedList<>();
        audioBooks = new LinkedList<>();

    }

    public void loadAll(){
        playlistsXML.loadXML(playlists);

        songsXML.loadXML(songs);
        audioBooksXML.loadXML(audioBooks);

    }

    public void saveAll(){
        playlistsXML.saveXML(playlists, "playlists", true);

        songsXML.saveXML(songs, "audio", true);
        audioBooksXML.saveXML(audioBooks, "audio", false);
    }
}
