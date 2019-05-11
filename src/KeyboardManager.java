import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardManager implements KeyListener {

    private boolean[] keys;
    public boolean up,down,left,right;
    public boolean escape;
    public boolean enter;
    public boolean save;

    public KeyboardManager()
    {
        keys = new boolean[256];
    }

    public void update()
    {
        up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_SPACE];
        down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
        escape = keys[KeyEvent.VK_ESCAPE];
        enter = keys[KeyEvent.VK_ENTER];
        save = keys[KeyEvent.VK_I];
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
