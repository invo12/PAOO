import java.awt.*;

public class UI {

    private String scoreString;

    public UI(int numberOfDeaths)
    {
        setNumberOfDeaths(numberOfDeaths);
    }

    public void render(Graphics g)
    {
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman",Font.BOLD,30));

        g.drawString(scoreString,30,30);
    }

    public void setNumberOfDeaths(int numberOfDeaths)
    {
        scoreString = "Deaths: " + numberOfDeaths;
    }
}
