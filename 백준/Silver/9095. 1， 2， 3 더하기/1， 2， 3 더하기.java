//  1, 2, 3 더하기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static int answer = 0;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            recursive(0,0,Integer.parseInt(br.readLine()));
            sb.append(answer).append("\n");
            answer = 0;
        }

        System.out.print(sb);
    }
    public static void recursive(int v, int sum, int goal){

        int newSum = v + sum;

        //종료 조건 1. goal에 해당하면 answer 업데이트
        if (newSum == goal){
            answer++;
            return;
        }

        // 종료 조건 2. 더 크면 그냥 빠져나오기
        if (newSum > goal) return;


        // 1, 2, 3 으로
        for (int i = 1; i <= 3; i++){
            recursive(i, newSum, goal);
        }
    }
}