package musichub.main;

import musichub.ui.UserApplication;
import musichub.ui.consoleui.ConsoleUI;
import musichub.ui.windowui.WindowUI;

/**
 * Main class of the program
 * @see musichub.ui.consoleui.ConsoleUI
 * @see musichub.ui.windowui.WindowUI
 * @author Thomas Archambeau, Eléonore Vaissaire
 */
public class MusicHub {

    UserApplication app;

    /**
     * Creates a new object of type MusicHub
     */
    private MusicHub(AppType type) {

        switch (type){
            case CONSOLE:
                app = new ConsoleUI();
                break;
            case WINDOW:
                app = new WindowUI();
                break;
        }


        app.init();

        while(!app.mustEnd()){
            app.update();
        }

        app.end();
        
    }

    public static void main(String[] args) {

        if (args[0].equals("-c")) {
            new MusicHub(AppType.CONSOLE);
        }
        else{
            new MusicHub(AppType.WINDOW);
        }

    }

    private enum AppType{
        CONSOLE(), WINDOW()
    }
}
