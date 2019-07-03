/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trash;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class mainInterface extends JFrame{
    
    private final int X_BOARD = 800;
    private final int Y_BOARD = 600;
    
    private final String[] paths = {"hoahoc/gd1.jpg", "hoahoc/dc.png", "hoahoc/canbang.png", "hoahoc/ptk.jpg"};
    
    public mainInterface(){
                        
        initUI();                                
        
    }
    private void initUI(){
        
        setTitle("Chemistry");
        setSize(X_BOARD, Y_BOARD);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);                                               
        
        setLayout(new GridLayout(3, 1));                
        
        add(new Selection("Dieu che", paths[0], 10, 10));        
        
        add(new Selection("Can bang", paths[2], 10, 10));
        
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(1, 3));
        imagePanel left = new imagePanel(paths[3]);        
        bottom.add(left);
        
        imagePanel center = new imagePanel(paths[1]);
        bottom.add(center);
        
        imagePanel right = new imagePanel(paths[3]);
        bottom.add(right);
                
        add(bottom);
        
        setVisible(true);
    }
}
