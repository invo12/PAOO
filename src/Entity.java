import java.awt.*;

public abstract class Entity {

    protected Handler handler;
    protected float x,y;
    int width,height;
    protected Rectangle box;

    public Entity(Handler handler,float x,float y,int width,int height)
    {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        box = new Rectangle(0,0,width,height);
    }

    public abstract void update();
    public abstract void render(Graphics g);
}
