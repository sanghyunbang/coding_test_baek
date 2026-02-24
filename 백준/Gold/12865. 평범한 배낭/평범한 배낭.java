//  평범한 배낭

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[K+1];

        // dp[n] = dp[n]

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            //[0,0,0,0,0,0,0,0]->[0,0,0,0,0,0,13,13]
            //[0,0,0,0,8,8,13,13] (Math.max로)
            //[0,0,0,6,8,8,13,14]
            //[0,0,0,6,8,12,13,14]
            // dp[x]는 x 무게 한도 내에서 얻을 수 있는 최댓값
            for(int j = K; j >= W; j--){
                dp[j] = Math.max(dp[j], dp[j-W] + V);
            }
        }

        int max = 0;

        for(int i = 0; i < K+1; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);

    }
}