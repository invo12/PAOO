public class Handler {

    private Game game;
    private Map map;

    public Handler(Game game)
    {
        this.game = game;
    }

    public  Camera getCamera()
    {
        return game.GetCamera();
    }

    public KeyboardManager getKeyboardManager()
    {
        return game.GetKeyboardManager();
    }

    public Game getGame()
    {
        return game;
    }
    public Map getMap()
    {
        return map;
    }
    public void setMap(Map map)
    {
        this.map = map;
    }

    public int getWidth()
    {
        return game.width;
    }
    public int getHeight()
    {
        return game.height;
    }
}
