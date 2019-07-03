/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;



public class Node {
    private List <String> chats;
    private String edge;
    private Map<String, Node> childs;
    public Node(List <String> chat){
        chats = PwL.copy(chat);
        edge = null;
        childs = new HashMap<String, Node>();
    }
    public Node(String X){
        chats = Arrays.asList(X);
        edge = null;
        childs = new HashMap<String, Node>();
    }
    public void setEdge(String X){
        edge = X;
    }
    // thêm đứa con X vào trong key
    public void addChild(String key, Node X){        
        childs.put(key, X);
        return;                            
    }
    public List<String> getChats(){
        return chats;
    }
    public Map<String, Node> getChilds(){
        return childs;
    }    
    public String getEdge(){
        return edge;
    }
    public void show(){
        System.out.print(chats);
        System.out.println();
        System.out.println(edge);
        System.out.println("Childs:");
        for (String key : childs.keySet()){
            System.out.print(key + " : ");
            Node c = childs.get(key);
            for (String st : c.getChats()){
                System.out.print(st + " ");
            }
            System.out.println();
        }
    }
}
