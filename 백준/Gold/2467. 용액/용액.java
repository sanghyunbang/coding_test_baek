//  용액

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int[] mins = new int[2];

        int l = 0;
        int r = N-1;
        int sum = Integer.MAX_VALUE;

        while(l < r) {
            // sum 비교해서 업데이트
            if ( sum > Math.abs(arr[r] + arr[l])){
                sum = Math.abs(arr[r] + arr[l]);
                mins[0] = arr[l];
                mins[1] = arr[r];
            }

            if (arr[r] + arr[l] > 0){
                r--;
            } else if ( arr[r] + arr[l] == 0) {
                break;
            } else {
                l++;
            }
        }

        System.out.println(mins[0]+" "+mins[1]);
    }
}