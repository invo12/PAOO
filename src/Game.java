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

    //saver
    private SaveInfo loadInfo;
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
                newGame();
            }
        });
        screen.getQuitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                running = false;
            }
        });
        screen.getContinueButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGame();
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
        if(keyboardManager.save)
        {
            if(State.getState() == gameState)
            {
                System.out.println("Pressed i");
                saveGame();
            }
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
        long elapsedTime;
        long frameDurationMilis = 16;

        while(running)
        {
            now = System.nanoTime();
            elapsedTime = (now - lastTime);
            delta += (now - lastTime)/timePerUpdate;
            timer += now - lastTime;
            lastTime = now;
            if(delta >= 1) {
                update();
                delta--;
             }
            render();
            frames++;
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
    private void switchToGameView()
    {
        screen.getCanvas().setVisible(true);
        screen.getMenuPanel().setVisible(false);
        State.setState(gameState);
    }
    private void newGame()
    {
        loadInfo = Loader.Load();
        gameState.getPlayer().setX(100);
        gameState.getPlayer().setY(100);
        handler.getMap().loadWorld("src/Resources/level1.txt");
        GameVariables.currentLevel = 1;
        updateScore(0);
        GameVariables.numberOfDeaths = 0;
        switchToGameView();
    }
    private void saveGame()
    {
        loadInfo.playerX = (int)gameState.getPlayer().getX();
        loadInfo.playerY = (int)gameState.getPlayer().getY();
        loadInfo.currentLevel = GameVariables.currentLevel;
        loadInfo.numberOfDeaths = GameVariables.numberOfDeaths;
        Loader.Save(loadInfo);
    }
    private void loadGame()
    {
        loadInfo = Loader.Load();
        gameState.getPlayer().setX(loadInfo.playerX);
        gameState.getPlayer().setY(loadInfo.playerY);
        handler.getMap().loadWorld("src/Resources/level" + loadInfo.currentLevel + ".txt");
        GameVariables.currentLevel = loadInfo.currentLevel;
        updateScore(loadInfo.numberOfDeaths);
        GameVariables.numberOfDeaths = loadInfo.numberOfDeaths;
        switchToGameView();
    }
}
//drawRect - deseneaza un dreptunghi gol
//fillRect - deseneaza un dreptunghi plin
//setClor - seteaza culoarea de desenat
//drawImage - deseneaza imaginea specificata