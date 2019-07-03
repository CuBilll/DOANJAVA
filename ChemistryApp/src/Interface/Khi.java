/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Khi{
    private Image image;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean vis;
    
    public Khi(int x, int y, int width, int height){                
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;                
        vis = true;                
        loadImage("images/white.jpg");
        
    }
    
    private void loadImage(String path){
        ImageIcon ii = new ImageIcon(path);
        image = ii.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);        
    }
    
    
    public void move(){
        Random rd = new Random();
        int dx = rd.nextInt(20) - 10;
        int dy = rd.nextInt(4);
        x += dx;
        y -= dy;
        
        if (y < 0){
            vis = false;
        }
    }
    
    public int getX(){
        return x;
    }            
    public int getY(){
        return y;
    }
    
    public Image getImage(){
        return this.image;
    }
    
    public boolean getVisible(){
        return vis;
    }
}
