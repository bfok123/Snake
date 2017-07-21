/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

/**
 *
 * @author Brandon
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class MainFrame extends JPanel implements KeyListener, ActionListener {
    Timer t = new Timer(5, this);
    Snake snake = new Snake();
    Food food = new Food();
    List<Snake> snakeBody = new ArrayList(0);
    private int points;;
    int lastKeyCode;
    
    public MainFrame(){
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        points = 0;
        snakeBody.add(snake);
    }   
     
    public static void main(String[] args){
        MainFrame frame = new MainFrame();
        JFrame mainFrame = new JFrame("Snake");
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(456, 508);
        mainFrame.add(frame);
        mainFrame.addKeyListener(frame);
    }
  
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 29, 440, 440);
        g.drawString("Points: " + points, 220, 20);
        
        g.setColor(Color.WHITE);
        g.fillRect(snake.getX(), snake.getY(), 10, 10);
        
        g.setColor(Color.BLUE);
        g.fillRect(food.getX(), food.getY(), 10, 10);
       
        if(foodEaten()) {
            Snake newTail = new Snake();
            newTail.setX(food.getX());
            newTail.setY(food.getY());
            snakeBody.add(newTail);
            food.setX((int)(Math.random()*40)*11);
            food.setY(29 + (int)(Math.random()*40)*11);
            points++;
        }
        
        if(snakeBody.size() > 1) {
            for(int s = snakeBody.size() - 1; s > 0; s--) {
                g.setColor(Color.WHITE);
                g.fillRect(snakeBody.get(s).getX(), snakeBody.get(s).getY(), 10, 10);
                g.fillRect(snake.getX(), snake.getY(), 10, 10);
                
                snakeBody.get(s).setOldX(snakeBody.get(s).getX());
                snakeBody.get(s).setOldY(snakeBody.get(s).getY());
                snakeBody.get(s).setX(snakeBody.get(s - 1).getX());
                snakeBody.get(s).setY(snakeBody.get(s - 1).getY());
            }
        }
    }
    
    public boolean foodEaten() {
        return (((Math.abs(snake.getX() - food.getX())) < 6) && ((Math.abs(snake.getY() - food.getY())) < 6));
    }
    
    public void dead() {
        boolean isDead = false;
        for(int s = 1; s < snakeBody.size(); s++) {
            if((snakeBody.get(s).getX() == snake.getX()) && (snakeBody.get(s).getY() == snake.getY())) {
                isDead = true;
            }
        }
        
        if((snake.getX() >= 440) || (snake.getX() < 0) || (snake.getY() < 29) || (snake.getY() >= 469)) {
            isDead = true;
        }
        
        if (isDead) {
            points = 0;
            snakeBody.removeAll(snakeBody);
            snake.setX(0);
            snake.setY(29);
            snake.setVelX(0);
            snake.setVelY(0);
            snake.setOldX(0);
            snake.setOldY(0);
            snakeBody.add(snake);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Thread.sleep(50);
        } catch(InterruptedException ie) { 
        }
        snake.setOldX(snake.getX());
        snake.setOldY(snake.getY());
        snake.setX(snake.getX() + snake.getVelX());
        snake.setY(snake.getY() + snake.getVelY());
        dead();
        repaint();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        
        if (c == KeyEvent.VK_LEFT) {
            if ((snakeBody.size() > 1) && (lastKeyCode == KeyEvent.VK_RIGHT)) {
                //do nothing if snake is greater than 1 in length and the user tries to go in the opposite direction
            } else {
                snake.setVelX(-11);
                snake.setVelY(0);
                lastKeyCode = KeyEvent.VK_LEFT;
            }
        }
        
        if (c == KeyEvent.VK_UP) {
            if ((snakeBody.size() > 1) && (lastKeyCode == KeyEvent.VK_DOWN)) {
                
            } else {
                snake.setVelX(0);
                snake.setVelY(-11);
                lastKeyCode = KeyEvent.VK_UP;
            }
        }
        
        if (c == KeyEvent.VK_RIGHT) {
            if ((snakeBody.size() > 1) && (lastKeyCode == KeyEvent.VK_LEFT)) {
                
            } else {
                snake.setVelX(11);
                snake.setVelY(0);
                lastKeyCode = KeyEvent.VK_RIGHT;
            }
        }
        
        if (c == KeyEvent.VK_DOWN) {
            if ((snakeBody.size() > 1) && (lastKeyCode == KeyEvent.VK_UP)) {
       
            } else {
                snake.setVelX(0);
                snake.setVelY(11);
                lastKeyCode = KeyEvent.VK_DOWN;
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {}
}
