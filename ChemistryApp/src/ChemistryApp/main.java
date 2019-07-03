
package ChemistryApp;

import knowledge.phanUng;
import Interface.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import math.LCS;
import process.dieuChe;

public class main {
    public static void main(String[] args) throws IOException{

        knowledge.knowledge.prepare();              

        mainInterface a = new mainInterface();
        a.show();                 

//        canBangF cb = new canBangF();
//        cb.show();
       
    }
}
