//  LCS

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ff = br.readLine();
        String ss = br.readLine();

        int nn = ff.length();
        int mm = ss.length();

        int[][] dp = new int[nn+1][mm+1];

        for(int i = 1; i <= nn; i++){
            for (int j = 1; j <= mm; j++){
                if( ff.charAt(i-1) == ss.charAt(j-1) ){
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        System.out.println(dp[nn][mm]);

    }
}