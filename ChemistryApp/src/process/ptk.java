/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import knowledge.knowledge;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


public class ptk {
    
    public static String replace;
    
    public static boolean isNum(char c){
        return c >= '0' && c <= '9';
    }
    
    public static String next(String X, int index, int n){
        String res = X.substring(index, index + 1);
        if (index == n - 1) return res;        
        // Check for a number
        int i = index;
        char c = X.charAt(index);
        String hs = "";
        while (i < n){            
            c = X.charAt(i);
            if (isNum(c)){
                hs += c;
            }
            else {
                if (hs.length() > 0) return hs;
                else break;
            }                      
            i += 1;
        }
        if (hs.length() > 0) return hs;
        
        String res2 = X.substring(index, index + 2);
        if (knowledge.nguyenTo(res2) == -1.0) return res;          
        return res2;
    }
    
    public static Map<String, Integer> split(String X){
        
        replace = "";
        String old_nt = "(";
        
        List <String> L1 = new Vector<String>();
        int n = X.length();
        int i = 0;
        List <String> str_ = new Vector<String> ();
        List <Integer> cnt_ = new Vector<Integer> ();
        int sl = -1;
        int close = 0;
        System.out.println(X);
        while (i < n){            
            String nt = next(X, i, n);            
                        
            
            //System.out.println(nt);
            if (isNum(nt.charAt(0))){
                
                //--------------
                replace = replace + " x " + nt;
                //--------------
                
                int cnt = Integer.parseInt(nt);
                if (str_.get(sl).equals(")")){                    
                    int j = sl - 1;
                    int d = close;
                    while (j >= 0){
                        if (str_.get(j).equals("(")) d--;                        
                        if (d == 0) break;
                        int old = cnt_.get(j);
                        cnt_.set(j, old * cnt);
                        j -= 1;
                    }
                }
                else{                                        
                    
                    int old = cnt_.get(sl);
                    cnt_.set(sl, old * cnt);
                }
            }
            else{
                
                float m = knowledge.nguyenTo(nt);
                String T;
                int m1 = (int)m;
                if (m - m1 < 0.01){
                    T = Integer.toString(m1);
                }
                else{
                    T = Float.toString(m);
                }
                //--------------------                
                if (nt.equals("(") || nt.equals(")")){                                    
                    if (replace.length() > 0){
                        if (nt.equals("(")) replace += " + ";
                    }
                    replace += nt;
                }
                else{
                    if (!old_nt.equals("(")) 
                        replace += " + " + T;
                    else 
                        replace += T;
                }
                //--------------------
                
                sl ++;
                if (nt.equals(")")) close += 1;                                   
                str_.add(nt);
                cnt_.add(1);
            }
            i += nt.length();
            old_nt = nt;
        }
        
        Map <String, Integer> split = new HashMap <String, Integer> ();
        for (i=0; i<str_.size(); i++){
            String st = str_.get(i);
            if (!(st.equals(")") || st.equals("("))){
                if (!split.containsKey(st)){
                    split.put(st, cnt_.get(i));
                }
                else{
                    int cnt = split.get(st);
                    cnt += cnt_.get(i);
                    split.replace(st, cnt);
                }
            }
        }
//        System.out.println(str_);
//        System.out.println(cnt_);        
        return split;
    }
    
    public static String getDescription(String X){
        Map <String, Integer> M = split(X);
        float res = 0;
        for (String st : M.keySet()){            
            float m = knowledge.nguyenTo(st);            
            res += m * M.get(st);
        }
        return replace + " = " + res;
    }
    
    public static float getM(String X){
        Map <String, Integer> M = split(X);
        float res = 0;
        for (String st : M.keySet()){            
            float m = knowledge.nguyenTo(st);
            res += m * M.get(st);
        }
        return res;
    }
}
