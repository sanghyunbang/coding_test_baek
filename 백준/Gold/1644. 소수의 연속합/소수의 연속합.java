//  소수의 연속합

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N == 1){
            System.out.print(0);
            return;
        }

        // list 채우기
        prime(N);

        // 투 포인트 시작
        int cnt = 0;
        int sum = 0;
        int r = 0;
        int l = 0;

        while(true){

            if (sum >= N){
                if (sum == N) cnt++;
                sum -= list.get(l++); // 값을 먼저 사용하고 나중 증가
            } else if (r == list.size()) {
                break;
            } else {
                sum += list.get(r++);
            }
        }

        System.out.print(cnt);
    }

    // n보다 작은 소수 배열 구하는 함수
    public static void prime(int n){
        boolean[] noPrime = new boolean[n+1];
        noPrime[0] = true;
        noPrime[1] = true;

        for(int i = 2; i * i <= n; i++) {
            for(int j = i * i; j <= n; j += i){
                noPrime[j] = true;
            }
        }

        for (int i = 0; i < noPrime.length; i++){
            if(!noPrime[i]){
                list.add(i);
            }
        }

    }
}