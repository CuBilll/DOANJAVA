/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;
import java.util.List;
import java.util.Vector;
import org.jpl7.Query;
import org.jpl7.Term;
import java.util.Scanner;
import knowledge.*;

public class timChat {
    public static List<String> talk = new Vector<String>();
    public static List <String> Found(){
        List <String> dsc = new Vector<String>();
        talk.add("");
        Scanner x = new Scanner(System.in);
        String k = x.nextLine();
        
       
        List <String> temp = new Vector<String>();
        /// nếu có quì tím nhận dạng được axit, bazo, muối
        if(k.equals("yes")){
            talk.add("Ban thay quy tim mau gi ? \n 1: Mau do \n 2: Mau xanh \n 3: Khong mau");
            int a = x.nextInt();
            if(a== 1) temp = knowledge.getAllAxit(); 
            else if (a ==2) temp = knowledge.getAllBazo();
            else if (a==3){
                List<String> temp1 = knowledge.getAllHC();
                for(int i =0 ;i<temp1.size();i++){
                    if(knowledge.getCA(temp1.get(i)).getCation() != "H" && knowledge.getCA(temp1.get(i)).getCation() != "OH")
                        temp.add(temp1.get(i));
                }
            }
        }
        else {
            temp = knowledge.getAllHC();
        }
       // dsc = temp;
        for(int i = 0; i < temp.size(); i++)
            dsc.add(temp.get(i));
        //System.out.println(dsc);
        temp.clear();
        
        
        /* Danh sách thuốc thử */
        String []a = {"AgNO3","BaCl2","Ba(OH)2","HCl","H2SO4","Pb(NO3)2","CaCl2","Ca(OH)2"};
        List<String> u = new Vector<String>();
        for(String i : a){
            u.add(i);
        }
        ///Nhận dạng bằng cách chạy qua các chất trên
        for(int i =0; i< u.size(); i++){
            if(dsc.size() == 1 || dsc.size() == 0)
                return dsc;
            talk.add("Ban co "+ u.get(i) + "  khong?");
            x.nextLine();
            k = x.nextLine();
            if (k.equals("yes")){
                talk.add("Ban co the cho biet no co hien tuong gi khong? \n 1: kết tủa đồng thời có khí \n 2: khí \n 3: kết tủa \n 4: không có hiện tượng");
                int t = x.nextInt();
                if( t == 1){
                    /* Lấy các chất vừa có kết tủa vừa có khí*/
                    talk.add("Ban cho toi biet ket tua mau gi?");
                    x.nextLine();
                    String in = x.nextLine();
                    for(int j = 0; j < dsc.size(); j++){
                        String b = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKT(); 
                        String c = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKhi();
                        if(b != null && c != null) // có kết tủa và có khí
                            if(knowledge.color(b).equals(in)) // màu giống màu kết tủa
                                temp.add(dsc.get(j));
                    }
                }
                if(t == 2){
                    /*Lấy các chất có khí*/
                    for(int j = 0; j < dsc.size(); j++){
                        String b = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKT(); 
                        String c = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKhi();
                        if(b == null && c != null) // có khí
                            temp.add(dsc.get(j));
                    }
                }
                if(t == 3){
                    /*Lấy các chất có kết tủa*/
                    talk.add("Ban cho toi biet ket tua mau gi?");
                    x.nextLine();
                    String in = x.nextLine();
                    for(int j = 0; j < dsc.size(); j++){
                        String b = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKT(); 
                        String c = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKhi();
                        if(b != null && c == null) // có kết tủa
                           // System.out.println(knowledge.color(b) + " " + in);
                            if(knowledge.color(b).equals(in)){ // màu giống màu kết tủa
                                temp.add(dsc.get(j));
                              //  System.out.println("a");
                            }
                    }
                   // System.out.println(temp);
                }
                if(t == 4){
                    /*Lấy các chất không có hiện tượng*/
                    for(int j = 0; j < dsc.size(); j++){
                        String b = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKT(); 
                        String c = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKhi();
                        if(b == null && c == null)
                            temp.add(dsc.get(j));
                    }
                }
                dsc.clear();
                for(int g = 0; g < temp.size(); g++)
                    dsc.add(temp.get(g));
                temp.clear();
            }
        }
        return dsc;
    }
}
