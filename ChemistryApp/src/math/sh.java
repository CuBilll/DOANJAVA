/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import java.util.List;
import java.util.Vector;


public class sh {
    public static int gcd(int a, int b){
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    public static int min(int a, int b){
        return a < b ? a : b;
    }
    public static int max(int a, int b){
        return a > b ? a : b;
    }            
    public static int max(int[] a){
        int res = a[0];
        for (int i=1; i<a.length; i++)
            res = max(res, a[i]);
        return res;
    }
    public static List<Integer> argMax(int[] a, int k){
        List <Integer> res = new Vector<Integer>();        
        int m = max(a);
        for (int i=0; i<a.length; i++){            
            if (m == a[i]){
                res.add(i);
                k--;       
                if (k == 0) break;
            }
        }
        return res;
    }
}
