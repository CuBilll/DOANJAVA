/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import java.util.HashMap;
import math.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javafx.util.Pair;
import knowledge.*;


public class canBang {
    public String huongdan = "";
    public canBang(){
        
    }
    private List<Integer> giaimatran(int [][]a, int n, int m){
        ///biến về ma trận tam giác trên
        int t = 0;
        while(t < n - 1){
            for(int j=0; j<m; j++){
                if(a[t][j] == 0){
                    int temp;
                    for(int k=t+1; k<n; k++){
                        if(a[k][j] != 0){
                            for(int z=0; z<m; z++){
                                temp = a[k][z];
                                a[k][z] = a[t][z];
                                a[t][z] = temp;
                            }
                            break;
                        }
                    }
                }
                int v = a[t][j];
                for(int k=t+1; k<n; k++){
                    if(a[k][j] != 0){
                        int u = a[k][j];
                        for(int z=j; z<m; z++)
                            a[k][z] = a[k][z]*v - a[t][z]*u;
                    }
                }
                t++;
                if(t >= n - 1)break;
                
            }
        }
        
        int []x = new int [100];
        
        ///tìm nghiệm
        x[m-1] = Math.abs(a[m-2][m-2]);
        //System.out.println(x[m-1]);
        for (int i=m-2; i>=0; i--){
            x[i] = 0;
            for (int j=0; j<m; j++)
                if (i != j)
                    x[i] = x[i] - a[i][j] * x[j];
            if(a[i][i]!=1 && i<n-1){
                for(int j=0; j<m; j++)
                    x[j] = x[j]*a[i][i];
            }
            x[i] = x[i] / a[i][i];
        }
        ///tối giản   
        int uc = x[0];
        for(int i=1; i<m; i++){            
            uc = sh.gcd(uc, x[i]);
        }
        for(int i=0; i<m; i++){
            x[i] /= uc;
        }
        List<Integer> e = new LinkedList();
        for(int i=0; i<m; i++)
            e.add(x[i]);
        return e;
    }
    private Pair<List, List> tachchat(String s){
        String []words = s.split("\\s");
        int k =0;
        for(String w:words){
            k++;
            if(w.equalsIgnoreCase("=")) break;
        }
        List<String> tt = new LinkedList<String>();
        List<String> sp = new LinkedList<String>();
        for(int i=0; i<words.length; i++){
            if(i < k - 1)
                if(!words[i].equalsIgnoreCase("+")){
                    tt.add(words[i]);
                }
            if(i >= k)
                if(!words[i].equalsIgnoreCase("+")){
                    sp.add(words[i]);
                }
        }
        Pair<List, List> e = new Pair<List,List>(tt,sp);
        return e;
    }
    
    private List <String> getSet(Map <String, Integer> st){
        List <String> res = new Vector<String>();        
        for (String s : st.keySet()){
            res.add(s);
        }
        return res;
    }
    private List <Integer> getValue(Map <String, Integer> st){
        List <Integer> res = new Vector<Integer>();
        for(Integer s: st.values()){
            res.add(s);
        }
        return res;
    }
    public String canbang(String s){
        int sobien, sochat = 0;
        huongdan +="\t" + s + "\nTa thấy: \n";
        List <String> TT1 = new LinkedList<String>();
        List <Integer> TT2 = new LinkedList<Integer>();
        List <String> SP1 = new LinkedList<String>();
        List <Integer> SP2 = new LinkedList<Integer>();
        List <String> VT = new LinkedList<String>(); 
        //tìm các chất tham gia, sản phẩm trong chuỗi
        List<String> tt = tachchat(s).getKey();
        List<String> sp = tachchat(s).getValue();
        ///Tách các chất tham gia, sản phẩm
        List <String> temp1;
        List <Integer> temp2;
        huongdan += "\n+ Bên các chất tham gia có: \n";
        for(int i=0; i<tt.size(); i++){
            temp1 = getSet(ptk.split(tt.get(i)));
            temp2 = getValue(ptk.split(tt.get(i)));
            for(int j=0; j<temp1.size(); j++){
                huongdan+="\t\t" + temp1.get(j) + " có số lượng là: " + temp2.get(j) + "\n";
                TT1.add(temp1.get(j));
                TT2.add(temp2.get(j));  
            }
        }
        huongdan += "\n+ Bên các chất sản phẩm có: \n";
        for(int i=0; i<sp.size(); i++){
            temp1 = getSet(ptk.split(sp.get(i)));
            temp2 = getValue(ptk.split(sp.get(i)));
            for(int j=0; j<temp1.size(); j++){
                huongdan+="\t" + temp1.get(j) + " có số lượng là: " + temp2.get(j) + "\n";
                SP1.add(temp1.get(j));
                SP2.add(temp2.get(j));
            }
        }
        huongdan += "\n+ Từ đó ta có hệ phương trình: \n";
        ///Thành lập ma trận
        sobien = tt.size() + sp.size();
        for(int i=0; i<TT1.size(); i++){
            VT.add(TT1.get(i));
            if(i>0){
                for(int j=i-1; j>=0; j--){
                    if(TT1.get(j).equals(TT1.get(i))){
                       VT.remove(VT.size()-1);
                        break;
                    }
                }
            }
        }   
        sochat = VT.size();
        String []hd = new String[sochat];
        int tq = 0;
        for(int i = 0; i< sochat; i++)
            hd[i] = "";
        String []cc = {"a", "b", "c", "d", "e","f","t","g"};
        int [][]matrix = new int [sochat][sobien];
        for(int i=0; i<tt.size(); i++){
            temp1 = getSet(ptk.split(tt.get(i)));
            temp2 = getValue(ptk.split(tt.get(i)));
            for(int e=0; e<temp1.size(); e++){
                for(int t=0; t<VT.size(); t++){
                    if(temp1.get(e).equals(VT.get(t))){
                        if(matrix[t][i] == 0){
                            hd[t] += temp2.get(e) + "" +cc[tq] + " + ";
                            matrix[t][i] = temp2.get(e);
                            break;
                        }
                    } 
                }
            }
            tq++;
        }
        for(int i=0; i<sp.size(); i++){
            temp1 = getSet(ptk.split(sp.get(i)));
            temp2 = getValue(ptk.split(sp.get(i)));
            for(int e=0; e<temp1.size(); e++){
                for(int t=0; t<VT.size(); t++){
                    if(temp1.get(e).equals(VT.get(t))){
                        if(matrix[t][i+tt.size()] == 0){
                            hd[t] += "(" + -temp2.get(e) + ")" +cc[tq];
                            matrix[t][i+tt.size()] = -temp2.get(e);
                            break;
                        }
                    } 
                }
            }
            tq++;
        }
        for(int i = 0; i<hd.length; i++)
            huongdan +="\t" + hd[i] + " = 0 \n";
        huongdan += "\n+ Giải hệ phương trình ta có nghiệm: \n";
        List<Integer> e = giaimatran(matrix,sochat,sobien);
        for(int i = 0; i < sobien; i++)
            huongdan +="\t" + cc[i] + " = " + e.get(i) + "\n";
        String []words = s.split("\\s");
        String str_ = printReaction(words, e);
         huongdan += "\nHệ số của phương trình: "+ str_;
        return str_;
    }
    private String printReaction(String[] result, List <Integer> heso){
        String s = "";
        int d = 0;
        for (int i=0; i<result.length; i++){
            if (result[i].equals("+") || result[i].equals("=")){
                s += result[i];
            }
            else{
                if (heso.get(d) > 1){
                    s += heso.get(d);                    
                }
                s += result[i];
                d += 1;
            }
            s += " ";
        }
        return s;
    }
    private Map<String, Integer> getOxh(String s){
        List<String> key = getSet(ptk.split(s));
        List<Integer> value = getValue(ptk.split(s));
        List<Integer> oxh = new Vector<Integer>();
        Map<String, Integer> result = new HashMap<String, Integer>();
        hopchat a = knowledge.getCA(s);
        int t = 0;
        int temp = 0;
        if(a == null){
            for(int i = 0; i< key.size(); i++){
                result.put(key.get(i), 0);
            }
        }
        else{
            for(int i = 0; i < key.size(); i++){
                if(a.getCation().equals("'" + key.get(i) + "'")){
                    temp += knowledge.getOxh(s)*value.get(i);
                }
                else if(key.get(i).equals("H")){
                    temp += value.get(i);
                }
                else if(key.get(i).equals("O")){
                    temp -= 2 * value.get(i);
                }
                else{
                    t = i;
                }
            }
            temp = - temp / value.get(t);
            for(int i =0; i < key.size(); i++){
                if(a.getCation().equals("'" + key.get(i) + "'")){
                    oxh.add(knowledge.getOxh(s));
                }
                else if(key.get(i).equals("H")){
                    oxh.add(1);
                }
                else if(key.get(i).equals("O")){
                    oxh.add(-2);
                }
                else{
                    oxh.add(temp);
                }
            }

            for(int i = 0; i< key.size(); i++){
                result.put(key.get(i), oxh.get(i));
            }
        }
        return result;
    }
    public String canBangElectron(String s){
        String kq = "";
        List<String> tt = tachchat(s).getKey();
        List<String> sp = tachchat(s).getValue();
       // System.out.println(tt + " " + sp);
        /*Lấy số oxh */
        List <String> TT1 = new LinkedList<String>();
        List <Integer> TT2 = new LinkedList<Integer>();
        List <Integer> TT3 = new LinkedList<Integer>();
        List <String> SP1 = new LinkedList<String>();
        List <Integer> SP2 = new LinkedList<Integer>();
        List <Integer> SP3 = new LinkedList<Integer>();
        List<String> temp1 = new LinkedList<String>();
        List<Integer> temp2 = new LinkedList<Integer>();
        List<Integer> temp3 = new LinkedList<Integer>();
        huongdan +="\t"+ s + "\nTa cần xác định số oxi hóa của các chất như sau: \n\t+ Bên các chất tham gia ta có:\n";
        for(int i=0; i<tt.size(); i++){
            temp1 = getSet(getOxh(tt.get(i)));
            temp2 = getValue(getOxh(tt.get(i)));
            temp3 = getValue(ptk.split(tt.get(i)));
            huongdan +="\nTrong chất: " + tt.get(i) + "số oxh của từng chất là: \n";
            for(int j=0; j<temp1.size(); j++){
                huongdan +="\t"+ temp1.get(j) + " có số oxh là: " + temp2.get(j) + "\n";
                TT1.add(temp1.get(j));
                TT2.add(temp2.get(j));
                TT3.add(temp3.get(j));
            }
        }
        huongdan +="\n+ Bên các chất sản phẩm ta có: \n";
        for(int i=0; i<sp.size(); i++){
            temp1 = getSet(getOxh(sp.get(i)));
            temp2 = getValue(getOxh(sp.get(i)));
            temp3 = getValue(ptk.split(sp.get(i)));
            huongdan +="\nTrong chất: " + sp.get(i) + "số oxh của từng chất là: \n";
            for(int j=0; j<temp1.size(); j++){
                huongdan +="\t"+ temp1.get(j) + " có số oxh là: " + temp2.get(j) + "\n";
                SP1.add(temp1.get(j));
                SP2.add(temp2.get(j));
                SP3.add(temp3.get(j));
            }
        }
        temp1.clear();
        temp2.clear();
        temp3.clear();
        /*Xác định quá trình khử - quá trình oxh*/
        huongdan += "\n+ Xác định quá trình khử và quá trình oxh: \n";
        for(int i = 0; i < TT1.size(); i++){
            for(int j = 0; j < SP1.size(); j++){
                if(TT1.get(i).equals(SP1.get(j)) && TT2.get(i) != SP2.get(j)){
                    temp1.add(TT1.get(i));
                    temp1.add(SP1.get(j));
                    temp2.add(TT2.get(i));
                    temp2.add(SP2.get(j));
                    temp3.add(TT3.get(i));
                    temp3.add(SP3.get(j));
                }
            }
        }
        if(temp1.size() == 0)
            return null;
        List<Integer> temp4 = new LinkedList<Integer>();
        for(int i = 0; i <temp3.size(); i++)
            temp4.add(temp3.get(i));
        int [] a = new int[temp1.size()/2];
        int k = 0;
        
        for(int i = 0; i < temp1.size(); i+=2){
            a[k] = temp2.get(i + 1) - temp2.get(i);
            if(temp2.get(i) < temp2.get(i + 1))
                   huongdan+="\tQuá trình oxh: " +temp3.get(i)+""+ temp1.get(i) + " -> " +temp3.get(i + 1)+""+ temp1.get(i + 1) + " + " + a[k]+"\n";
            else
                   huongdan+="\tQuá trình khử: " + temp3.get(i)+""+temp1.get(i) +" + " + -a[k] + " -> " + temp3.get(i + 1)+""+temp1.get(i + 1)+"\n";
            if(temp3.get(i) != temp3.get(i+1)){
                Integer t = temp3.get(i) * temp3.get(i + 1);
                temp3.set(i, t);
                temp3.set(i + 1, t);
                a[k] *= t;
            }
            k++;
        }
        huongdan+="\n+ Cân bằng hệ số các nguyên tố trong 2 quá trình oxh - khử:\n";
        k = 0;
        for(int i = 0; i < temp1.size(); i+=2){
            if(temp2.get(i) < temp2.get(i + 1))
                 huongdan+="\tQuá trình oxh: " +temp3.get(i)+""+ temp1.get(i) + " -> " +temp3.get(i + 1)+""+ temp1.get(i + 1) + " + " + a[k]+"\n";
            else
                 huongdan+="\tQuá trình khử: " + temp3.get(i)+""+temp1.get(i) +" + " + -a[k] + " -> " + temp3.get(i + 1)+""+temp1.get(i + 1)+"\n";
            k++;
        }
        k = 0;
        int d = 0, am = 0;
        
        for(int i = 0; i < temp1.size(); i+=2){
            if(a[k] > 0)    d+= a[k];
            else    am+= a[k];
            k++;
        }
        /*Tìm ước chung*/
        int uc = sh.gcd(d, -am);
        d /= uc;    am /= uc;
        System.out.println(d + " " + am + " " + uc);
        /*Nhân hệ số*/
        k = 0;
        huongdan+="\n+ Cân bằng electron và nhân hệ số sao cho: \n\t\t\tTổng e cho = Tổng e nhân: \n";
        for(int i = 0; i < temp1.size(); i+=2){
            if(a[k] > 0){
                temp3.set(i, temp3.get(i) * (-am));
                temp3.set(i + 1, temp3.get(i+ 1) * (-am));
                huongdan+="\tQuá trình oxh: " +temp3.get(i)+""+ temp1.get(i) + " -> " +temp3.get(i + 1)+""+ temp1.get(i + 1) + " + " + a[k] * (-am)+"\n";     
            }
            else{
                temp3.set(i, temp3.get(i) * d);
                temp3.set(i + 1, temp3.get(i+1) * d);
                huongdan+="\tQuá trình oxh: " +temp3.get(i)+""+ temp1.get(i) + " -> " +temp3.get(i + 1)+""+ temp1.get(i + 1) + " + " + a[k] * (d)+"\n";
            }
            k++;
        }
        for(int i = 0; i < temp3.size(); i++){
            System.out.println(temp3.get(i) + " " + temp1.get(i) + " " + temp4.get(i));
        }
        for(int i = 0; i < temp1.size(); i++)
             temp3.set(i, temp3.get(i) / temp4.get(i));
        for(int i = 0; i < temp3.size(); i++){
            System.out.println(temp3.get(i) + " " + temp1.get(i) + " " + temp4.get(i));
        }
        int cc = 0;
        for(int i = 1; i < temp1.size(); i++)
            if(!temp1.get(i).equals(temp1.get(i-1))){
                cc = 1;
                break;
            } 
        /*Đưa lên phương trình*/
        
        int sobien = tt.size() + sp.size();
        int []hso = new int[sobien];
        
        k = 0;
        for(int i = 0; i < tt.size(); i++){
            List<String> tl = getSet(getOxh(tt.get(i)));
            List<Integer> tm = getValue(getOxh(tt.get(i)));
            for(int j = 0; j < tl.size(); j++){
                for(int p = 0; p < temp1.size(); p++){
                    if(tl.get(j).equals(temp1.get(p)) && tm.get(j) == temp2.get(p))
                        hso[k] = temp3.get(p);
                }
            }
            k++;
        }

        if(cc == 1){
            for(int i = 0; i < sp.size(); i++){
                List<String> tl = getSet(getOxh(sp.get(i)));
                List<Integer> tm = getValue(getOxh(sp.get(i)));
                for(int j = 0; j < tl.size(); j++){
                    for(int p = 0; p < temp1.size(); p++){
                        if(tl.get(j).equals(temp1.get(p)) && tm.get(j) == temp2.get(p))
                            hso[k] = temp3.get(p);
                    }
                }
                k++;
            }
        }
        else{
            for(int i = 0; i < sp.size(); i++){
                List<String> tl = getSet(getOxh(sp.get(i)));
                List<Integer> tm = getValue(getOxh(sp.get(i)));
                for(int j = 0; j < tl.size(); j++){
                    for(int p = 0; p < temp1.size(); p++){
                        if(tl.get(j).equals(temp1.get(p)) && tm.get(j) == temp2.get(p))
                            hso[k] += temp3.get(p);
                    }
                }
                k++;
            }
        }
           
        k = sobien - sp.size();
        List <String> VT = new LinkedList<String>(); 
        for(int i=0; i<SP1.size(); i++){
            VT.add(SP1.get(i));
            if(i>0){
                for(int j=i-1; j>=0; j--){
                    if(SP1.get(j).equals(SP1.get(i))){
                       VT.remove(VT.size()-1);
                        break;
                    }
                }
            }
        }
        int []ks = new int[VT.size()];
        for(int i =0; i< sp.size(); i++){
             List<String> tl = getSet(getOxh(sp.get(i)));
             List<Integer> tm = getValue(ptk.split(sp.get(i)));
             for(int j = 0; j< tl.size(); j++){
                 for(int p =0; p<VT.size(); p++){
                     if(tl.get(j).equals(VT.get(p)))
                         ks[p] += tm.get(j) * hso[k];
                 }
             }
             k++;
        }
        for(int i = 0; i < VT.size(); i++)
            System.out.println(ks[i] + " " + VT.get(i));
        huongdan += "\n+ Cân bằng các nguyên tố không thay đổi eclectron như là: ";
        for(int i = 0; i < VT.size(); i++){
            int t = 0;
            for(int j = 0; j< temp1.size(); j+=2){
                if(temp1.get(j).equals(VT.get(i))) t = 1;
                if(t == 0){ 
                    huongdan += VT.get(i) + " ";
                    break;
                }
            }
        }
        if(VT.size() > 2){
        k = 0;
        for(int i =0; i < tt.size(); i++){
            List<String> tl = getSet(getOxh(tt.get(i)));
            List<Integer> tm = getValue(ptk.split(tt.get(i)));
            for(int j = 0; j< tl.size(); j++){
                 for(int p =0; p<VT.size(); p++){
                     if(tm.get(j)*hso[k] < ks[p] && tl.get(j).equals(VT.get(p)))
                         hso[k] = ks[p];
                 }
             }
             k++;
        }
        k = 0;
        int y = 0;
        for(int i=0; i<tt.size(); i++){
            List<Integer> tm = getValue(ptk.split(tt.get(i)));
            for(int j=0; j<tm.size(); j++){
                TT3.set(y, tm.get(j) *hso[k]);
                y++;
            }
            k++;
        }
        y = 0;
        for(int i=0; i<sp.size(); i++){
            List<Integer> tm = getValue(ptk.split(sp.get(i)));
            for(int j=0; j<tm.size(); j++){
                SP3.set(y, tm.get(j) * hso[k]);
                y++;
            }
            k++;
        }
        for(int i = 0; i < hso.length; i++){
            if(hso[i] == 0){
                if(i > sobien - sp.size()){
                    int ts = -(sobien - sp.size() - i);
                    List<String> t2 = getSet(getOxh(sp.get(ts)));
                    List<Integer> t3 = getValue(ptk.split(sp.get(ts)));
                    y = 0;
                    for(int j = 0; j < tt.size(); j++){
                        List<String> t4 = getSet(getOxh(tt.get(j)));
                        List<Integer> t5 = getValue(ptk.split(tt.get(j)));
                        for(int p = 0; p < t4.size(); p++){
                            if(t4.get(p).equals(t2.get(0)))
                                hso[i] +=  hso[y] * t5.get(p);
                        }
                        y++;
                    }
                    
                    for(int j = 0; j < sp.size(); j++){
                        if(sp.get(j).equals(sp.get(ts)))
                            break;
                        List<String> t4 = getSet(getOxh(sp.get(j)));
                        List<Integer> t5 = getValue(ptk.split(sp.get(j)));
                        for(int p = 0; p < t4.size(); p++){
                            if(t4.get(p).equals(t2.get(0)))
                                hso[i] -=  hso[y] * t5.get(p);
                        }
                        y++;
                    }
                    hso[i] /= t3.get(0);
                }
            }
        }
        }
        uc = hso[0];
        for(int i=1; i<hso.length; i++){            
            uc = sh.gcd(uc, hso[i]);
        }
        for(int i=0; i<hso.length; i++){
            hso[i] /= uc;
        }
        List<Integer> e = new LinkedList<Integer>();
        for(int i = 0; i < hso.length; i++)
            e.add(hso[i]);
        String []words = s.split("\\s");
        kq = printReaction(words, e);
        huongdan += "\nHệ số của phương trình: "+ kq;
        return kq;
    }
}
