package musichub.main;

import musichub.util.AudioXML;

import java.util.LinkedList;

import musichub.business.Playlist;


public class Application {

    AudioXML<Playlist> playlistsXML;
    //TODO: AudioXML for albums, songs and audiobooks

    LinkedList<Playlist> playlists;
    //TODO: LinkedList for albums, songs and audiobooks

    public Application(){
        playlistsXML = new AudioXML<>("./files/playlists.xml", Playlist.class);    

        playlists = new LinkedList<>();

    }

    public void loadAll(){
        playlistsXML.loadXML(playlists);

    }

    public void saveAll(){
        playlistsXML.saveXML(playlists, "playlists", true);

    }
}
