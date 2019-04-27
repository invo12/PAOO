import java.awt.image.BufferedImage;

public class PlatformTile extends Tile {
    public PlatformTile(int id) {
        super(Assets.platform, id);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }
}
