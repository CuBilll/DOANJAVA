/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;


public class LCS {      // Longest Common String
    String X, Y;
    int[][] f;
    int n, m;
    int lcs;
    public LCS(String X, String Y){        
        n = X.length();
        m = Y.length();
        f = new int[n + 1][m + 1];
        this.X = X.toUpperCase();
        this.Y = Y.toUpperCase();        
    }
    private void execute(){
        for (int i=0; i<=n; i++) f[i][0] = 0;
        for (int j=0; j<=m; j++) f[0][j] = 0;
        for (int i=1; i<=n; i++){
            for (int j=1; j<=m; j++){
                f[i][j] = sh.max(f[i - 1][j], f[i][j - 1]);
                if (X.charAt(i - 1) == Y.charAt(j - 1)){
                    f[i][j] = f[i - 1][j - 1] + 1;
                }                
            }
        }
        lcs = f[n][m];
    }
    public int lcs(){
        execute();
        return lcs;
    }
}
