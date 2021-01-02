package musichub.main;

import musichub.business.ElementNotFoundException;
import musichub.main.consoleui.ConsoleApplication;

import java.io.IOException;
import java.text.ParseException;

public class MusicHub {

    ConsoleApplication app;

    private MusicHub() throws ElementNotFoundException, ParseException {
        app = new ConsoleApplication();

        app.init();

        while(!app.mustEnd()){
            app.update();
        }

        app.end();
        
    }

    public static void main(String[] args) throws ElementNotFoundException, ParseException {
        new MusicHub();
    }
}
