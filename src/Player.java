import java.awt.*;
import java.util.Collections;

public class Player extends Entity {

    private final int DEFAULT_WIDTH = 64,DEFAULT_HEIGHT = 64;
    private float xMove,yMove;
    private int speed = 6;
    private int gravityScale = 5;
    private int jumpSpeed = 50;
    private boolean grounded = false;
    private boolean jumping = false;
    private int numberOfFrames = 0;

    private float initialX,initialY;
    public Player(Handler handler,float x,float y,int width,int height)
    {
        super(handler,x,y,width,height);
        initialX = x;
        initialY = y;
    }

    @Override
    public void update() {
        checkIfDead();
        input();
        move();
        checkIfDead();
        handler.getCamera().CenterOnPlayer(this);
    }

    private void move()
    {
        moveX();
        moveY();
    }
    private void moveX()
    {
        if(xMove > 0)
        {
            int xx = (int)(x + xMove + box.width) / Tile.TILE_WIDTH;

            if(!collisionWithTile(xx,(int)y/Tile.TILE_HEIGHT) && !collisionWithTile(xx,(int)(y + box.height) / Tile.TILE_HEIGHT))
            {
                x+=xMove;
            }
            else
            {
                x = xx * Tile.TILE_WIDTH - box.width - 1;
            }
        }
        else if(xMove < 0)
        {
            int xx = (int)(x + xMove) / Tile.TILE_WIDTH;

            if(!collisionWithTile(xx,(int)y/Tile.TILE_HEIGHT) && !collisionWithTile(xx,(int)(y + box.height) / Tile.TILE_HEIGHT))
            {
                x+=xMove;
            }
        }
    }
    private void moveY()
    {
        if(yMove < 0)
        {
            int yy = (int)(y + yMove)/Tile.TILE_HEIGHT;
            if(!collisionWithTile((int)x/Tile.TILE_WIDTH,yy) && !collisionWithTile((int)(x + box.width)/Tile.TILE_WIDTH,yy))
            {
                y+=yMove;
            }
        }
        else
        {
            int yy = (int)(y + gravityScale + box.height)/Tile.TILE_HEIGHT;
            if(jumping)
            {
                if(numberOfFrames < 4)
                {
                    y-=jumpSpeed/4;
                    numberOfFrames++;
                }
                else
                {
                    jumping = false;
                }
            }
            else if(!collisionWithTile((int)x/Tile.TILE_WIDTH,yy) && !collisionWithTile((int)(x + box.width)/Tile.TILE_WIDTH,yy))
            {
                y+=gravityScale;
                grounded = false;
            }
            else
            {
                numberOfFrames = 0;
                grounded = true;
                y = yy * Tile.TILE_HEIGHT - box.height - 1;
            }
        }

    }

    private boolean collisionWithTile(int x,int y)
    {
        return handler.getMap().getTile(x,y).isSolid();
    }

    private boolean deathCollisionWithTile(int x,int y)
    {
        return handler.getMap().getTile(x,y).isDeath();
    }

    private void input()
    {
        xMove = 0;
        yMove = 0;
        if(handler.getKeyboardManager().up)
        {
            if(grounded)
            {
                yMove=-jumpSpeed;
                jumping = true;
                grounded = false;
            }
        }
        if(handler.getKeyboardManager().left)
        {
            xMove = -speed;
        }
        if(handler.getKeyboardManager().right)
        {
            xMove = speed;
        }
    }
    private void checkIfDead()
    {
        int yy = (int)(y + gravityScale + box.height)/Tile.TILE_HEIGHT;
        if(deathCollisionWithTile((int)x/Tile.TILE_WIDTH,yy) || deathCollisionWithTile((int)(x + box.width)/Tile.TILE_WIDTH,yy))
        {
            x = initialX;
            y = initialY;
            if(State.getState() != null)
            {
                State.getState().gameOver();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player,(int)(x - handler.getCamera().getXOffset()),(int)(y - handler.getCamera().getYOffset()),width,height,null);

        g.setColor(Color.red);
    }

    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
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
