package musichub.ui.windowui;

import musichub.business.AudioBook;
import musichub.business.Categories;
import musichub.business.Languages;
import musichub.util.TimeString;

import javax.swing.*;
import java.awt.event.*;

public class SwingCreateBookDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField titleField;
    private JTextField authorField;
    private JComboBox langCombo;
    private JComboBox categoryCombo;
    private JTextField minutesField;
    private JTextField secondsField;
    private JTextField contentField;
    private JTextField hoursField;

    private AudioBook book;

    public SwingCreateBookDialog() {
        book = null;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Add an audio book");

        for(Languages lang: Languages.values()){
            langCombo.addItem(lang);
        }

        for(Categories cat: Categories.values()){
            categoryCombo.addItem(cat);
        }

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
                "Unable to create ",
                JOptionPane.ERROR_MESSAGE);
    }

    private void onOK() {
        String title, author, content;
        Languages lang;
        Categories cat;
        int hours, minutes, seconds;

        if(titleField.getText().equals("")){
            displayErrorMessage("Title field is empty");
            return;
        }
        else{
            title = titleField.getText();
        }

        if(authorField.getText().equals("")){
            displayErrorMessage("Author field is empty");
            return;
        }
        else{
            author = authorField.getText();
        }

        try{
            lang = (Languages) langCombo.getSelectedItem();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            displayErrorMessage("Invalid language, for whatever reason");
            return;
        }

        try{
            cat = (Categories) categoryCombo.getSelectedItem();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            displayErrorMessage("Invalid category, for whatever reason");
            return;
        }

        if(hoursField.getText().equals("") && minutesField.getText().equals("") && secondsField.getText().equals("")){
            displayErrorMessage("No length has been specified");
            return;
        }
        else{
            if(hoursField.getText().equals("")){
                hours = 0;
            }
            else{
                try{
                    hours = Integer.parseInt(hoursField.getText());
                }
                catch (NumberFormatException e){
                    System.out.println(e.getMessage());
                    displayErrorMessage("Incorrect number format for hours");
                    return;
                }
            }

            if(minutesField.getText().equals("")){
                minutes = 0;
            }
            else{
                try{
                    minutes = Integer.parseInt(minutesField.getText());
                }
                catch (NumberFormatException e){
                    System.out.println(e.getMessage());
                    displayErrorMessage("Incorrect number format for minutes");
                    return;
                }
            }

            if(secondsField.getText().equals("")){
                seconds = 0;
            }
            else {
                try{
                    seconds = Integer.parseInt(secondsField.getText());
                }
                catch (NumberFormatException e){
                    System.out.println(e.getMessage());
                    displayErrorMessage("Incorrect number format for seconds");
                    return;
                }
            }
        }

        if(contentField.getText().equals("")){
            displayErrorMessage("Content field is empty");
            return;
        }
        else{
            content = contentField.getText();
        }

        book = new AudioBook(author, lang, cat, title, TimeString.toSeconds(hours, minutes, seconds), content);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public AudioBook getBook(){
        return book;
    }
}
