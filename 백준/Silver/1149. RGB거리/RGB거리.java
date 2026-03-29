//  RGB거리

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] costs = new int[n][3];

        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            costs[i][0] = Integer.parseInt(st.nextToken());
            costs[i][1] = Integer.parseInt(st.nextToken());
            costs[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n][3];

        // 0번째 초기화
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];

        for(int t = 1; t < n; t++){
            dp[t][0] = costs[t][0] + Math.min(dp[t-1][1], dp[t-1][2]);
            dp[t][1] = costs[t][1] + Math.min(dp[t-1][2], dp[t-1][0]);
            dp[t][2] = costs[t][2] + Math.min(dp[t-1][0], dp[t-1][1]);
        }

        int answer = Integer.MAX_VALUE;

        for(int i = 0; i < 3; i++){
            answer = Math.min(answer, dp[n-1][i]);
        }

        System.out.print(answer);


    }

}