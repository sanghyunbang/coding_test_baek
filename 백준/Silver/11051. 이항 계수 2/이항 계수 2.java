//  이항 계수 2

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N+1][N+1];
        int MOD = 10007;

        // 초기화
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }

        // i는 row, j는 col
        for(int i = 1; i <= N; i++) {

            for (int j = 1; j <= K; j++ ) {
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j]) % MOD;
            }
        }

        // 출력
        System.out.println(dp[N][K]);

    }
}