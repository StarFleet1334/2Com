import java.awt.*;

public class SeconddHealth {
    public static int bar = 100;

    public void tick() {
        bar = Game.clamp(bar,0,100);

    }
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(500,20,100,20);
        g.setColor(Color.CYAN);
        g.fillRect(500,20,bar,20);
        g.setColor(Color.WHITE);
        g.drawRect(500,20,100,20);
    }

}
