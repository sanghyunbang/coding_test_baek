//  N과 M (1)

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static boolean[] visited;
    static int maxDepth;
    static int maxNum;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        maxNum = N;
        maxDepth = M;
        visited = new boolean[N+1];

        sb = new StringBuilder();
        backtracking(0);
    }

    public static void backtracking(int depth){

        if (depth == maxDepth) {
            System.out.println(sb);
            return;
        }

        for (int i = 1; i <= maxNum; i++){

            if(!visited[i]){

                // 1. 일단 방문 및 초기 sb 길이 구해놓기
                int len = sb.length();
                visited[i] = true;
                // 2. sb에 추가
                sb.append(i+" ");
                // 3. 다시 태우기
                backtracking(depth + 1);
                // 4. 위에서 빠져나왔으면 다시 visited 되돌리기
                visited[i] = false;
                // 5. sb 다시 이전 길이로
                sb.setLength(len);

            }
        }

    }
}