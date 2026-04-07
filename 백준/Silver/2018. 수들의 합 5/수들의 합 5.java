//  수들의 합 5

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 1. 투 포인트 과정 시작 (구간 합)
        int sum = 0;
        int l = 1;
        int r = 1;
        int cnt = 0;

        // 등호 여부 고려
        while(l <= r) {

            // 1. sum이 N보다 작으면 확장
            while (sum < N && r <= N) {
                sum += r;
                r++;
            }

            // 2. sum이 N과 같으면 카운트
            if(sum == N) cnt++;

            // 3. sum이 N보다 크거나 같으면 (다음 탐색을 위해) 왼쪽 축소
            sum -= l;
            l++;
        }

        System.out.print(cnt);



    }
}