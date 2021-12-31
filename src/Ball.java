import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Ball extends GameObject {
    private Handler handler;
    private Color color;


    public Ball(int x, int y, ID id, Handler handler, Color color) {
        super(x, y, id);
        this.handler = handler;
        this.color = color;

        velX = 5;
        velY = 5;
    }
    @Override
    public void tick() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        x += velX;
        y += velY;
        x = Game.clamp(x,0,Game.WIDTH-26);
        y = Game.clamp(y,83,Game.HEIGHT-70);
        File file = new File("src\\First-Game Stuff\\sogood.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        if(x <= 0) {
            clip.start();
            velX = -velX;
            Health.bar -= 10;
            if(Health.bar == 0){
                String firstName = Game.names.get(1);
                JOptionPane.showMessageDialog(null,"PLayer 2 called: " + firstName + " has Won!");
                JOptionPane.showMessageDialog(null,"GAME OVER!");
                System.exit(1);
            }
        } else if(x >= Game.WIDTH-26){
            clip.start();
            velX = -velX;
            SeconddHealth.bar -= 10;
            if(SeconddHealth.bar == 0){
                String secondName = Game.names.get(0);
                JOptionPane.showMessageDialog(null,"Player 1 called: " + secondName + " has won!");
                JOptionPane.showMessageDialog(null,"GAME OVER!");
                System.exit(1);
            }
      } else  if(y <= 83 || y >= Game.HEIGHT-70) {
            clip.start();
            velY = -velY;
        }
        collision();
        accelaration();
        handler.addObject(new Trail(x,y,ID.Trail,color,16,16,0.08f,handler));



    }

    public void collision() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
      for(int i = 0; i < handler.objects.size(); i++){
            GameObject temp = handler.objects.get(i);
            if(temp.getId() == ID.Player_One || temp.getId() == ID.Player_Two){
                if(getBounds().intersects(temp.getBounds())){
                    File file = new File("src\\First-Game Stuff\\sogood.wav");
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                    velX = -velX;
                    velY = -velY;
                }
            }
        }
    }


    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval(x,y,16,16);
    }

    public void accelaration(){
        for(int i = 0; i < handler.objects.size(); i++){
            GameObject temp = handler.objects.get(i);
            if(temp.getId() == ID.Player_One || temp.getId() == ID.Player_Two){
                if(getBounds().intersects(temp.getBounds())) {
                    if (velX <= 10 && velY <= 10) {
                        if (velX < 0 && velY < 0) {
                            velX -= 1;
                            velY -= 1;
                        } else {
                            velX += 1;
                            velY += 1;
                        }
                    } else{
                        velX = 5;
                        velY = 5;
                    }
                }
            }
        }
    }


    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,16,16);
    }


    public void spawn_Ball(){
        if(x < 0 || x > Game.WIDTH-26 || y < 83 || y > Game.HEIGHT-70){
            for(int i = 0; i < handler.objects.size(); i++){
                GameObject temp = handler.objects.get(i);
                if(temp.getId() == ID.Ball){
                    handler.removeObject(temp);
                }
            }
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
