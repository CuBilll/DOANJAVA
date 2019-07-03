/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;


public class Tree {
    
    private Node root;
    private List<Node> all;        
    
    public Tree(){        
        root = null;
    }
    public void setRoot(List <String> L){
        root = new Node(L);        
    }
    public void addNode(Node X, String label, Node parent){        
        parent.addChild(label, X);
    }
    public Node getRoot(){
        return root;
    }   
    public void DFS(Node u){
        u.show();        
        for (Node child: u.getChilds().values()){
            if (child.getChats().size() == 1) continue;
            DFS(child);
        }
    }            
    public void DFS2(Node u){
        all.add(u);
        for (Node child: u.getChilds().values()){   
            if (child.getChats().size() == 1) continue;
            DFS2(child);
        }
    }
    public void travel(){
        DFS(root);
    }
    public List<Node> getTravel(){
        all = new Vector<Node>();
        DFS2(root);
        return all;
    }
    
}
