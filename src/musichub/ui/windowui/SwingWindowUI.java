package musichub.ui.windowui;

import musichub.business.*;
import musichub.main.Application;
import musichub.ui.IUserApplication;
import musichub.util.TimeString;

import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;

public class SwingWindowUI extends JFrame implements IUserApplication {

    //GUI members

    private JPanel contentPane;
    private JTabbedPane tabbedPane1;

    //song panel
    private JPanel songsPanel;
    private JList songsDisplay;
    private JButton newSongButton;
    private JPanel songInfo;
    private JLabel songTitle;
    private JLabel songArtist;
    private JLabel songGenre;
    private JLabel songLength;


    //book panel
    private JPanel booksPanel;
    private JList booksDisplay;
    private JPanel bookInfo;
    private JLabel bookTitle;
    private JLabel bookAuthor;
    private JLabel bookCategory;
    private JLabel bookLanguage;


    //album panel
    private JPanel albumsPanel;
    private JList albumsDisplay;
    private JPanel albumInfo;
    private JList albumSongsDisplay;
    private JLabel albumLength;
    private JLabel albumTitle;
    private JLabel albumArtist;


    //playlist panel
    private JPanel playlistsPanel;
    private JList playlistsDisplay;
    private JLabel playlistTitle;
    private JPanel playlistInfo;
    private JList playlistElementDisplay;
    private JLabel bookLength;



    //Other members

    private Application app;
    private Vector<Song> songs;

    public SwingWindowUI() {
        app = new Application();

        setContentPane(contentPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        songInfo.setVisible(false);
        bookInfo.setVisible(false);
        albumInfo.setVisible(false);
        playlistInfo.setVisible(false);

        newSongButton.addActionListener(e -> createNewSong());


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

        songs = new Vector<>(app.getSongs());

        songsDisplay.setListData(songs);



        songsDisplay.addListSelectionListener(new ListSelectionListener() {

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


                Song selected = songs.get(index);
                songTitle.setText(selected.getTitle());
                songArtist.setText(selected.getArtist());
                songGenre.setText(selected.getGen().toString());
                songLength.setText(TimeString.timeToString(selected.getLength()));

                if(!songInfo.isVisible())
                    songInfo.setVisible(true);

            }
        });

    }

    private void loadBooksList(){
        Vector<AudioBook> v = new Vector<>(app.getAudioBooks());

        booksDisplay.setListData(v);


        booksDisplay.addListSelectionListener(new ListSelectionListener() {

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
                bookLength.setText(TimeString.timeToString(selected.getLength()));

                if(!bookInfo.isVisible())
                    bookInfo.setVisible(true);

            }
        });
    }

    private void loadAlbumsList(){
        Vector<Album> v = new Vector<>(app.getAlbums());

        albumsDisplay.setListData(v);

        albumsDisplay.addListSelectionListener(new ListSelectionListener() {

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


                Album selected = v.get(index);
                albumTitle.setText(selected.getTitle());
                albumArtist.setText(selected.getArtist());
                albumLength.setText(TimeString.timeToString(selected.getLength()));

                Vector<Song> content = new Vector<>();

                try {
                    for (int id : selected.getSongs()) {
                        content.add(app.getSongWithID(id));
                    }
                }
                catch (ElementNotFoundException err){
                    System.out.println(err.getMessage());
                    return;
                }

                albumSongsDisplay.setListData(content);

                if(!albumInfo.isVisible())
                    albumInfo.setVisible(true);

            }
        });
    }

    private void loadPlaylistsList(){
        Vector<Playlist> v = new Vector<>(app.getPlaylists());

        playlistsDisplay.setListData(v);

        playlistsDisplay.addListSelectionListener(new ListSelectionListener() {

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


                Playlist selected = v.get(index);
                playlistTitle.setText(selected.getName());

                Vector<AudioElement> content = new Vector<>();

                try {
                    for (int id : selected.getElementsList()) {
                        content.add(app.getElementWithID(id));
                    }
                }
                catch (ElementNotFoundException err){
                    System.out.println(err.getMessage());
                    return;
                }

                playlistElementDisplay.setListData(content);

                if(!playlistInfo.isVisible())
                    playlistInfo.setVisible(true);

            }
        });

    }

    private void createNewSong(){
        SwingCreateSongDialog dial = new SwingCreateSongDialog();
        dial.setLocationRelativeTo(null);
        dial.setVisible(true);

        if(dial.getSong() != null) {

            app.addSong(dial.getSong());

            songs = new Vector<>(app.getSongs());
            songsDisplay.setListData(songs);
        }
    }

    public void run() {

        setTitle("MusicHub");


        app.loadAll();

        loadSongList();
        loadBooksList();
        loadAlbumsList();
        loadPlaylistsList();

        //pack();
        setSize(800, 600);
        setVisible(true);
    }

}
