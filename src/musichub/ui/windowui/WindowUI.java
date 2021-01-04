package musichub.ui.windowui;

import musichub.ui.UserApplication;

public class WindowUI implements UserApplication {

    private boolean mustEnd;

    public WindowUI(){
        mustEnd = true;
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        mustEnd = false;
    }

    @Override
    public void end() {

    }

    @Override
    public boolean mustEnd() {
        return mustEnd;
    }
}
