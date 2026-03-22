//  수들의 합

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long S = Long.parseLong(br.readLine());

        long N = 0;
        long sum = 0;

        while(S >= sum){
            N++; // N을 한 칸 키운다
            sum += N;
        }

        System.out.print(N-1);

    }
}