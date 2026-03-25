//  이항 계수 3

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        long K = Long.parseLong(st.nextToken());
        long M = 1_000_000_007L;

        // 페르마 소정리 활용하기 : N! * (K! * (N-K)! ) ^ (M-2) (mod M)가 되는 셈

        // 1. N! (mod M) 구하기 : O(N) 만큼 그냥 돌려서 구하기

        long first = 1;

        for(int i = 1; i <= N; i++){
            first = (first * i) % M;
        }

        // 2. (K! * (N-K)! ) (mod M) 구하기 = [K!(mod M) * (N-K)!(mod M)] (mod M)

        // 2-1. K!(mod M)
        long kFact = 1;
        for (int i = 1; i <= K; i++){
            kFact = (kFact * i) % M;
        }

        // 2-2. (N-K)!(mod M)
        long nkFact = 1;
        for (int i = 1; i <= N-K; i++){
            nkFact = (nkFact * i) % M;
        }

        // 2-3. [K!(mod M) * (N-K)!(mod M)] (mod M)
        long second = ( (kFact % M) * (nkFact % M) ) % M;

        // 3. (K! * (N-K)!) ^ (M-2) (mod M) 구하기 = second ^ (M-2) (mod M) 구하기
        second = recursive(second, M-2, M);

        // 4. 합치기
        long answer = (  (first % M) * (second % M)  ) % M;
        System.out.print(answer);

    }

    public static long recursive(long val, long pow, long mod){

        if (pow == 1) {
            return val % mod;
        }

        long newV = val % mod;
        long temp = recursive(newV, pow/2, mod) % mod;

        // 짝수인 경우
        if (pow % 2==0) {
            return (temp * temp) % mod;
        } else {
        // 홀수인 경우 : pow 반씩 초갠거 하고, 거기다 다시 ( val % mod) 만큼 곱하고 (mod)
        return (   ( (temp * temp) % mod ) * newV   ) % mod;
        }

    }
}