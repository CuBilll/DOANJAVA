/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.Document;
import math.LCS;
import math.sh;
import process.*;

/**
 *
 * @author hanh
 */
public class ptkForm extends javax.swing.JFrame implements KeyListener {

    /**
     * Creates new form ptk
     */               
    
    private String _text;
    
    public ptkForm() {
        initComponents();
        
        //initSet();
        
        setTitle("Phân tử khối");
        
        initEvent();
        initImage(jLabel1,"images/binh.png");
        
        _text = "Enter";
        editor.setText(_text);
        
    }
    private void initImage(JLabel x, String path){
        ImageIcon ii = new ImageIcon(path);
        Image image = ii.getImage().getScaledInstance(x.getWidth(), x.getHeight(), Image.SCALE_SMOOTH);
        x.setIcon(new ImageIcon(image));
    }

    private void initEvent(){        
        editor.addKeyListener((KeyListener) this);        
    }
    
    
    
    public void keyTyped(KeyEvent arg0) {
        
    }
    public void keyPressed(KeyEvent arg0) {               
        
    }

    public void keyReleased(KeyEvent arg0) {
        int key = arg0.getKeyCode();
        System.out.println("Key Pressed " + key);                
        
        if (key == 8){
            return;
        }
        if(key == 10){
            java.awt.event.ActionEvent evt=null;
            jButton1ActionPerformed( evt);
         }
        if (key == 37) return;
        if (key == 16) return;
        
        String X = editor.getText();
                
        int id = X.length();                           
        
        if (X.length() == 0){
            return ;
        }        
                        
        String m = match(X);
        System.out.println("Match : " + m);
        if (m != null){                    
            editor.setText(m);                        
        }                       
                
        editor.setSelectionStart(id);                                      
    }

    private String match(String X){
        String res  = null;
        int m = 100;
        int d = X.length();
        for (String st : knowledge.knowledge.all){
            if (st.length() < d) continue;
            String tmp = st.substring(0, d);
            if (tmp.equals(X)){
                if (m > st.length()){
                    m = st.length();     
                    res = st;
                }
            }
        }
        return res;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        giaithich = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        editor = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Giải thích:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 100, 20));
        getContentPane().add(giaithich, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 460, 40));

        jButton1.setText("Tính");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 100, -1));

        editor.setBackground(new java.awt.Color(204, 255, 204));
        editor.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        editor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editorMouseClicked(evt);
            }
        });
        editor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editorActionPerformed(evt);
            }
        });
        getContentPane().add(editor, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 90, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 660, 50));

        jLabel1.setText("binh");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 310, 260));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editorActionPerformed
        
    }//GEN-LAST:event_editorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String X = editor.getText();
                
        String tmp = match(X);
        if (!X.equals(tmp)){                            
            Object[] options = {"Yes", "No"}; 
            int n = JOptionPane.showOptionDialog(this, "Công thức nhập sai. \nBấm Yes nếu vẫn muốn tính.", "Thông báo", JOptionPane.YES_NO_CANCEL_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (n == 0){
                giaithich.setText("M(" + X + ") = " + process.ptk.getDescription(X));
            }
            else{
                System.out.println();
                System.out.println("Khi NO " + n);
                giaithich.setText("Công thức " + X + " nhập sai.");
                return;
            }
        }
        else{
            giaithich.setText("M(" + X + ") = " + process.ptk.getDescription(X));
        }        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void editorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editorMouseClicked
        _text = "";
        editor.setText("");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ptkForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ptkForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ptkForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ptkForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    knowledge.knowledge.prepare();
                } catch (IOException ex) {
                    Logger.getLogger(ptkForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                new ptkForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField editor;
    private javax.swing.JLabel giaithich;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
