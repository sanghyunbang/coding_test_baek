//  N과 M (5)

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static StringBuilder sb = new StringBuilder();
    public static int[] arr;
    public static int maxDepth;
    public static int maxIdx;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = Integer.parseInt(st.nextToken());
        int chunks = Integer.parseInt(st.nextToken());
        maxIdx = num;
        maxDepth = chunks;

        st = new StringTokenizer(br.readLine());
        arr = new int[num+1];

        for(int i = 1; i <= num; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 1차로 정렬해두기
        Arrays.sort(arr);

        boolean[] visited = new boolean[num+1];
        backtracking(0, visited, new StringBuilder());

        System.out.print(sb);
    }

    public static void backtracking (int depth, boolean[] visited, StringBuilder s) {

        if (depth == maxDepth) {
            sb.append(s).append("\n");
            return;
        }

        // 1 부터 num까지 문어발식으로 죽죽 전략 선택
        for (int i = 1; i <= maxIdx; i++) {

            // 방문 한 경우는 그냥 넘어감
            if (visited[i]) continue;

            StringBuilder tempS = new StringBuilder(s);
            boolean[] tempV = new boolean[maxIdx + 1];
            for (int j = 0; j <= maxIdx; j++){
                tempV[j] = visited[j];
            }

            s.append(arr[i]).append(" ");
            visited[i] = true;
            backtracking(depth + 1, visited, s);

            s = tempS;
            visited = tempV;

        }
    }

}