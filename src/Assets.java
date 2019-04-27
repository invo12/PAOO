import java.awt.image.BufferedImage;

public class Assets {
    private static final int width = 32,height = 32;

    public static BufferedImage platform,background,water,player;
    public static void init()
    {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("Resources/spriteSheet.png"));
        platform = sheet.crop(0,0,width,height);
        background = sheet.crop(width,0,width,height);
        player = sheet.crop(0,height,width,height);
        water = sheet.crop(width,height,width,height);
    }
}
