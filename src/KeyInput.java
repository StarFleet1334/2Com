import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
    private Handler handler;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i = 0; i < handler.objects.size(); i++){
            GameObject temp = handler.objects.get(i);
            if(temp.id == ID.Player_One){
                if(key == KeyEvent.VK_UP){
                    temp.setVelY(-5);
                } else if(key == KeyEvent.VK_DOWN){
                    temp.setVelY(5);
                }
            } else if(temp.id == ID.Player_Two){
                if(key == KeyEvent.VK_W){
                    temp.setVelY(-5);
                } else if(key == KeyEvent.VK_S){
                    temp.setVelY(5);
                }
            } else if(key == KeyEvent.VK_ESCAPE){
                System.exit(1);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i = 0; i < handler.objects.size(); i++){
            GameObject temp = handler.objects.get(i);
            if(temp.id == ID.Player_One){
                if(key == KeyEvent.VK_UP){
                    temp.setVelY(0);
                } else if(key == KeyEvent.VK_DOWN){
                    temp.setVelY(0);
                }
            } else if(temp.id == ID.Player_Two){
                if(key == KeyEvent.VK_W){
                    temp.setVelY(0);
                } else if(key == KeyEvent.VK_S){
                    temp.setVelY(0);
                }
            }
        }











    }
}
