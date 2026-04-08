//  부분합

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 숫자 -> 합은 long으로 받기
        int S = Integer.parseInt(st.nextToken()); // 부분합 제약

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;

        int l = 0;
        int r = 0;
        int sum = 0;

        while(true){
            if (sum >= S){
                // 합이 요건을 충족 하면 일단 업데이트 하고 꼬리 당기기
                min = Math.min(min, r - l);
                sum -= arr[l];
                l++;
            } else if (r == N){
                break;
            } else {
                sum += arr[r];
                r++;
            }
        }

        System.out.print(min == Integer.MAX_VALUE ? 0 : min);
    }
}