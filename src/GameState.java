import java.awt.*;

public class GameState extends State{

    private Player player;
    private Map map;
    private UI ui;

    int numberOfDeaths = 0;

    public GameState(Handler handler)
    {
        super(handler);
        player = new Player(handler,100,100,64,64);
        map = new Map(handler,"src/Resources/level1.txt");
        ui = new UI(0);
        handler.setMap(map);
        handler.setUI(ui);
    }

    @Override
    public void update() {
        map.update();
        player.update();
    }

    @Override
    public void render(Graphics g)
    {
        map.render(g);
        player.render(g);
        ui.render(g);
    }
    public void gameOver()
    {
        numberOfDeaths++;
        handler.getGame().updateScore(numberOfDeaths);
    }
}
