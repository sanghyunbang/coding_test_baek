//  플로이드

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static final int INF = 10_000_001;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] arr = new int[m][3];

        int[][] dp = new int[n+1][n+1];

        // 초기 모두 무한 -> 자기자신은 0으로
        for (int i = 0; i <= n; i++){
            for (int j = 0; j <= n; j++){
                dp[i][j] = INF;
            }
        }
        for (int i = 0; i <= n; i++) {
            dp[i][i] = 0;
        }

        // 데이터 전처리 초기화
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        // arr 돌면서 dp 초기화
        for (int i = 0; i < m; i++){
            int l = arr[i][0];
            int r = arr[i][1];
            int cost = arr[i][2];

            dp[l][r] = Math.min(dp[l][r], cost);
        }

        // 삼중 for문 돌면서 플로드 워셜 구현

        for(int k = 1; k <= n; k++){
            for (int s = 1; s <= n; s++){
                for (int e = 1; e <= n; e++){
                    dp[s][e] = Math.min(dp[s][e], dp[s][k] + dp[k][e]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){

                if(dp[i][j] >= INF) {
                    dp[i][j] = 0;
                }

                sb.append(dp[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);






    }
}