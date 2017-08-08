import javax.swing.JPanel;
import java.util.*;

public class Snake extends JPanel {
    private int x, y, velX, velY, oldX, oldY;
    
    public Snake() {
        x = 220;
        y = 239;
        velX = 0;
        velY = 0;
        oldX = x;
        oldY = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getVelX() {
        return velX;
    }
    
    public int getVelY() {
        return velY;
    }
    
    public int getOldX() {
        return oldX;
    }
    
    public int getOldY() {
        return oldY;
    }
    
    public void setX(int newX) {
        x = newX;
    }
    
    public void setY(int newY) {
        y = newY;
    }
    
    public void setVelX(int newVelX) {
        velX = newVelX;
    }
    
    public void setVelY(int newVelY) {
        velY = newVelY;
    }
    
    public void setOldX(int newOldX) {
        oldX = newOldX;
    }
    
    public void setOldY(int newOldY) {
        oldY = newOldY;
    }
}

