/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trash;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;


public class imagePanel extends JPanel{
    private Image image;
    private final int X_BOARD = 200;
    private final int Y_BOARD = 200;        
    public imagePanel(String path){        
        initImage(path);                                
    }
    private void initImage(String path){       
        ImageIcon ii = new ImageIcon(path);
        image = ii.getImage().getScaledInstance(X_BOARD, Y_BOARD, Image.SCALE_SMOOTH);        
    }       
        
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);        
        g.drawImage(image, 0, 0, this);        
    }    
}
