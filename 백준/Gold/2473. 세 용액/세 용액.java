//  세 용액

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        long[] arr = new long[n];

        for (int i = 0; i < n ; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        long diff = Long.MAX_VALUE;
        long[] answer = new long[3];

        for (int i = 0; i < n-2 ; i ++){

            int l = i + 1;
            int r = n - 1;

            // 투 포인트 시작하기
            while(l < r){
                long sum = arr[i] + arr[l] + arr[r];

                // diff값 비교해서 더 작으면 업데이트
                if(Math.abs(sum) < diff) {
                    diff = Math.abs(sum);
                    answer[0] = arr[i];
                    answer[1] = arr[l];
                    answer[2] = arr[r];
                }

                if (sum < 0) l++;
                else if (sum > 0) r--;
                else {
                    // sum == 0 인 경우 최적의 답이므로 바로 출력 후 종료
                    System.out.println(answer[0]+" "+answer[1]+" "+answer[2]+" ");
                    return;
                }
            }
        }
        System.out.println(answer[0]+" "+answer[1]+" "+answer[2]+" ");

    }


}