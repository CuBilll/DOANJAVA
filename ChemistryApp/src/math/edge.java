/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;


public class edge {
    private int id;
    private String pthh;
    public edge(){}
    public edge(int k, String pt){
        this.id = k;
        this.pthh = pt;
    }
    public int getId(){
        return this.id;
    }
    public String getPt(){
        return this.pthh;
    }
}
