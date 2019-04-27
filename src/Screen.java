import javax.swing.*;
import java.awt.*;

public class Screen {
    private JFrame frame;
    private Canvas canvas;  //ca sa putem desena imaginile

    private String title = "Gioc";
    private int width,height;

    public Screen(int width,int height)
    {
        this.width = width;
        this.height = height;

        createWindow();
    }

    private void createWindow()
    {
        frame = new JFrame(title);
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);  //centreaza fereastra
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));//te asiguri ca canvasul
        canvas.setMaximumSize(new Dimension(width,height));//va avea mereu marimea
        canvas.setMinimumSize(new Dimension(width,height));//width x height
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();       //da resize la fereastra ca sa putem vedea tot canvasul
    }

    public Canvas getCanvas()
    {
        return canvas;
    }
    public void setTitle(String title)
    {
        frame.setTitle(this.title + title);
    }
    public JFrame getFrame()
    {
        return frame;
    }
}
