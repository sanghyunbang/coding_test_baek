//  오큰수

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {

        // 입력값 받는 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // Stack형태의 자료구조 구현
        int[] answer = new int[N];
        Stack<Integer> stack = new Stack<>();

        stack.push(0); // 맨 처음은 넣어놓고 시작 (주의! 값이 아니라 인덱스 넣어놓기)

        for(int i = 1; i < N; i++){
            int check = arr[i];

            while(!stack.isEmpty() && check > arr[stack.peek()]){
                answer[stack.pop()] = check;
            }
            stack.push(i);
        }

        while(!stack.isEmpty()){
            answer[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i]);
            if (i != N - 1) sb.append(' ');
        }
        System.out.println(sb);


    }
}