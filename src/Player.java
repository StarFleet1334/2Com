import java.awt.*;
import java.util.Random;

public class Player extends GameObject{
    Handler handler;
    Color color;

    public Player(int x, int y, ID id,Handler handler, Color color) {
        super(x, y, id);
        this.handler = handler;
        this.color = color;




    }

    @Override
    public void tick() {
        y += velY;
        y = Game.clamp(y,80,Game.HEIGHT-105);
        handler.addObject(new Trail(x,y,ID.Trail,color,16,16,0.1f,handler));
    }


    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x,y,20,80);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,20,80);
    }

}
