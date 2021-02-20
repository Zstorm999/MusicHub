package musichub.ui.windowui;

import musichub.business.Album;
import musichub.business.AudioBook;
import musichub.business.Playlist;
import musichub.business.Song;
import musichub.main.Application;
import musichub.ui.IUserApplication;

import java.awt.print.Book;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private JLabel songTitle;
    private JPanel songInfo;
    private JLabel songArtist;
    private JLabel songGenre;
    private JPanel bookInfo;
    private JLabel bookTitle;
    private JLabel bookAuthor;
    private JLabel bookCategory;
    private JLabel bookLanguage;

    private Application app;

    public SwingWindowUI() {
        app = new Application();

        setContentPane(contentPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        songInfo.setVisible(false);
        bookInfo.setVisible(false);

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

    private int findSelectIndex(int first, int last, int lastFirst, int lastLast, int lastSelected){
        int index = 0;
        if(first != lastFirst){
            index = first;

            if(first == lastLast){ //not a normal case! but swing is weird...
                index = last;
            }
            else{
                index = first;
            }

        }
        else if(last != lastLast){

            index = last;
        }
        else{
            if(lastSelected == lastFirst) index = lastLast;
            else index = lastFirst;
        }

        return index;
    }

    private void loadSongList(){

        Vector<Song> v = new Vector<>(app.getSongs());

        songsListDisplay.setListData(v);



        songsListDisplay.addListSelectionListener(new ListSelectionListener() {

            private int lastFirst = 0;
            private int lastLast = 0;
            private int lastSelected = 0;

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()) return;

                int index = findSelectIndex(e.getFirstIndex(), e.getLastIndex(), lastFirst, lastLast, lastSelected);



                lastSelected = index;
                lastFirst = e.getFirstIndex();
                lastLast = e.getLastIndex();


                Song selected = v.get(index);
                songTitle.setText(selected.getTitle());
                songArtist.setText(selected.getArtist());
                songGenre.setText(selected.getGen().toString());

                if(!songInfo.isVisible())
                    songInfo.setVisible(true);

            }
        });

    }

    private void loadBooksList(){
        Vector<AudioBook> v = new Vector<>(app.getAudioBooks());

        booksListDisplay.setListData(v);


        booksListDisplay.addListSelectionListener(new ListSelectionListener() {

            private int lastFirst = 0;
            private int lastLast = 0;
            private int lastSelected = 0;

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()) return;

                int index = findSelectIndex(e.getFirstIndex(), e.getLastIndex(), lastFirst, lastLast, lastSelected);



                lastSelected = index;
                lastFirst = e.getFirstIndex();
                lastLast = e.getLastIndex();


                AudioBook selected = v.get(index);
                bookTitle.setText(selected.getTitle());
                bookAuthor.setText(selected.getAuthor());
                bookCategory.setText(selected.getCategory().toString());
                bookLanguage.setText(selected.getLanguage().toString());

                if(!bookInfo.isVisible())
                    bookInfo.setVisible(true);

            }
        });
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
