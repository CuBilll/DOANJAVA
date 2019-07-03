/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChemistryApp;



import Interface.*;

public class functions {
    public static boolean buttonClicked;
    public static chatBotInterface a;
    public static void func1(){
        a = new chatBotInterface();        
        a.show();
        
    }    
    public static void func2(){        
        while (a == null){
            System.out.println("Creating object a");
            
        }
        a.Bot();
    }    
}
