//  요세푸스 문제

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Deque<Integer> que = new ArrayDeque<>();

        for(int i = 1; i <= N; i++) {
            que.offer(i);
        }

        StringBuilder sb = new StringBuilder(2*N);
        sb.append("<");
        // 이제 K번 넘기기
        while (!que.isEmpty()){

            for (int i = 0; i < K-1; i++) {
                que.offer(que.pop());
            }
            int v = que.poll();
            sb.append(v);

            if(que.size() >= 1) {
                sb.append(", ");
            }
        }
        sb.append(">");

        System.out.print(sb);
    }
}