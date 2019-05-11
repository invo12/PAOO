import sun.plugin2.util.ColorUtil;

import javax.swing.*;
import javax.swing.plaf.basic.DefaultMenuLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen {
    //game
    private JFrame frame;
    private Canvas canvas;  //ca sa putem desena imagini

    //menu
    private JPanel menuPanel,containerPanel;
    private JButton startButton,continueButton,quitButton;
    private JLabel gameTitle;

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

        gameTitle = new JLabel("Patratel");
        startButton = new JButton("START");
        continueButton = new JButton("CONTINUE");
        quitButton = new JButton("QUIT");

        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(width,height));
        menuPanel.setMaximumSize(new Dimension(width,height));
        menuPanel.setMinimumSize(new Dimension(width,height));
        menuPanel.setBackground(Color.BLACK);
        menuPanel.setLayout(new GridBagLayout());

        containerPanel = new JPanel();
        containerPanel.setPreferredSize(new Dimension(width/2,height));
        containerPanel.setBackground(Color.BLACK);
        containerPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        containerPanel.setFocusable(false);
        containerPanel.setLayout(null);

        int containerWidth = containerPanel.getPreferredSize().width;
        int containerHeight = containerPanel.getPreferredSize().height;

        containerPanel.add(gameTitle);
        gameTitle.setFont(new Font("Courier New",Font.BOLD,50));
        gameTitle.setForeground(Color.white);
        gameTitle.setBounds(80,height/8,containerWidth,50);
        gameTitle.setAlignmentX(JLabel.CENTER);

        containerPanel.add(startButton);
        startButton.setBackground(Color.white);
        startButton.setPressedIcon(null);
        startButton.setBounds(containerWidth/8,containerHeight/4,3*containerWidth/4,100);
        startButton.setFocusable(false);

        containerPanel.add(continueButton);
        continueButton.setBackground(Color.white);
        continueButton.setBounds(containerWidth/8,containerHeight/2,3*containerWidth/4,100);
        continueButton.setFocusable(false);

        containerPanel.add(quitButton);
        quitButton .setBackground(Color.white);
        quitButton.setBounds(containerWidth/8,3*containerHeight/4,3*containerWidth/4,100);
        quitButton.setFocusable(false);

        //containerPanel.add(startButton);
        //containerPanel.add(quitButton);
        menuPanel.add(containerPanel);
        menuPanel.setVisible(true);
        menuPanel.setFocusable(false);

        frame.setResizable(false);
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
    public JButton getContinueButton(){return continueButton;}
    public JButton getQuitButton(){return quitButton;}
}
