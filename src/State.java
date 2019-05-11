import java.awt.*;

public abstract class State {

    private static State currentState = null;

    public static void setState(State state)
    {
        currentState = state;
    }

    public static State getState()
    {
        return currentState;
    }

    //separated
    protected Handler handler;
    public State(Handler handler)
    {
        this.handler = handler;
    }

    public abstract void update();
    public abstract void render(Graphics g);
    public abstract void gameOver();
    public abstract void nextLevel();
    public Player getPlayer(){return null;};
}
