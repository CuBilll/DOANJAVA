/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChemistryApp;


import ChemistryApp.*;

public class multiThread implements Runnable{
    private Thread t;
    private String threadName;
    private int id;
    public multiThread(String name, int id){
        threadName = name;
        this.id = id;
    }
    public void start(){
        if (t == null){
            t = new Thread(this, threadName);
            t.start();
        }                
    }   
    
    public void first(){
        functions.func1();
    }
    public void second(){
        functions.func2();
    }
    
    public void run(){
        if (id == 1){
            first();
        }           
        else{
            second();
        }
    }
}
