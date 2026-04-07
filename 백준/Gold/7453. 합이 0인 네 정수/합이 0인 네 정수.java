//  합이 0인 네 정수

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] A = new int[n]; int[] B = new int[n];
        int[] C = new int[n]; int[] D = new int[n];

        // I/O
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        // 배열들 다 정렬
        Arrays.sort(A); Arrays.sort(B);
        Arrays.sort(C); Arrays.sort(D);

        // 1.AB 합과 CD 합 배열 만들기
        int[] AB = new int[n * n];
        int[] CD = new int[n * n];

        int idx = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                AB[idx] = A[i] + B[j];
                CD[idx] = C[i] + D[j];
                idx++;
            }
        }

        // 이분 탐색 시작
        Arrays.sort(AB);
        long cnt = 0;

        for(int i = 0; i < n*n; i++){
            int target = - CD[i];
            cnt += upperBound(AB, target) - lowerBound(AB, target);
        }

        System.out.println(cnt);

    }

    //  LowerBound : target 이상의 값이 처음으로 나오는 곳
    public static int lowerBound(int[] arr, int target){
        int l = 0;
        int r = arr.length; // lowerBound가 없으면 n까지 가도록
        while (l < r) {
            int mid = (l + r) / 2;

            if (arr[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    // UpperBound : target을 초과하는 값이 처음으로 나오는 곳
    public static int upperBound(int[] arr, int target){
        int l = 0;
        int r = arr.length;

        while (l < r) {
            int mid = (l + r) / 2;

            if (arr[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}