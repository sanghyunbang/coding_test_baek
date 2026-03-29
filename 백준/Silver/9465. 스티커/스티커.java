//  스티커

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++){
            int n = Integer.parseInt(br.readLine());

            int[][] arr = new int[2][n];
            for (int j = 0; j < 2; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int k = 0; k < n; k++){
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }


            int[][] dp = new int[3][n]; // 위, 아래, 안뽑음

            dp[0][0] = arr[0][0];
            dp[1][0] = arr[1][0];
            dp[2][0] = 0;

            for (int j = 1; j < n; j++){
                 dp[0][j] = arr[0][j] + Math.max(dp[1][j-1], dp[2][j-1]); // 현재 위를 뽑는 경우
                 dp[1][j] = arr[1][j] + Math.max(dp[0][j-1], dp[2][j-1]); // 현재 아래를 뽑는 경우
                 dp[2][j] = 0 + Math.max(dp[0][j-1], dp[1][j-1]); // 현재 안 뽑는 경우
            }

            int max = 0;
            for(int j = 0; j < 3; j++){
                max = Math.max(max, dp[j][n-1]);
            }

            sb.append(max).append("\n");
        }

        System.out.print(sb);

    }
}