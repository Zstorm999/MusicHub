package musichub.ui.windowui;

import musichub.business.Genres;
import musichub.business.Song;
import musichub.util.TimeString;

import javax.swing.*;
import java.awt.event.*;

public class SwingCreateSongDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField titleField;
    private JTextField artistField;
    private JComboBox genreCombo;
    private JTextField lengthMinutesField;
    private JTextField contentField;
    private JTextField lengthSecondsField;

    private Song newSong;

    public SwingCreateSongDialog() {
        newSong = null;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Add a song");

        for(Genres gen : Genres.values()){
            genreCombo.addItem(gen);
        }
        genreCombo.setSelectedIndex(0);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        pack();

    }

    private void displayErrorMessage(String message){
        JOptionPane.showMessageDialog(contentPane,
                message,
                "Unable to create song",
                JOptionPane.ERROR_MESSAGE);
    }

    private void onOK() {
        // add your code here
        String title, artist, content;
        Genres gen;
        int seconds, minutes;


        if(titleField.getText().equals("")){
            displayErrorMessage("Title field is empty");
            return;
        }
        else{
            title = titleField.getText();
        }

        if(artistField.getText().equals("")){
            displayErrorMessage("Artist field is empty");
            return;
        }
        else{
            artist = artistField.getText();
        }

        if(contentField.getText().equals("")){
            displayErrorMessage("Content field is empty");
            return;
        }
        else{
            content = contentField.getText();
        }

        if(lengthMinutesField.getText().equals("") && lengthSecondsField.getText().equals("")){
            displayErrorMessage("No length has been specified");
            return;
        }
        else{
            if (lengthSecondsField.getText().equals("")){
                seconds = 0;
            }
            else{
                try{
                    seconds = Integer.parseInt(lengthSecondsField.getText());
                }
                catch (NumberFormatException e){
                    System.out.println(e.getMessage());
                    displayErrorMessage("Incorrect number format for seconds");
                    return;
                }
            }

            if (lengthMinutesField.getText().equals("")){
                minutes = 0;
            }
            else{
                try{
                    minutes = Integer.parseInt(lengthMinutesField.getText());
                }
                catch (NumberFormatException e){
                    System.out.println(e.getMessage());
                    displayErrorMessage("Incorrect number format for minutes");
                    return;
                }
            }
        }

        try {
            gen = (Genres) genreCombo.getSelectedItem();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            displayErrorMessage("Invalid genre, for whatever reason");
            return;
        }

        newSong = new Song(artist, gen, title, TimeString.toSeconds(0, minutes, seconds), content);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public Song getSong(){
        return newSong;
    }

}
