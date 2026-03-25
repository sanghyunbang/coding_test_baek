//  2×n 타일링

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int mod = 10007;
        int[] dp = new int[n+1];
        dp[0] = 1; dp[1] = 1;

        for (int i = 2; i <= n; i++){
            dp[i] = (dp[i-1]%mod + dp[i-2]%mod) % mod;
        }

        System.out.print(dp[n]);
    }

}