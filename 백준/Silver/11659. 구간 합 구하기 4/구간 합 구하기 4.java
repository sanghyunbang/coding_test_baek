//  구간 합 구하기 4

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 케이스 개수

        st = new StringTokenizer(br.readLine()); // N개의 수가 공란과 함께 String으로 들어감
        int[] arr = new int[N+1];
        int[] sumArr = new int[N+1];
        int sum = 0;
        for (int i = 1; i < N+1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            sumArr[i] = sum;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());


            int answer = sumArr[end] - sumArr[start-1];
            System.out.println(answer);

        }

    }
}