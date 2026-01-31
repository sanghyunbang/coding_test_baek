//  제로

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            int v = Integer.parseInt(br.readLine());

            if(v == 0) {
                stack.pop();
            } else {
                stack.push(v);
            }
        }

        int sum = 0;
        for(int i : stack) {
            sum += i;
        }

        System.out.print(sum);
    }
}