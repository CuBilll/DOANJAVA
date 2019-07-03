/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import knowledge.phanUng;
import java.util.List;
import java.util.Vector;
import static knowledge.knowledge.prepare;
import org.jpl7.Query;
import org.jpl7.Term;
import static knowledge.phanUng.makeQuery;


public class chuoiPhanUng {
    public static String makeQuery(String X){
        String [] v = X.split("\\s");
        String res = "";
        int n = v.length;
        for (int i=0; i<n - 1; i++){         
            res += "\'" + v[i] + "\',";
        }
        res += "\'" + v[n - 1] + "\'";
        return res;
    }    
    public static List<String> find(String X){               
        
        List <String> res = new Vector<String>();
              
        String chuoi = makeQuery(X);
        chuoi = "dieuche([" + chuoi + "], X)";
        System.out.println(chuoi);
        Query qr = new Query(chuoi);
        
        if (!qr.hasSolution()){
            System.out.println("Khong ton tai");
            return null;
        }
        else{
            Term []arr = qr.oneSolution().get("X").toTermArray();            
            for (int i=0; i<arr.length; i++){
                String x = phanUng.clean(arr[i].toString());
                res.add(x);
            }            
        }
        return res;
    }   
    public static List <String> showAll(String X){
        List <String> res = new Vector<String>();
        List <String> result = find(X);
        if (result == null) return null;
        
        String []v = X.split("\\s");
        
        canBang cb = new canBang();
        
        for (int i=0; i<result.size(); i++){         
            
            String resi = result.get(i);
            if (resi.equals("on")){                
                resi = "";
            }
            res.add(cb.canbang(phanUng.showReaction(v[i] + " " + resi)));
        }
        
        return res;
    }
}
