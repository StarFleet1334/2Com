import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable{
    public static final int WIDTH = 640, HEIGHT = 780;
    private boolean running = false;
    private Thread thread;
    private Handler handler;
    public static ArrayList<String> names = new ArrayList<>();
    private Health health;
    private SeconddHealth seconddHealth;
    private Color color,player_one_Color,player_Two_Color;

    public Game() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        new Window(WIDTH,HEIGHT,"Another Game!",this);
        health = new Health();
        seconddHealth = new SeconddHealth();

        handler.addObject(new Player(2,HEIGHT/2-50,ID.Player_One,handler, player_one_Color));
        handler.addObject(new Player(WIDTH-38,HEIGHT/2-50,ID.Player_Two,handler,player_Two_Color));
        handler.addObject(new Ball(WIDTH/2-32,HEIGHT/2-32,ID.Ball,handler,color));
        handler.addObject(new Block(0,60,ID.Block,WIDTH,20,handler));


    }

    public void start() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        thread = new Thread(this);
        ImageIcon icon = new ImageIcon("src\\First-Game Stuff\\ping-pong-masters.jpg");
        ImageIcon first = new ImageIcon("src\\First-Game Stuff\\s-l300.jpg");
        ImageIcon second = new ImageIcon("src\\First-Game Stuff\\cold.jpg");
        String x = JOptionPane.showInputDialog(this, icon, "Input \"YES\" if you want to play!", JOptionPane.PLAIN_MESSAGE);
        if(x.equals("YES")) {
            String firstName = JOptionPane.showInputDialog(this,first,"Player 1: Enter your Name: ",JOptionPane.PLAIN_MESSAGE);
            names.add(firstName);
            String secondName = JOptionPane.showInputDialog(this,second,"Player 2: Enter your name: ",JOptionPane.PLAIN_MESSAGE);
            names.add(secondName);
            Color col = JColorChooser.showDialog(null,"Choose a color for a ball", Color.GRAY);
            if(col != null){
                color =  col;
            }
            Color p1 = JColorChooser.showDialog(null,"Choose a color " + names.get(0), Color.GRAY);
            if(p1 != null){
                player_one_Color = p1;
            }
            Color p2 = JColorChooser.showDialog(null,"Choose a color " + names.get(1), Color.GRAY);
            if(p2 != null){
                player_Two_Color = p2;
            }
            File file = new File("src\\First-Game Stuff\\song.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            thread.start();

        } else {
            JOptionPane.showMessageDialog(this,"მაშინ გააჯვი!!!!");
            System.exit(1);
        }
        running = true;
    }
    public void stop(){
        try{
            thread.join();
            running = false;
        } catch (InterruptedException e){
            e.getStackTrace();
        }
    }



    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                try {
                    tick();
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                    e.printStackTrace();
                }
                delta--;
            }
            if(running)
                render();
            frames++;
            if(System.currentTimeMillis() -timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    public void tick() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        handler.tick();
        health.tick();
        seconddHealth.tick();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);
        health.render(g);
        seconddHealth.render(g);

        bs.show();
        g.dispose();
    }

    public static int clamp(int var, int min, int max){
        if(var >= max){
            return var = max;
        } else if(var <= min){
            return var = min;
        } else{
            return var;
        }
    }






    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Game();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
