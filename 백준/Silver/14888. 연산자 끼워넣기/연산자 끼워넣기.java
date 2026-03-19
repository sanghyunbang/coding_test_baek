//  연산자 끼워넣기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static int plus;
    public static int minus;
    public static int mul;
    public static int div;

    static int maxDepth;

    static int[] arr;

    static int min;
    static int max;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 최대 깊이
        maxDepth = N-1;

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        plus = Integer.parseInt(st.nextToken());
        minus = Integer.parseInt(st.nextToken());
        mul = Integer.parseInt(st.nextToken());
        div = Integer.parseInt(st.nextToken());

        backtracking(0, arr[0]); // depth는 연산 개수 기준

        System.out.println(max);
        System.out.println(min);
    }

    public static void backtracking(int depth, int value){

        if (depth == maxDepth){
            // 종료조건
            min = Math.min(value, min);
            max = Math.max(value, max);
            return;
        }

        // i가 0,1,2,3이 각각 플, 마, 곱, 나
        for(int i = 0; i < 4; i++){

            if(i == 0) {
                // 플러스 예산 남았는지 부터 확인
                if(plus <= 0) continue;

                // 예산 줄이기
                plus--;

                // 해당 연산하기 : 현재 깊이 +1 값 arr[depth]
                int temp = value;
                value += arr[depth+1];

                // 재귀
                backtracking(depth + 1, value);

                // 연산 되돌리기
                value = temp;

                // 예산 되돌리기
                plus++;

            } else if (i == 1) {

                // 마이너스 예산 남았는지 부터 확인
                if(minus <= 0) continue;

                // 예산 줄이기
                minus--;

                // 해당 연산하기 : 현재 깊이 +1 값 arr[depth]
                int temp = value;
                value -= arr[depth+1];

                // 재귀
                backtracking(depth + 1, value);

                // 연산 되돌리기
                value = temp;

                // 예산 되돌리기
                minus++;

            } else if (i == 2) {

                // 플러스 예산 남았는지 부터 확인
                if(mul <= 0) continue;

                // 예산 줄이기
                mul--;

                // 해당 연산하기 : 현재 깊이 +1 값 arr[depth]
                int temp = value;
                value *= arr[depth+1];

                // 재귀
                backtracking(depth + 1, value);

                // 연산 되돌리기
                value = temp;

                // 예산 되돌리기
                mul++;

            } else {
                // 플러스 예산 남았는지 부터 확인
                if(div <= 0) continue;

                // 예산 줄이기
                div--;

                // 해당 연산하기 : 현재 깊이 +1 값 arr[depth]
                int temp = value;

                if (value < 0) {
                    value = -((-value) / arr[depth+1]);
                } else {
                    value /= arr[depth+1];
                }

                // 재귀
                backtracking(depth + 1, value);

                // 연산 되돌리기
                value = temp;

                // 예산 되돌리기
                div++;
            }
        }

    }
}