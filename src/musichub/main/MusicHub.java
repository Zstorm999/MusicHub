package musichub.main;

import musichub.main.consoleui.ConsoleApplication;

/**
 * Main class of the program
 * @see musichub.main.consoleui.ConsoleApplication
 * @author Thomas Archambeau, Eléonore Vaissaire
 */
public class MusicHub {

    ConsoleApplication app;

    /**
     * Creates a new object of type MusicHub
     */
    private MusicHub() {
        app = new ConsoleApplication();

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
