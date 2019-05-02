import java.awt.image.BufferedImage;

public class FinishTile extends Tile
{
    public FinishTile(int id) {
        super(Assets.finish, id);
    }

    @Override
    public boolean isFinish()
    {
        return true;
    }
}
