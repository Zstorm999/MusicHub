package musichub.main.consoleui;

import musichub.main.Application;

public class ConsoleApplication {
    
    private Application app;
    private boolean mustEnd;

    public ConsoleApplication(){
        app = new Application();
    }

    public boolean mustEnd(){
        return mustEnd;
    }

    /**
     * Put here everything that should be done on the application's startup
     */
    public void init(){
        app.loadAll();

    }

    public void update(){
        mustEnd = true;
    }

    /**
     * Put here everything that should be done just before the application's end
     */
    public void end(){
        app.saveAll();


    }

}
