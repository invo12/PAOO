import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Tile {

    public static Tile[] tiles = new Tile[256];
    public static Tile platformTile = new PlatformTile(0);
    public static Tile backgroundTile = new BackgroundTile(1);
    public static Tile waterTile = new WaterTile(2);
    public static Tile finishTile = new FinishTile(3);

    public static final int TILE_WIDTH = 64,TILE_HEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture,int id)
    {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public boolean isSolid()
    {
        return false;
    }
    public boolean isDeath(){return false;}
    public boolean isFinish(){return false;}
    public void update()
    {

    }
    public void render(Graphics g,int x,int y)
    {
        g.drawImage(texture,x,y,TILE_WIDTH,TILE_HEIGHT,null);
    }
}
