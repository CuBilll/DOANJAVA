/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import process.hienTuong;


public class Cup extends JPanel{    
    private int x, y;
    private int X_SIZE;
    private int Y_SIZE;    
    private JLabel space;
    private JLabel cl;
    private JLabel kt;
    private JLabel left;
    private JLabel right;
    private Color cl_cl;
    private Color cl_kt;    
    private hienTuong ht;
    
    public Cup(int x, int y, int width, int height){  
        X_SIZE = width;
        Y_SIZE = height;
        this.x = x;
        this.y = y;
        
        setLocation(this.x, this.y);        
        setLayout(new BorderLayout());
        setSize(X_SIZE, Y_SIZE ); 
        
        cl_cl = Color.white;
        cl_kt = Color.white;
        
        left = new JLabel();
        int xleft = 4;
        int yleft = Y_SIZE;
        left.setSize(xleft, yleft);
        left.setOpaque(true);
        left.setBackground(Color.BLACK);
        add(left);
                
                
        //add(kt);
        
        space = new JLabel();
        int sleft = X_SIZE - xleft;
        int sright = 5 * (Y_SIZE / 10);
        space.setSize(sleft, sright);
        float []a = Color.RGBtoHSB(204, 255, 204, null);
        space.setBackground(Color.getHSBColor(a[0], a[1], a[2]));
        space.setOpaque(true);
        space.setLocation(0, 0);
        
        cl = new JLabel();
        int lleft = X_SIZE - xleft;
        int lright = 4 * (Y_SIZE / 10);
        cl.setSize(lleft, lright);
        cl.setOpaque(true);
        cl.setLocation(0, sright);
        cl.setBackground(Color.white);
                
        kt = new JLabel();
        int kleft = X_SIZE - xleft;
        int kright = Y_SIZE / 10;
        kt.setSize(kleft, kright);
        kt.setOpaque(true);
        kt.setBackground(Color.white);
        kt.setLocation(0, sright + lright);               
        
        add(space);
        add(cl);
        add(kt);
        
                
        
        right = new JLabel();
        int xright = 4;
        int yright = Y_SIZE;
        right.setSize(xright, yright);
        right.setOpaque(true);
        right.setBackground(Color.black);
        add(right);               
        
        ht = null;
                
    }
    public static Color blend(Color color, Color c2, float ratio){
        double totalAlpha = color.getAlpha() + c2.getAlpha();
        double weight0 = color.getAlpha() / totalAlpha;
        double weight1 = c2.getAlpha() / totalAlpha;
        double r = weight0 * color.getRed() + weight1 * c2.getRed();
        double g = weight0 * color.getGreen() + weight1 * c2.getGreen();
        double b = weight0 * color.getBlue() + weight1 * c2.getBlue();                
        double a = Math.max(color.getAlpha(), c2.getAlpha());
        
        return new Color((int)r, (int)g, (int)b, (int)a);
    }
    public void setCL(String X, Color c){
        
    }
    public void addItem(String X, Color c){
        if (ht == null){
            ht = new hienTuong(X);
            setColorCL(c);
            setColorKT(c);                          
        }
        else{
                                    
            ht.add(X);                              
                                    
            // thay doi mau cho chat long
            List<Color> cls = ht.getColor();
            if (cls.size() == 0) setColorCL(Color.white);
            else{
                if (cls.get(0) != null) setColorCL(cls.get(0));
                for (int i=1; i<cls.size(); i++){   
                    Color ci = cls.get(i);
                    if (ci != null) addColor(ci, 1);
                }    
            }
            
            // thay doi mau cho ket tua
            List <String> kettua = ht.get_kettua();            
            if (kettua.size() > 0){
                X = kettua.get(0);                
                String colorOf = knowledge.knowledge.color(X);
                c = knowledge.knowledge.colors.get(colorOf);  
                setColorKT(c);
            }
            else{
                setLikeCL();
            }
        }
                                    
    }
    public void addItem(String X){
        String str_c = knowledge.knowledge.color(X);
        Color c = knowledge.knowledge.colors.get(str_c);
        addItem(X, c);
    }
    public void del_kt_k(){
        ht.del_kettua();
        ht.del_khi();
    }
    public void addColor(Color c2, float ratio){
        Color c = blend(cl_cl, c2, ratio);                
        cl_cl = c;                
    }           
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWidth(){
        return X_SIZE;                               
    }
    public int getHeight(){
        return Y_SIZE;
    }
    public hienTuong getHT(){
        return ht;
    }
    public void setColorCL(Color c){
        if (c != null) cl_cl = c;        
        else cl_cl = Color.white;
    }
    public void setColorKT(Color c){
        if (c != null) cl_kt = c;        
        else cl_kt = Color.white;
    }
    public void setLikeCL(){
        cl_kt = cl_cl;              
    }
    public void refresh(){
        cl.setBackground(cl_cl);
        kt.setBackground(cl_kt);
        repaint();
    }       
}
