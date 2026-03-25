//  곱셈

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        long answer = recursive(A, B, C);
        System.out.print(answer);

    }

    public static long recursive(long v, long power, long div){

        // power가 1이 되면 그대로 v 출력
        if (power == 1) {
            return v % div;
        }

        // power가 1이 아닌 경우
        // 1. 해아하는 것 : 들어온 값 v를 div로 나눈 나머지
        long newV = v % div;
        
        // 주의 : recursive 분기 커지지 않게
        long temp = recursive(newV, power/2, div) % div;

        // 짝수인 경우
        if(power % 2 == 0) {
            return ( temp * temp) % div;
        } else {
        // 홀수인 경우
            // recursive값은 이미 (mod) 된 값
            // return (    (temp % div) * (temp % div)     * newV) % div;
            return ( (temp * temp) % div * newV) % div;
        }
    }
}