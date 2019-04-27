public class Camera {

    private float xOffset,yOffset;  //cat de mult ne indepartam de pozitia initiala
    private Handler handler;

    public Camera(Handler handler,float xOffset,float yOffset)
    {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void CenterOnPlayer(Player player)
    {
        xOffset = player.getX() - handler.getWidth()/2f + player.getWidth()/2f;
        yOffset = player.getY() - handler.getHeight()/2f + player.getHeight()/2f;
        keepInLimits();
    }

    public void move(float x,float y)
    {
        xOffset += x;
        yOffset += y;
    }

    public float getXOffset()
    {
        return xOffset;
    }
    public float getYOffset()
    {
        return yOffset;
    }

    public void keepInLimits()
    {
        if(xOffset < 0)
        {
            xOffset = 0;
        }
        else  if(xOffset > handler.getMap().getWidth() * Tile.TILE_WIDTH - handler.getWidth())
        {
            xOffset = handler.getMap().getWidth() * Tile.TILE_WIDTH - handler.getWidth();
        }
        if(yOffset < 0)
        {
            yOffset = 0;
        }
        else if(yOffset > handler.getMap().getHeight()*Tile.TILE_HEIGHT - handler.getHeight())
        {
            yOffset = handler.getMap().getHeight()*Tile.TILE_HEIGHT - handler.getHeight();
        }
    }
}
