//  N-Queen

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static int N;
    public static int[] board;
    public static int count = 0;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new int[N]; // 행 개수 만큼, 각 값은 해당 행에 몇 열에 위치한건지

        backtracking(0);
        System.out.print(count);
    }

    public static void backtracking(int row){

        if (row == N){
            count++;
            return;
        }

        // 주어진 행에서 모든 열 탐색
        for(int i = 0; i < N; i++){

            if(isPossible(row, i)){
                board[row] = i; // 해당 row에 col 추가, (temp 필요 없음 -> 이후에 해당 번째 값이 그냥 다시 덮어써지는 구조)
                backtracking(row + 1);
            }
        }
    }

    public static boolean isPossible(int row, int col){

        for (int i = 0; i < row; i++){
            // 1. row는 같을 수가 없음
            // 2. 이전의 값들과 col이 같은 경우가 하나라도 있는 경우
            if (board[i] == col){
                return false;
            }
            // 3. 이전의 값들과 대각선이 같은 경우
            if ( Math.abs(i-row) == Math.abs(board[i]-col)) {
                return false;
            }
        }
        return true;

    }
}