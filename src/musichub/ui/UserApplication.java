package musichub.ui;

import musichub.business.ElementNotFoundException;

import java.text.ParseException;

/**
 *
 */
public interface UserApplication {

    /**
     * Put here everything that should be done on the application's startup
     */
    void init();

    /**
     * Put here everything to run continuously
     */
    void update();

    /**
     * Put here everything that should be done just before the application's end
     */
    void end();

    /**
     * Returns a boolean that indicates if the program has to stop
     * @return a boolean that indicates if the program has to stop
     */
    boolean mustEnd();
}
