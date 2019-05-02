import java.awt.*;

public class FinishState extends State {

    public FinishState(Handler handler)
    {
        super(handler);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.finishBackground,0,0,handler.getWidth(),handler.getHeight(),null);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g.drawString("AI CASTIGAT CU " + Assets.numberOfDeaths +" MORTI",handler.getWidth()/5,handler.getHeight()/2);
    }

    @Override
    public void gameOver() {

    }

    @Override
    public void nextLevel() {

    }
}
