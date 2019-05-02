import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {
    private Screen screen;  //referinta la screen

    public int width,height;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;      //buffer strategy spune cum sa deseneze calculatorul pe ecran,cate buffere va folosi
    private Graphics g;             //ne lasa sa desenam grafica pe Canvas

    //States
    private State gameState;
    private State menuState;
    private State finishState;

    //Input
    private KeyboardManager keyboardManager;

    //Camera
    private Camera camera;

    //Handler
    private Handler handler;

    public Game(int width,int height)
    {
        this.width = width;
        this.height = height;
        keyboardManager = new KeyboardManager();
    }

    private void init()
    {
        screen = new Screen(width,height);
        screen.getFrame().addKeyListener(keyboardManager);
        Assets.init();
        screen.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.getCanvas().setVisible(true);
                screen.getMenuPanel().setVisible(false);
                State.setState(gameState);
            }
        });
        screen.getQuitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                running = false;
            }
        });

        handler = new Handler(this);
        camera = new Camera(handler,0,0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        finishState = new FinishState(handler);
        State.setState(menuState);
    }

    private void update()   //logica jocului
    {
        keyboardManager.update();
        if(State.getState() != null)
        {
            State.getState().update();
        }
        if(keyboardManager.escape)
        {
            screen.getCanvas().setVisible(false);
            screen.getMenuPanel().setVisible(true);
            State.setState(menuState);
        }
    }

    private void render()   //deseneaza pe ecran
    {
        bs = screen.getCanvas().getBufferStrategy();
        if(bs == null)  //prima oara cand ruleaza nu avem mun buffer strategy deci trebuie sa cream unul
        {
            screen.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();   //obtine metoda de a desena
        //curatam ecranul
        g.clearRect(0,0,width,height);

        //desenam de aici
        if(State.getState() != null)
            State.getState().render(g);
        //pana aici

        bs.show();      //da swap la buffere
        g.dispose();    //pentru garbage collector,scapi de grafica creata
    }

    public void run()
    {
        init();

        int fps = 60;
        double timePerUpdate = 1000000000.0 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int frames = 0;

        while(running)
        {
            now = System.nanoTime();
            delta += (now - lastTime)/timePerUpdate;
            timer += now - lastTime;
            lastTime = now;
            if(delta >= 1) {
                update();
                render();
                delta--;
                frames++;
             }
            if(timer >= 1000000000)
            {
                screen.setTitle(" " + frames + " fps");
                timer = 0;
                frames = 0;
            }
        }
        stop();
        System.exit(0);
    }

    public synchronized void start()    //cand pornesti sau opresti threaduri folosesti synchronized ca sa te asiguri ca nu pierzi nimic in proces
    {
        if(running)     //daca jocul ruleaza deja nu mai ai de pornit alt threead din greseala
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop()
    {
        if(!running)
            return;
        running = false;
        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    public void gameOver()
    {

    }

    public void updateScore(int newScore)
    {
        handler.getUI().setNumberOfDeaths(newScore);
    }
    public void FinishTheGame(){State.setState(finishState);}
    public KeyboardManager GetKeyboardManager()
    {
        return keyboardManager;
    }
    public Camera GetCamera()
    {
        return camera;
    }
}
//drawRect - deseneaza un dreptunghi gol
//fillRect - deseneaza un dreptunghi plin
//setClor - seteaza culoarea de desenat
//drawImage - deseneaza imaginea specificata