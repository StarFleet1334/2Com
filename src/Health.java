import javax.swing.*;
import java.awt.*;

public class Health {
    public static int bar = 100;
    public void tick() {
        bar = Game.clamp(bar ,0,100);

    }

    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(10,20,100,20);
        g.setColor(Color.cyan);
        g.fillRect(10,20,bar,20);
        g.setColor(Color.WHITE);
        g.drawRect(10,20,100,20);
    }



}
