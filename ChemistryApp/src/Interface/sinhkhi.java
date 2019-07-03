/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.Timer;


public class sinhkhi extends JPanel implements ActionListener{
    private int x;
    private int y;
    private int WIDTH;
    private int HEIGHT;
    
    private Timer timer;
    private Vector<Khi> luongkhi;
    
    public sinhkhi(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.WIDTH = width;
        this.HEIGHT = height;
        
        setLocation(this.x, this.y);
        setSize(this.WIDTH, this.HEIGHT);
        
        float []a = Color.RGBtoHSB(204, 255, 204, null);
        setBackground(Color.getHSBColor(a[0], a[1], a[2]));
        createKhi(100);
        setVisible(true);
        timer = new Timer(50, (ActionListener) this);    
        timer.start();
    }    
    
    @Override
    public void paintComponent(Graphics g){        
        super.paintComponent(g); 
        doDrawing(g);
    }   

    /**
     *
     * @param g
     */    
    public void doDrawing(Graphics g){   
        System.out.println(luongkhi.size());
        for (Khi k : luongkhi){
            g.drawImage(k.getImage(), k.getX(), k.getY(), null);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (luongkhi.size() == 0){
            timer.stop();
            return;
        }
        
        Vector<Khi> tmp = new Vector<Khi>();
        for (Khi k : luongkhi){
            k.move();
            if (k.getVisible() == true){
                tmp.add(k);
            }
        }
        luongkhi = tmp;                        
        System.out.println(luongkhi.size());
        repaint();
    }
 
    public void createKhi(int sl){
        Random rd = new Random();
        luongkhi = new Vector<Khi>();
        for (int i=0; i<sl; i++){            
            int xx = rd.nextInt(WIDTH);
            int yy = this.HEIGHT;
            luongkhi.add(new Khi(xx, yy, 5, 5));
        }        
    }
}
