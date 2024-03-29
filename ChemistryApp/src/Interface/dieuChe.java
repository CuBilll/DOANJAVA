/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import math.PwL;
import process.ptk;


public class dieuChe extends javax.swing.JFrame implements KeyListener{

    private Vector<JTextField> listOfC;
    private Vector<JTextField> listOfD;
    private final int dx = 25 * 3;
    private final int dy = 25;
    private final int space = 10;
    private int X_BOARD;
    private int Y_BOARD;
    private int X_INIT1;
    private int Y_INIT1;
    private int X_INIT2;
    private int Y_INIT2;
    private JTextField _textbox;
    
    public dieuChe() {
        initComponents();
        setTitle("Điều chế");
        initVars();
        initImage(jLabel3, "images/dieuche2.png");        
    }
    private void initImage(JLabel x, String path){
        ImageIcon ii = new ImageIcon(path);
        Image image = ii.getImage().getScaledInstance(x.getWidth(), x.getHeight(), Image.SCALE_SMOOTH);
        x.setIcon(new ImageIcon(image));
    }
    private void initVars(){
        X_BOARD = contain.getWidth();
        Y_BOARD = contain.getHeight();
        
        listOfC = new Vector<JTextField>();                
        listOfD = new Vector<JTextField>();
        
        X_INIT1 = (int)plus.getLocation().getX();
        Y_INIT1 = (int)plus.getLocation().getY();
        
        X_INIT2 = (int)plus.getLocation().getX();
        Y_INIT2 = (int)plus.getLocation().getY();
        
        _textbox = null;                    
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        ok2 = new javax.swing.JButton();
        contain1 = new javax.swing.JPanel();
        plus1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        output = new javax.swing.JTextArea();
        contain = new javax.swing.JPanel();
        plus = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 110));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        ok2.setText("Ok");
        ok2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ok2MouseClicked(evt);
            }
        });

        contain1.setBackground(new java.awt.Color(230, 255, 204));

        plus1.setText("+");
        plus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plus1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contain1Layout = new javax.swing.GroupLayout(contain1);
        contain1.setLayout(contain1Layout);
        contain1Layout.setHorizontalGroup(
            contain1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contain1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(plus1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(292, Short.MAX_VALUE))
        );
        contain1Layout.setVerticalGroup(
            contain1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contain1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(plus1)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        output.setEditable(false);
        output.setColumns(20);
        output.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        output.setRows(5);
        jScrollPane2.setViewportView(output);

        contain.setBackground(new java.awt.Color(255, 204, 204));

        plus.setText("+");
        plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout containLayout = new javax.swing.GroupLayout(contain);
        contain.setLayout(containLayout);
        containLayout.setHorizontalGroup(
            containLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(plus, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(294, Short.MAX_VALUE))
        );
        containLayout.setVerticalGroup(
            containLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(plus)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        jLabel1.setText("Chất điều chế:");

        jLabel2.setText("Nhập các chất ban đầu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(contain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(contain1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ok2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contain1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(ok2)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 810, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void plusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusActionPerformed
        int x_plus = (int)plus.getLocation().getX();
        int y_plus = (int)plus.getLocation().getY();

        JTextField tx = new JTextField();
        tx.setSize(dx, dy);
        tx.setLocation(x_plus, y_plus);
        
        // add key event
        tx.addKeyListener((KeyListener) this);
        addMouseListener(tx);
        addActionListener(tx);        
        //----
        
        contain.add(tx);
        tx.setVisible(true);

        listOfC.add(tx);

        if (x_plus + 2 * dx + space > X_BOARD ){
            plus.setLocation(X_INIT1, y_plus + dy);
        }
        else{
            plus.setLocation(x_plus + dx + space, y_plus);
        }
    }//GEN-LAST:event_plusActionPerformed
    private void addActionListener(JTextField tx){
        tx.addFocusListener(new FocusListener(){        
            @Override
            public void focusGained(FocusEvent e) {
                _textbox = tx;
            }

            @Override
            public void focusLost(FocusEvent e) {                
            }
        });
    }
    private void addMouseListener(JTextField tx){
        tx.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){                
                _textbox = tx;
                
            return ;
            }
        });
    }
    
    private void ok2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ok2MouseClicked
        
        // Nhập sai input
        
        List <String> list = new Vector<String>();
        for (JTextField textField : listOfC){
            if (textField.getText().length() > 0) 
                list.add(textField.getText());
        }
        // Chất sai.
        for (String st : list){
            String tmp = match(st);
            if (!st.equals(tmp)) {
                JOptionPane.showMessageDialog(
                        this, 
                        "Chương trình chưa hiểu chất " + st + " là chất gì.\nCó lẽ bạn nên check lại.", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
                output.setText("Lỗi đầu vào.");
                return;
            }
        }
                
        // Chất điều chế sai.                
        List <String> _list = new Vector<String>();
        for (JTextField textField : listOfD){
            if (textField.getText().length() > 0) 
                _list.add(textField.getText());
        }
        for (String st : _list){
            String tmp = match(st);
            if (!st.equals(tmp)) {
                JOptionPane.showMessageDialog(
                        this, 
                        "Chương trình chưa hiểu chất " + st + " là chất gì.\nCó lẽ bạn nên check lại.", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
                output.setText("Lỗi đầu vào.");
                return;
            }
        }                
        // Bảo toàn nguyên tố
        
        Vector <String> L1 = new Vector<String>();
        for (String st : list){
            for (String e : ptk.split(st).keySet()){
                if (!PwL.checkIn(e, L1)) L1.add(e);
            }
        }
        Vector <String> L2 = new Vector<String>();
        for (String st : _list){
            for (String e : ptk.split(st).keySet()){
                if (!PwL.checkIn(e, L2)) L2.add(e);
            }
        }
        
        for (String st : L2){
            if (!PwL.checkIn(st, L1)){
                JOptionPane.showMessageDialog(
                        this, 
                        "Nguyên tố " + st + " không có trong các nguyên tố ban đầu, \n" +                                
                        "nên không thể điều chế ra nguyên tố này được.\n" +
                        "\nCó lẽ bạn nên check lại.", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
                output.setText("Lỗi đầu vào.");
                return;
            }
        }
        // Nếu chất tham gia hoặc sản phẩm rỗng thì lỗi
        if (PwL.Zero(list) == list.size()){
            JOptionPane.showMessageDialog(
                        this,                         
                        "Danh sách chất ban đầu rỗng. Không thể thực hiện điều chế.", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
            output.setText("Lỗi đầu vào.");
                return;
        }
        if (PwL.Zero(_list) == _list.size()){
            JOptionPane.showMessageDialog(
                        this,                         
                        "Danh sách chất cần điều chế rỗng, không thực hiện được.", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
            output.setText("Lỗi đầu vào.");
                return;
        }
        // Nếu trạng thái ban đầu đã chứa. Thì khỏi làm.
        boolean ok = true;
        for (String st : _list){
            if (!PwL.checkIn(st, list)){
                ok = false;
                break;                
            }
        }
        if (ok){
            JOptionPane.showMessageDialog(
                        this,                         
                        "Chất cần điều chế đã có trong chất ban đầu.\n" +
                        "\nCó lẽ bạn nên check lại.", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
            
                return;
        }
        // Điều chế thôi.
        process.dieuChe dc = new process.dieuChe(list, _list);
        int id = dc.find();        
        
        if (!process.dieuChe.found){
            JOptionPane.showMessageDialog(
                        this,                                                 
                        "Việc điều chế các chất này nằm ngoài khả năng của chương trình.", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
            
            output.setText("Việc điều chế các chất này \nnằm ngoài khả năng của chương trình.");
            
            return;
        }
        
        process.canBang cb = new process.canBang();        
        String X = "";
        for (String pthh : dc.showPath(id)){
            String tmp = cb.canbang(pthh);
            X += tmp + "\n";
        }
        output.setText(X);
    }//GEN-LAST:event_ok2MouseClicked

    private void plus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plus1ActionPerformed
        int x_plus = (int)plus1.getLocation().getX();
        int y_plus = (int)plus1.getLocation().getY();

        JTextField tx = new JTextField();
        tx.setSize(dx, dy);
        tx.setLocation(x_plus, y_plus);
        
        // add key event
        tx.addKeyListener((KeyListener) this);
        addMouseListener(tx);
        addActionListener(tx);        
        //----
        
        contain1.add(tx);
        tx.setVisible(true);

        listOfD.add(tx);

        if (x_plus + 2 * dx + space > X_BOARD ){
            plus1.setLocation(X_INIT2, y_plus + dy);
        }
        else{
            plus1.setLocation(x_plus + dx + space, y_plus);
        }
    }//GEN-LAST:event_plus1ActionPerformed

    public void keyTyped(KeyEvent arg0) {
        
    }
    public void keyPressed(KeyEvent arg0) {               
        
    }

    public void keyReleased(KeyEvent arg0) {
        
        if (_textbox == null) return;
        
        int key = arg0.getKeyCode();
                     
        
        if (key == 8){
            return;
        }
        if (key == 37) return;
        if (key == 16) return;
        
        JTextField editor = _textbox;
        
        String X = editor.getText();
                
        int id = X.length();                           
        
        if (X.length() == 0){
            return ;
        }        
                        
        String m = match(X);
        
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
            java.util.logging.Logger.getLogger(dieuChe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dieuChe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dieuChe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dieuChe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dieuChe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contain;
    private javax.swing.JPanel contain1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton ok2;
    private javax.swing.JTextArea output;
    private javax.swing.JButton plus;
    private javax.swing.JButton plus1;
    // End of variables declaration//GEN-END:variables
}
