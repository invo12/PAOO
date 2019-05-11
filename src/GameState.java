import java.awt.*;

public class GameState extends State{

    private Player player;
    private Map map;
    private UI ui;

    private int currentLevel;
    private final int numberOfLevels = 2;

    public GameState(Handler handler)
    {
        super(handler);
        player = new Player(handler,100,100,64,64);
        map = new Map(handler,"src/Resources/level1.txt");
        ui = new UI(0);
        handler.setMap(map);
        handler.setUI(ui);
        currentLevel = 1;
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
    @Override
    public void gameOver()
    {
        GameVariables.numberOfDeaths++;
        handler.getGame().updateScore(GameVariables.numberOfDeaths);
    }
    @Override
    public void nextLevel()
    {
        currentLevel = ++GameVariables.currentLevel;
        if(currentLevel > numberOfLevels)
        {
            GameVariables.currentLevel = GameVariables.currentLevel - 1;
            handler.getGame().FinishTheGame();
        }
        else
        {
            handler.getMap().loadWorld("src/Resources/level" + currentLevel + ".txt");
        }
    }
    public Player getPlayer()
    {
        return player;
    }
}
