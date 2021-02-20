package musichub.main;

import musichub.ui.IUserApplication;
import musichub.ui.consoleui.ConsoleUI;
import musichub.ui.windowui.SwingWindowUI;

/**
 * Main class of the program
 * @see musichub.ui.consoleui.ConsoleUI
 * @see SwingWindowUI
 * @author Thomas Archambeau, Eléonore Vaissaire
 */
public class MusicHub {

    IUserApplication app;

    /**
     * Creates a new object of type MusicHub
     */
    private MusicHub(AppType type) {

        switch (type){
            case CONSOLE:
                app = new ConsoleUI();
                break;
            case WINDOW:
                app = new SwingWindowUI();
                break;
        }


        app.run();
        
    }

    public static void main(String[] args) {

        if (args[0].equals("-c")) {
            new MusicHub(AppType.CONSOLE);
        }
        else{ //arg for graphical is -w
            new MusicHub(AppType.WINDOW);
        }

    }

    private enum AppType{
        CONSOLE(), WINDOW()
    }
}
