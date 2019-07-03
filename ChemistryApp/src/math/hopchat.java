/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;


public class hopchat {
    private String cation;
    private String anion;
    public hopchat(){
        this.cation = null;
        this.anion = null;
    }
    public hopchat(String X, String Y){
        this.anion = Y;
        this.cation = X;
    }    
    public String getCation(){
        return this.cation;
    }
    public String getAnion(){
        return this.anion;
    }            
}
