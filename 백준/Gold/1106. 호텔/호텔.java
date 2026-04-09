//  호텔

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static int C;
    public static int N;
    public static int[][] arr;
    public static int minCost = Integer.MAX_VALUE;

    public static final int INF = 100_000_000;
    public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            C = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            int[] dp = new int[C + 100];
            Arrays.fill(dp, INF);
            dp[0] = 0;

            for (int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                int cost = Integer.parseInt(st.nextToken());
                int customer = Integer.parseInt(st.nextToken());

                // dp값 채워보기
                for (int j = customer; j < C+100; j++){
                    dp[j] = Math.min(dp[j], dp[j - customer] + cost);
                }
            }

            // C부터 C+100 다 뒤져서 MIN값 찾기
            int answer = INF;
            for(int i = C; i < C + 100; i++){
                answer = Math.min(answer, dp[i]);
            }

            System.out.println(answer);

    }

}