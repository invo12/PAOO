import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen {
    //game
    private JFrame frame;
    private Canvas canvas;  //ca sa putem desena imagini

    //menu
    private JPanel menuPanel;
    private JButton startButton,quitButton;

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
        canvas.setVisible(false);

        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(width,height));
        menuPanel.setMaximumSize(new Dimension(width,height));
        menuPanel.setMinimumSize(new Dimension(width,height));
        menuPanel.setLayout(new GridLayout(2,1));

        startButton = new JButton("START");
        quitButton = new JButton("QUIT");
        startButton.setFocusable(false);
        quitButton.setFocusable(false);

        menuPanel.add(startButton);
        menuPanel.add(quitButton);
        menuPanel.setVisible(true);
        menuPanel.setFocusable(false);

        frame.setLayout(new CardLayout());
        frame.add(menuPanel);
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
    public JPanel getMenuPanel(){ return menuPanel;}
    public JButton getStartButton(){return startButton;}
    public JButton getQuitButton(){return quitButton;}
}
