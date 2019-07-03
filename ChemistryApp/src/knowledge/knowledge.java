
package knowledge;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javafx.util.Pair;
import static knowledge.phanUng.phanUng;
import math.*;
import org.jpl7.Term;
import org.jpl7.Query;
import process.ptk;


public class knowledge {
    
    public static List <String> allOfHC_tan;
    public static List <String> allOfHC_ktan;
    public static List <String> allOfAx;
    public static List <String> allOfBz;
    public static List <String> allOfMu;
    public static List <String> allOfDC;
    public static List <String> allOfOx;
    public static List <String> all;
    public static Map<String, Color> colors;    
    public static Map <String, Integer> oxh;       
    public static final String[] nt = {"H", "O", "Cl", "Br", "F", "I"};
    public static final int[]    it = {1,   -2,   -1,   -1,  -1,  -1};
    public static final String pathColor = "knowledge/colors.txt";
    public static final String pathHC_tan = "knowledge/hc_tan.txt";
    public static final String pathHC_ktan = "knowledge/hc_ktan.txt";
    public static final String pathAX = "knowledge/axit.txt";
    public static final String pathBZ = "knowledge/bazo.txt";
    public static final String pathMu = "knowledge/muoi.txt";       
    public static final String pathData = "knowledge/data.txt";
    public static final String pathDC = "knowledge/dc.txt";
    public static final String pathOx = "knowledge/oxit.txt";
    
    public static void prepare() throws IOException{                                        
        
        Query q1 = new Query("consult('knowledge/app.pl')");        
        q1.hasSolution();
        
        File f = new File(pathData);
        String path = f.getAbsolutePath();
        
        System.out.println(path);
        path = path.replace(path.charAt(2), '/');
        System.out.println(path);
        q1 = new Query("readKnowledge(\'" + path + "\').");
        
        q1.hasSolution();
        System.out.println();
        readColors();              
        
        allOfHC_tan = readFile(pathHC_tan);        
        allOfHC_ktan = readFile(pathHC_ktan);
        allOfAx = readFile(pathAX);
        allOfBz = readFile(pathBZ);
        allOfMu = readFile(pathMu);
        allOfDC = readFile(pathDC);
        allOfOx = readFile(pathOx);
        
        oxh = new HashMap<String, Integer>();
        for (int i=0; i<nt.length; i++){
            oxh.put(nt[i], it[i]);
        }
        
        all = PwL.copy(allOfHC_tan);
        for (String st : allOfDC) all.add(st);
        for (String st : allOfHC_ktan) all.add(st);
        for (String st : allOfOx) all.add(st);
                
    }
       
    
    public static float nguyenTo(String X){        
        Query q = new Query("nguyento('" + X + "\', X)");        
        if (!q.hasSolution()) return -1;
        Term a = q.oneSolution().get("X");
        return a.floatValue();
    }
    public static int getOxh(String T){        
        Query qr = new Query("name('" + T + "', X, Y)");
        String X = qr.oneSolution().get("X").toString();
        qr = new Query("cation(" + X + ", _ , X, Y)");
        X = qr.oneSolution().get("X").toString();
        int result = java.lang.Integer.parseInt(X);
        return result;
    }  
        
    public static Map<String, Integer> getAllOxh(String X){
        Map <String, Integer> res = new HashMap<String, Integer>();
        String dc = donChat(X);
        if (dc != null) {
            res.put(dc, 0);
            return res;
        }
        
        Map <String, Integer> sp = ptk.split(X);        
        
        String ca = phanUng.clean(getCA(X).getCation()); 
        int _cation = getOxh(X);
                
        int sum = sp.get(ca) * _cation;
        
        res.put(ca, _cation);
        
        String unique = "";
        
        for (String key : sp.keySet()){
            if (key.equals(ca)) continue;
            if (oxh.containsKey(key)){
                sum += oxh.get(key) * sp.get(key);
                res.put(key, oxh.get(key));
            }
            else{
                unique = key;
                res.put(key, 0);
            }
        }
                
        if(sum != 0){
            sum = (-sum) / sp.get(unique);
            res.replace(unique, sum);
        }
        return res;
    }
    
    public static Pair<List<String>, List<Integer>> getAllOxhList(String[] L){
        List <String> names = new Vector<String>();
        List <Integer> oxh = new Vector<Integer>();
        for (String st : L){
            Map <String, Integer> ox = knowledge.getAllOxh(st);
            for (String st2 : ox.keySet()){
                if (!PwL.checkIn(st2, names)){
                    names.add(st2);
                    oxh.add(ox.get(st2));
                }
                else{
                    boolean isAdd = true;
                    for (int i=0; i<names.size(); i++){
                        String tmp = names.get(i);
                        if (tmp.equals(st2)){
                            if (oxh.get(i) == ox.get(st2)){
                                isAdd = false;
                                break;
                            }
                        }
                        if (isAdd == false) break;
                    }
                    if (isAdd){
                        names.add(st2);
                        oxh.add(ox.get(st2));
                    }
                }
            }
        }
        return new Pair<List<String>, List<Integer>>(names, oxh);
    }
    
    public static Pair<String, String> chatChoNhan(List<String> name1, List<Integer> o1, 
                                 List<String> name2, List<Integer> o2){
        
        String cho = null;       
        for (int i=0; i<name1.size(); i++){
            String nm = name1.get(i);
            int ox = o1.get(i);
            for (int j=0; j<name2.size(); j++){
                String _nm = name2.get(j);
                int _ox = o2.get(j);
                if (nm.equals(_nm)){
                    if (_ox > ox){
                        cho = nm;
                    }
                }
            }
            if (cho != null) break;
        }
        
        String nhan = null;       
        for (int i=0; i<name1.size(); i++){
            String nm = name1.get(i);
            int ox = o1.get(i);
            for (int j=0; j<name2.size(); j++){
                String _nm = name2.get(j);
                int _ox = o2.get(j);
                if (nm.equals(_nm)){
                    if (_ox < ox){
                        nhan = nm;
                    }
                }
            }
            if (nhan != null) break;
        }
        return new Pair<String, String>(cho, nhan);
    }            
    
    public static String donChat(String X){
        String res = null;
        Query isDC = new Query("donchat(\'" + X + "\')");
        if (isDC.hasSolution()){
            Query name = new Query("name(\'" + X + "\', X, Y)");
            String st = name.oneSolution().get("X").toString();
            res = phanUng.clean(st);
        }
        return res;
    }
    
    public static boolean axit(String X){        
        Query qr = new Query("axit(" + X + ")");
        return qr.hasSolution();
    }
    public static boolean oxit(String X){
        Query qr = new Query("oxit('" + X + "')");
        return qr.hasSolution();
    }
    public static boolean bazo(String X){        
        Query qr = new Query("bazo(" + X + ")");
        return qr.hasSolution();
    }
    public static boolean muoi(String X){        
        Query qr = new Query("muoi(" + X + ")");
        return qr.hasSolution();
    }
    public static String mauQuy(String X){        
        if (axit(X)) return "red";
        if (bazo(X)) return "blue";
        return "violet";
    }
    public static List<String> getAllMauQ(List<String> L){
        List<String> result = new Vector<String>();
        for (String st : L){
            result.add(mauQuy("\'" + st + "\'"));
        }
        return result;
    }
    public static boolean kettua(String X){        
        Query qr = new Query("khongtan(\'" + X + "\', _)");
        return qr.hasSolution();             
    }
    
    public static String ketTua(List <String> v){
        
        if (v == null) return null;
        for (String st : v){                        
            if (kettua(st)) return st;
        }
        return null;
    }
    public static boolean isKhi(String X){        
        Query qr = new Query("khi(\'" + X + "\')");
        return qr.hasSolution();            
    }
    
    public static String khi(List <String> v){
        if (v == null) return null;
        for (String st : v){
            if (isKhi(st)) return st;
        }
        return null;
    }
    
    public static String color(String X){        
        Query qr = new Query("mau(\'" + X + "\', Y)");
        if (!qr.hasSolution()){
            return null;
        }
        return phanUng.clean(qr.oneSolution().get("Y").toString());
    }
    
    public static List <String> getColor(List <String> v){
        List <String> res = new Vector<String>();
        for (String st : v){
            res.add(color(st));
        }
        return res;
    }
    
    public static ktk getKetTua_Khi(String X){
        List <String> result = phanUng.phanUng(X);
        ktk res = new ktk(knowledge.ketTua(result), knowledge.khi(result));        
        return res;
    }
    
    public static hopchat getCA(String X){             
        Query qr = new Query("getCA(\'" + X + "\', C, A)");
        if (!qr.hasSolution()) return null;
        String A = qr.oneSolution().get("A").toString();
        String C = qr.oneSolution().get("C").toString();
        hopchat result = new hopchat(C, A);
        return result;
    }
    
    public static boolean isNumeric(String s) {  
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
    }  
    
    public static void readColors(){
        
        colors = new HashMap<String, Color>();
        
        BufferedReader br = null;
        FileReader fr = null;

        try {

                //br = new BufferedReader(new FileReader(FILENAME));
                fr = new FileReader(pathColor);
                br = new BufferedReader(fr);

                String sCurrentLine;

                while ((sCurrentLine = br.readLine()) != null) {
                    
                    String nextLine = br.readLine();                    
                    String[] v = nextLine.split("\\s");                    
                    int r = java.lang.Integer.parseInt(v[0]);
                    int g = java.lang.Integer.parseInt(v[1]);
                    int b = java.lang.Integer.parseInt(v[2]);
                    Color c = new Color(r, g, b);
                    colors.put(sCurrentLine, c);
                                       
                }

        } catch (IOException e) {

                e.printStackTrace();

        } finally {

                try {

                        if (br != null)
                                br.close();

                        if (fr != null)
                                fr.close();

                } catch (IOException ex) {

                        ex.printStackTrace();

                }

        }

    }
    
    
    public static Vector<String> readFile(String path){                
        
        Vector<String> result = new Vector<String>();
        
        BufferedReader br = null;
        FileReader fr = null;

        try {

                //br = new BufferedReader(new FileReader(FILENAME));
                fr = new FileReader(path);
                br = new BufferedReader(fr);                                   
                
                String sCurrentLine;

                while ((sCurrentLine = br.readLine()) != null) {
                    
                    result.add(sCurrentLine);
                                       
                }

        } catch (IOException e) {

                e.printStackTrace();

        } finally {

                try {

                        if (br != null)
                                br.close();

                        if (fr != null)
                                fr.close();

                } catch (IOException ex) {

                        ex.printStackTrace();

                }

        }
        
        return result;

    }
    public static List<String> getAllMuoi(){
        return allOfMu;
    }     
    public static List<String> getAllAxit(){
        return allOfAx;
    }            
    public static List<String> getAllBazo(){
        return allOfBz;
    }
    public static List <String> getAllHC_tan(){
        return allOfHC_tan;
    }
    public static List <String> getAllHC_ktan(){
        return allOfHC_ktan;        
    }
    public static List <String> getAllHC(){
        return all;
    }
    
    // Phản ứng sẽ có các loại sau:
    // + không phản ứng
    // + phản ứng tạo khí
    // + phản ứng tạo kết tủa màu x
    // + phản ứng tạo khí và kết tủa màu x
    // lưu theo kiểu: none, khi, kt_Yellow, kkt_Yellow
    // Đưa vào 1 phản ứng trả ra nhãn của nó
    public static String productLabelOne(String pu){
        List <String> result = phanUng.phanUng(pu);
        String label = "none";
        String k = khi(result);
        String kt = ketTua(result);
        boolean _khi = (k != null);
        boolean _kt = (kt != null);
        if (_khi && !_kt) label = "Khí";
        if (!_khi && _kt) {
            String cl = color(kt);
            label = "kết tủa màu " + cl;
        } 
        if (_khi && _kt){
            String cl = color(kt);
            label = "khí + kết tủa " + cl;
        }
        
        return label;
    }
    // X + tt có hiện tượng gì?
    public static List<String> productLabelList(List<String> L, String tt){
        List <String> result = new Vector<String> ();
        for (String X : L){
            String pu = X + " " + tt;
            result.add(productLabelOne(pu));
        }        
        return result;
    }            
        
    
} 
