//  피보나치 함수

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        int[][] dp = new int[41][2];

        // dp 초기화 : 0이면 0만 한개, 1이면 1만 한개
        dp[0][0] = 1; // f(0)
        dp[1][1] = 1; // f(1);

        List<Integer> list = new ArrayList<>();
        int max = 0;

        for (int i = 0; i < T; i++){
            int n = Integer.parseInt(br.readLine());
            list.add(n);
            max = Math.max(max, n);
        }

        for (int i = 2; i <= max; i++){
            dp[i][0] = dp[i-2][0] + dp[i-1][0];
            dp[i][1] = dp[i-2][1] + dp[i-1][1];
        }

        for (int i : list) {
            int zeros = dp[i][0];
            int ones = dp[i][1];
            sb.append(zeros).append(" ").append(ones).append("\n");
        }

        System.out.print(sb);


    }

}