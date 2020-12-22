package musichub.main;

import musichub.main.consoleui.ConsoleApplication;

public class MusicHub {

    ConsoleApplication app;

    private MusicHub(){
        app = new ConsoleApplication();

        app.init();

        while(!app.mustEnd()){
            app.update();
        }

        app.end();
        
    }

    public static void main(String[] args) {
        new MusicHub();
    }
}
