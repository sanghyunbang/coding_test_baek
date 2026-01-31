//  IOIOI

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        String ss = br.readLine();

        int answer = 0;
        int IOI = 0;

        int i = 0;

        while(i < M-2){

            // 1-1. IOI 모양 한번 성립하면
            if (ss.charAt(i) == 'I' && ss.charAt(i+1) == 'O' && ss.charAt(i+2) == 'I'){

                IOI++; // 2. IOI 한 번 나왔다고 추가
                i += 2; // 인덱스 +2증가

                if( IOI == N) { // 3. 추가 후에 N이랑 횟수 같으면 answer 부분 추가
                 answer++;
                 IOI--; // IOI 다시 초기화
                }
            } else {
                //1-2. IOI 모양 성립 안하면 다시 초기화
                IOI = 0;
                i++; // 안맞으면 +2가 아니라 +1 인덱스 증가
            }

        }

        System.out.println(answer);

    }
}