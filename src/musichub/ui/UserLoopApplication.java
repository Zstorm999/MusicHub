package musichub.ui;

/**
 * Represents a user application based on an internal loop.
 * @author Thomas Archambeau
 * @see musichub.ui.UserApplication
 * @see musichub.ui.consoleui.ConsoleUI
 */
public abstract class UserLoopApplication implements UserApplication{

    protected boolean mustEnd;

    /**
     * Put here everything that should be done on the application's startup
     */
    public abstract void init();

    /**
     * Put here everything to run continuously
     */
    public abstract void update();


    /**
     * Put here everything that should be done just before the application's end
     */
    public abstract void end();


    /**
     * Returns a boolean that indicates if the program has to stop
     * @return a boolean that indicates if the program has to stop
     */
    public boolean mustEnd() {
        return mustEnd;
    }


    @Override
    public void show(){
        init();

        while(!mustEnd()){
            update();
        }

        end();
    }


}
