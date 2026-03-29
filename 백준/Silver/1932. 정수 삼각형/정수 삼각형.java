//  정수 삼각형

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<List<Integer>> list = new ArrayList<>();

        for(int i = 1; i <= n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<Integer> l = new ArrayList<>();
            for(int j = 0; j < i; j++){
                l.add(Integer.parseInt(st.nextToken()));
            }
            list.add(l);
        }

        int[][] dp = new int[n][n];
        dp[0][0] = list.get(0).get(0);
        
        for (int i = 1; i < n; i++){

            for (int j = 0; j <= i; j++){
                if(j == 0){
                    // 오른쪽만
                    dp[i][j] = list.get(i).get(j) + dp[i-1][0];
                } else if (j == i){
                    // 왼쪽만
                    dp[i][j] = list.get(i).get(j) + dp[i-1][j-1];
                } else{
                    // 양쪽 다 해서 큰거
                    dp[i][j] = list.get(i).get(j) + Math.max(dp[i-1][j-1], dp[i-1][j]);
                }
            }
        }

        int answer = 0;

        for (int i = 0; i < n; i++){
            answer = Math.max(answer, dp[n-1][i]);
        }

        System.out.print(answer);


    }
}