//  N과 M (2)

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static StringBuilder sb = new StringBuilder();
    public static int maxDepth;
    public static int maxNum;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        maxNum = n;
        maxDepth = m;

        backtracking(0, 0, new StringBuilder());
        System.out.print(sb);
    }

    public static void backtracking(int depth, int v, StringBuilder s){

        // 기저 조건
        if (depth == maxDepth){
            sb.append(s).append("\n");
            return;
        }

        for (int i = v+1; i <= maxNum; i++){

            StringBuilder temp = new StringBuilder(s);
            backtracking(depth + 1, i, s.append(i).append(" "));
            s = temp;

        }

    }

}