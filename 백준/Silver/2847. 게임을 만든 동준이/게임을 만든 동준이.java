//  게임을 만든 동준이

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int answer = 0;
        int max = Integer.MAX_VALUE;
        int n = Integer.parseInt(br.readLine());

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            stack.push(Integer.parseInt(br.readLine()));
        }

        // 뒤에서 부터 두개씩 해서 앞에 꺼보다 작게 만들기
        while(!stack.isEmpty()){
            int v = stack.pop();
            if (max > v) {
                max = v; // 맥스 값 아래 레벨로 바꾸기만.
            } else {
                int needGap = v - max + 1; // 큰 레벨 보다 1만큼 작게 만들기 위한 gap
                v -= needGap; // 해당 gap만큼 v를 줄이기
                answer += needGap; // -1 해준 횟수 추가
                max = v;

            }
        }

        System.out.println(answer);



    }
}