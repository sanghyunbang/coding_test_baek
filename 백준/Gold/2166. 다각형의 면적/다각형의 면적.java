//  다각형의 면적

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] x = new int[N];
        int[] y = new int[N];
        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        long first = 0;
        long second = 0;

        for (int i = 0; i < N; i++){
            // x1y2 + ... + xn y1
            int next = (i+1) % N;
            first += (long) x[i]*y[next];
            second += (long) y[i] * x[next];
        }

        double abs = Math.abs(first-second) / 2.0;
        System.out.printf("%.1f",abs);







    }
}