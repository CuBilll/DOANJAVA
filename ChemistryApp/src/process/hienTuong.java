/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import java.awt.Color;
import knowledge.phanUng;
import java.util.*;


public class hienTuong {
    private List <String> ds;
    public hienTuong(String X){
        this.ds = new Vector<String>();
        this.ds.add(X);
        this.ds.add("H2O");
    }            
    public hienTuong(){}
    private void push(List <String> R, String X){        
        if (!checkIn(R, X))
            R.add(X);       
    }
    private boolean checkIn(List <String> R, String X){
        for (String Y : R)
            if (X.equals(Y)) return true;
        return false;
    }
    
    /*private List <String> push(List <String> R, List <String> L){
        List <String> res = new Vector<String>();
        for (String X : R) res.add(X);
        if (L == null) return res;
        for (String X : L){
            if (!checkIn(res, X)) res.add(X);
        }
        return res;
    }
    private List <String> deleted(List <String> R, List <String> del){
        List <String> res = new Vector<String>();
        for (String X : R){
            if (checkIn(this.ds, X) || !checkIn(del, X)){
                res.add(X);
            }
        }
        return res;
    }
    public void add(String X){
        List <String> mtp = new Vector<String>();
        List <String> tmp = new Vector<String>();
        for (String Y : this.ds) tmp.add(Y);
        
        boolean ok = false;        
        for (String Y : tmp){
            List <String> pu = phanUng.phanUng(X + " " + Y);
            mtp = push(mtp, pu);
            if (pu != null){
                ok = true;                
                //System.out.println(pu);
            }
        }
        if (!ok){
            this.ds.add(X);
            return;
        }
                        
        tmp = push(tmp, mtp);
        //System.out.println(tmp);
        List <String> del = new Vector<String>();
        while (true){
            mtp.clear();
            del.clear();
            for (int i=0; i<tmp.size() - 1; i++){                
                String Y = tmp.get(i);
                for (int j=0; j<tmp.size(); j++){
                    String Z = tmp.get(j);
                    if (!Y.equals(Z)){
                        List <String> pu = phanUng.phanUng(Y + " " + Z);
                        mtp = push(mtp, pu);                                   
                        if (pu != null){                            
                            del.add(Z);
                            del.add(Y);
                            //System.out.println(Z + " + " + Y);
                        }
                    }                    
                }
            }            
            
            tmp = push(tmp, mtp);
            tmp = deleted(tmp, del);
                      
            if (mtp.size() == 0) break;                           
        }
        
        this.ds = push(this.ds, tmp);     
    }*/
    public void add(String X){
        boolean added = false;
        List <String> tmp = new Vector<String>();
        for (String Y : this.ds){
            List <String> result = phanUng.phanUng(X + " " + Y);
            if (result == null){                
                if (!added) push(tmp, X);
                push(tmp, Y);
            }
            else{
                added = true;
                for (String one : result) 
                    push(tmp, one);
            }
        }        
        this.ds = tmp;
    }
    public List <String> get(){
        return this.ds;
    }
    public List <Color> getColor(){
        List <Color> result = new Vector<Color>();
        List <String> tmp = knowledge.knowledge.getColor(get_cl());
        for (String st : tmp){
            Color c = knowledge.knowledge.colors.get(st);
            if (c != null) result.add(c);
        }
        return result;
    }
    public List <String> get_khi(){
        List <String> res = new Vector<String>();
        for (String st : this.ds){
            if (knowledge.knowledge.isKhi(st)) 
                res.add(st);            
        }
        return res;
    }
    public List <String> get_cl(){
        List <String> res = new Vector<String>();
        for (String st : this.ds)
            if (!knowledge.knowledge.kettua(st) && 
                !knowledge.knowledge.isKhi(st)) res.add(st);
        return res;
    }
    public List <String> get_kettua(){
        List <String> res = new Vector<String>();
        for (String st : this.ds)
            if (knowledge.knowledge.kettua(st)) res.add(st);
        return res;
    }
    public void del_khi(){
        List <String> res = new Vector<String>();
        for (String st : this.ds)
            if (!knowledge.knowledge.isKhi(st)) res.add(st);
        this.ds = res;
    }
    public void del_kettua(){
        List <String> res = new Vector<String>();
        for (String st : this.ds)
            if (!knowledge.knowledge.kettua(st)) res.add(st);
        this.ds = res;
    }
    public void showAll(){
        for (String X : this.ds) 
            System.out.println(X + " ");
        System.out.println();
    }
}
