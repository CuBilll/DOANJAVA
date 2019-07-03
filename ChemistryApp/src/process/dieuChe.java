/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import knowledge.phanUng;
import math.edge;
import java.util.*;
import math.PwL;
import org.jpl7.Query;
import org.jpl7.Term;

public class dieuChe {
    public static boolean found;
    private List <String> chats;
    private List <String> kq;
    private edge[] trace;
    public dieuChe(List<String> X, List <String> Y){
        trace = new edge[1000];
        this.chats = PwL.copy(X);
        this.kq = PwL.copy(Y);
        found = false;
    }
    
    private boolean isGoal(List <String> v){
        for (String st : kq){
            if (!PwL.checkIn(st, v)){
                return false;
            }
        }
        return true;
    }        
    
    private String make(List <String> L1, List <String> L2){
        String res = "";
        for (int i=0; i<L1.size() - 1; i++) res += L1.get(i) + " + ";        
        res += L1.get(L1.size() - 1) + " = ";        
        for (int i=0; i<L2.size() - 1; i++) res += L2.get(i) + " + ";
        res += L2.get(L2.size() -  1);
        return res;
    }
    
    
    // trả về id rồi lấy id đó bỏ vào show path
    public int find(){
        List <String> s = PwL.copy(chats);
        
        List < List <String> > queue = new Vector<List<String>>();        
        queue.add(s);
        int L = 0, R = 0;        
        while (L <= R){     
            
            for (int k=L; k<=R; k++){
                List <String> u = queue.get(k);
                for (int i=0; i<u.size(); i++){
                    String c = u.get(i);
                    
                    // Phân hủy                                      
                    // Nếu đơn chất thì khỏi xét cái này. Mất thời gian.
                    
                    if (knowledge.knowledge.donChat(c) == null){                                            
                        System.out.println("2" + c);
                        List <String> pu = phanUng.phanUng(c);
                    
                        if (pu != null){
                            List <String> v = PwL.copy(pu);
                            for (String c2 : u) 
                                if (!PwL.checkIn(c2, v)) v.add(c2);
                            
                            if (PwL.checkIn(v, queue)){
                                continue;
                            }
                            
                            List <String> thamgia = new Vector<String>();
                            thamgia.add(c);
                            queue.add(v);                                                       
                            
                            trace[queue.size() - 1] = new edge(k, make(thamgia, pu));
                            
                            if (isGoal(v)){                  
                                found = true;
                                return queue.size() - 1;
                            }
                        }
                    }                    
                    // Kết hợp
                    for (int j=i+1; j<u.size(); j++){                        
                        List <String> pu2 = phanUng.phanUng(c + " " + u.get(j));                       
                        if (pu2 != null){
                            List <String> v = PwL.copy(pu2);
                            
                            for (String c2 : u) 
                                if (!PwL.checkIn(c2, v)) v.add(c2);
                            
                            if (PwL.checkIn(v, queue)){
                                continue;
                            }
                                                        
                            List <String> thamgia = new Vector<String>();
                            thamgia.add(c);
                            thamgia.add(u.get(j));
                            queue.add(v);
                           // System.out.println(v);
                            trace[queue.size() - 1] = new edge(k, make(thamgia, pu2));
                            
                            if (isGoal(v)){             
                                found = true;
                                return queue.size() - 1;                               
                            }
                            
                        }                        
                    } 
                }
            }
            
            L = R + 1;
            R = queue.size() - 1;              
            
            if (queue.size() > 1000) return queue.size() - 1;
            
        }
        return 0;
    }
    public List<String> showPath(int id){
        
        List <String> res = new Vector<String>();
        
        int k = id;
        
        while (k > 0){
            res.add(trace[k].getPt());
            k = trace[k].getId();            
        }
        
        int n = res.size();
        for (int i=0; i<n / 2; i++){
            String st = res.get(i);
            res.set(i, res.get(n - i - 1));
            res.set(n - i - 1, st);
        }            
        
        return res;
    }
}
