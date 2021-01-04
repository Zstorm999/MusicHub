package musichub.main;

import musichub.ui.consoleui.ConsoleUI;

/**
 * Main class of the program
 * @see musichub.ui.consoleui.ConsoleUI
 * @author Thomas Archambeau, Eléonore Vaissaire
 */
public class MusicHub {

    ConsoleUI app;

    /**
     * Creates a new object of type MusicHub
     */
    private MusicHub() {
        app = new ConsoleUI();

        app.init();

        while(!app.mustEnd()){
            try{
                app.update();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        app.end();
        
    }

    public static void main(String[] args) {
        new MusicHub();
    }
}
