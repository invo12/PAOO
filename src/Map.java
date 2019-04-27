import java.awt.*;

public class Map {

    private Handler handler;
    private int width,height;
    private int[][] tiles;
    private int spawnX,spawnY;

    public Map(Handler handler,String path)
    {
        this.handler = handler;
        loadWorld(path);
    }

    public void update()
    {

    }
    public void render(Graphics g)
    {
        int xStart = (int)Math.max(0,handler.getCamera().getXOffset() / Tile.TILE_WIDTH); //the most left visible tile
        int xEnd = (int) Math.min(width,(handler.getCamera().getXOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);   //right
        int yStart = (int)Math.max(0,handler.getCamera().getYOffset() / Tile.TILE_HEIGHT);; //up
        int yEnd = (int) Math.min(height,(handler.getCamera().getYOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);;   //down
        for(int y = yStart;y < yEnd;++y)
        {
            for(int x = xStart;x < xEnd;++x)
            {
                getTile(x,y).render(g,(int)(x * Tile.TILE_WIDTH - handler.getCamera().getXOffset()),(int)(y * Tile.TILE_HEIGHT - handler.getCamera().getYOffset()));
            }
        }
    }

    public Tile getTile(int x,int y)
    {
        if(x < 0 || y < 0 || x >= width || y>=height)
            return Tile.backgroundTile;

        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
            return Tile.backgroundTile;
        return t;
    }

    private void loadWorld(String path)
    {
        String file = Utils.loadFile(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = 0;
        spawnY = 0;
        tiles = new int[width][height];
        for(int y = 0;y < height;y++)
        {
            for(int x=0;x < width;x++)
            {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 2]);
            }
        }
    }

    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
}
