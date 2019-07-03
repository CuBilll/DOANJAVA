/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knowledge;

import java.util.List;
import java.util.Vector;
import static knowledge.knowledge.prepare;

import org.jpl7.Query;
import org.jpl7.Term;
import math.*;


public class phanUng {
    public static String makeQuery(String X){
        String [] v = X.split("\\s");
        String res = "";
        for (String x : v){
          //  System.out.println("res: " + x);
            res += "\'" + x + "\',";
        }
        
        return res;
    }
    public static String clean(String X){
        return X.substring(1, X.length() - 1);
    }
    public static List <String> phanUng(String v){     
        System.out.println("v " + v);
        String in = makeQuery(v);
        String []inp = v.split("\\s");        
        List <String> input = convertArr(inp);
        
        Query q = new Query("pu(" + in + " X)");          
        if (!q.hasSolution()){
            int n = input.size();
            String X = "";
            for (int i=n - 1; i>=0; i--) X += inp[i] + " ";            
            in = makeQuery(X); 
            q = new Query("pu(" + in + " X)");    
        }
        
        if (!q.hasSolution()) return null;
        
        Term []arr = q.oneSolution().get("X").toTermArray();
        
        List <String> res = new Vector<String>();
        for (int i=0; i<arr.length; i++){
            String X = arr[i].toString();
            res.add(clean(X));
        }        
        return res;
    }       
    
    public static List <String> convertArr(String [] strs){
        List <String> res = new Vector<String>();
        for (String x : strs){
            res.add(x);                    
        }
        return res;
    }
    
    public static String makePlus(List <String> L){
        String res = "";
        int n = L.size();
        for (int i=0; i<n - 1; i++){
            res += L.get(i) + " + ";
        }
        res += L.get(n - 1);
        return res;
    }
    public static String showReaction(String X){        
        List <String> result = phanUng(X);
        if (result == null) return "Khong phan ung"; 
        String []v = X.split("\\s");
        List <String> con = convertArr(v);
        
        String res = makePlus(con);
        res += " = ";
        res += makePlus(result);        
        return res;
    }
}
