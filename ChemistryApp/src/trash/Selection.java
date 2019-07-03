/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trash;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Selection extends JPanel{
    
    protected String text;
    protected int x, y;
    private final int WIDTH = 200;
    private final int HEIGHT = 200;
    protected Image image;            
    
    public Selection(String text, String path, int x, int y){       
        this.x = x;
        this.y = y;
        loadImage(path);
        this.text = text;
    }
    
    private void loadImage(String path){
        ImageIcon ii = new ImageIcon(path);
        image = ii.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);        
    }
    
    public Image getImage(){
        return image;
    }
    
    public String getText(){
        return text;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, x, y, this);
    }
}
