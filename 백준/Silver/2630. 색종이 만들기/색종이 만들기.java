//  색종이 만들기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static int white = 0;
    public static int blue = 0;

    public static int[][] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        // 초기화
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        recursive(N, 0, 0);

        sb.append(white).append("\n").append(blue);
        System.out.print(sb);

    }

    public static void recursive(int size, int r, int c){

        // 해당 사분면이 꽉 차있으면 넘기기
        if ( fullColor(size, r, c) ){
            return;
        }

        // fullColor 통과 못하면 쪼개기
        int half = size / 2;

        // 사분면 검사 하기
        recursive(half, r, c); // 1사분면
        recursive(half, r, c + half); // 2사분면
        recursive(half, r + half, c); // 3사분면
        recursive(half, r + half, c + half); // 4사분면

    }

    public static boolean fullColor(int size, int r, int c) {

        int wh = 0;
        for (int i = 0; i < size; i++){
            int row = r + i;

            for (int j = 0; j < size; j++) {
                int col = c + j;
                if(arr[row][col] == 0) wh++;
            }
        }

        if (wh == size * size) {
            white++;
            return true;
        } else if (wh == 0){
            blue++;
            return true;
        } else {
            return false;
        }
    }
}