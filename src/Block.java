import java.awt.*;

public class Block extends GameObject{
    Handler handler;

    int width,height;
    public Block(int x, int y, ID id,int width,int height,Handler handler) {
        super(x, y, id);
        this.width = width;
        this.height = height;
        this.handler = handler;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,width,height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }
}
