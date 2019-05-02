import java.awt.*;

public class MenuState extends State {

    public MenuState(Handler handler)
    {
        super(handler);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.menuBackground,0,0,handler.getWidth(),handler.getHeight(),null);

    }

    @Override
    public void gameOver() {
    }

    @Override
    public void nextLevel()
    {
    }
}
