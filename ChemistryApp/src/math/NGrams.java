/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class NGrams {
    String word;
    public NGrams(String t){
        this.word = t;
    }
    public String subString(String[] a, int s, int e){
        String t = "";
        for(int i = s; i < e; i++)
            t += (i > s ? " ": "") + a[i];
        return t;
    }
    public List<String> ngrams(int n){
        List<String> listgrams = new ArrayList<String>();
        String []w = word.split(" ");
        for(int i = 0; i < w.length - n + 1; i++){
            listgrams.add(subString(w, i, i + n));
        }
        return listgrams;
    }
    public String answer(String []tl){
        String t = "";
        for(int i = 3; i >= 1; i--){
            List<String> wd = ngrams(i);
            for(int j = 0; j < wd.size(); j++){
                for(int k = 0; k < tl.length; k++){
                    if(wd.get(j).equals(tl[k])) t += tl[k] + " ";
                }
            }
        }
        try{
            t = t.substring(0, t.length() - 1);
        }catch(Exception e){};
        return t;
    }
   /* public static void main(String[] args){
        List<String> a = ngrams(3, "Trân là con chó điên, chó điên là trân");
        for(int i = 0; i< a.size(); i++)
            System.out.println(a.get(i));
    }*/
}
