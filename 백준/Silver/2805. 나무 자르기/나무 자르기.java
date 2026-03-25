//  나무 자르기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        // 가장 큰 값
        int right = 0;

        // arr 초기화
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, arr[i]);
        }

        // 이분 탐색
        long left = 0;
        long answer = -1;

        // 고민지점 등호 붙이는지 여부
        while(left <= right){
            // 현재 탐색 지점
            int mid = (int)((right + left) / 2);

            // 만약 요구사항에 미치지 못하면 cutSize 줄이기
            if (calculate(mid) < M) {
                right = mid -1;
            } else {
                // 나무가 충분해서 조건 만족할 때 업데이트
                answer = mid;
                left = mid + 1;
            }
        }

        System.out.print(answer);
    }

    public static long calculate(long cutSize){
        long sum = 0;

        for (int l : arr) {
            sum += Math.max(0, l-cutSize);
        }

        return sum;
    }
}