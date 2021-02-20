package musichub.ui.windowui;

import musichub.business.Album;
import musichub.business.AudioBook;
import musichub.business.Playlist;
import musichub.business.Song;
import musichub.main.Application;
import musichub.ui.IUserApplication;

import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class SwingWindowUI extends JFrame implements IUserApplication {
    private JPanel contentPane;
    private JTabbedPane tabbedPane1;
    private JPanel songsPanel;
    private JPanel albumsPanel;



    private JList songsListDisplay;
    private JPanel booksPanel;
    private JPanel playlistsPanel;
    private JList albumsListDisplay;
    private JList booksListDisplay;
    private JList playlistsListDisplay;

    private Application app;

    public SwingWindowUI() {
        app = new Application();

        setContentPane(contentPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                app.saveAll();
            }
        });
    }

    private void onExit() {
        // add your code here if necessary
        dispose();
    }

    private void loadSongList(){

        Vector<String> v = new Vector<>();

        for(Song song : app.getSongs()){
               v.add(song.getTitle());
        }

        songsListDisplay.setListData(v);

    }

    private void loadBooksList(){
        Vector<String> v = new Vector<>();

        for(AudioBook book : app.getAudioBooks()){
            v.add(book.getTitle());
        }

        booksListDisplay.setListData(v);
    }

    private void loadAlbumsList(){
        Vector<String> v = new Vector<>();

        for(Album album : app.getAlbums()){
            v.add(album.getTitle());
        }

        albumsListDisplay.setListData(v);
    }

    private void loadPlaylistsList(){
        Vector<String> v = new Vector<>();

        for(Playlist playlist : app.getPlaylists()){
            v.add(playlist.getName());
        }

        playlistsListDisplay.setListData(v);
    }

    public void run() {

        setTitle("MusicHub");


        app.loadAll();

        loadSongList();
        loadBooksList();
        loadAlbumsList();
        loadPlaylistsList();

        pack();
        setVisible(true);
    }
}
