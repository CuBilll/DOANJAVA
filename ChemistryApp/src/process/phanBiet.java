/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import knowledge.*;
import java.util.*;
import math.*;
import org.jpl7.Query;
import org.jpl7.Term;


public class phanBiet {
        
    private boolean quyAllowed;      
    private Tree tree;
    public static Vector<String> hetcach;
    
    public static final List<String> templates = 
            Arrays.asList("AgNO3", "BaCl2", "Ba(OH)2", "HCl", "H2SO4", "Pb(NO3)2", "CaCl2", "Ca(OH)2");
    
    public phanBiet(){}
    public phanBiet(List<String> L, boolean quy){
        // set capacity of tree is 100
        tree = new Tree();
        tree.setRoot(L); 
        quyAllowed = quy;                
        // set quyAllowed        
        hetcach = new Vector<String>();
    }
    public void buildTree(){
        DFS(tree.getRoot());
    }
    public void travel(){
        tree.travel();
    }
    public void DFS(Node u){
        
        List <String> chats = u.getChats();
        
        // Nếu có 1 con thì có thể xem như đã phân biệt được.
        if (chats.size() == 1) return;
        
        //
        boolean found = false;
        // Có thể phân biệt màu nè
        List <String> mau = knowledge.getColor(chats);
        // Nếu biết hết màu
        if (PwL.available(mau)){                     
            Map<String, List<String>> ph = PwL.phanHoach(mau, chats);
            // Có nhiều hơn 1 màu 
            if (ph.keySet().size() > 1){
                // set edge with mau
                u.setEdge("mau");
                for (String key : ph.keySet()){
                    u.addChild(key, new Node(ph.get(key)));                    
                }
                found = true;
                // visit all childs                
                for (Node child : u.getChilds().values()){
                    DFS(child);
                }
            }
        }
        if (found) return;
        // Giờ chúng ta sẽ xem có phân biệt được bằng quỳ tím không nà.        
        if (quyAllowed){
            System.out.println(chats);    
            //System.out.println("vao quy");            
            List <String> mauQ = knowledge.getAllMauQ(chats);                  
            Map<String, List<String>> ph = PwL.phanHoach(mauQ, chats);                          
            // Có nhiều hơn 1 màu
            if (ph.keySet().size() > 1){
                for (String key : ph.keySet()){
                    u.addChild(key, new Node(ph.get(key))); 
                }
                found = true;
                // set edge with quy
                u.setEdge("quy");
                found = true;
                // visit all childs                
                for (Node child : u.getChilds().values()){
                    DFS(child);
                }
            }
        }
        if (found) return;
        // Chọn một chất trong template để phân biệt coi nào.
        // Chọn chất nào mà có thể phân hoạch ra nhiều nhóm nhất.
        Map <String, List<String>> choose = null;
        int Max = 0;
        String edge = "";
        for (String spt : templates){
            List <String> label = knowledge.productLabelList(chats, spt);            
            Map <String, List<String>> ph = PwL.phanHoach(label, chats);
            if (Max < ph.keySet().size()){
                Max = ph.keySet().size();
                choose = ph;
                edge = spt;
            }
        }
        if (Max < 2){            
            // Hết cách
            for (String st : chats) hetcach.add(st);
            return;
        }
        u.setEdge(edge);
        for (String key : choose.keySet()){
            u.addChild(key, new Node(choose.get(key))); 
        }
        found = true;
        for (Node child : u.getChilds().values()){
            DFS(child);
        }
    }      
    public Tree getTree(){
        return tree;
    }
}
