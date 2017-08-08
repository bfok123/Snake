import java.awt.Graphics;

public class Food {
    private int x, y;
    
    public Food() {
        x = (int)(Math.random()*40)*11;
        y = 29 + (int)(Math.random()*40)*11;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setX(int newX) {
        x = newX;
    }
    
    public void setY(int newY) {
        y = newY;
    }
}
