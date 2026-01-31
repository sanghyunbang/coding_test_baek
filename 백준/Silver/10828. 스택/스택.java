//  스택

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String op = st.nextToken();
            int v= 0;
            if(op.equals("push")) {
                v = Integer.parseInt(st.nextToken());
            }
            operations(op, stack, v);
        }

    }

    public static void operations(String op, Deque<Integer> stack, int v) {
        if (op.equals("push")){
            stack.push(v);
        } else if (op.equals("pop")) {
            if(stack.isEmpty()) {
                System.out.println(-1);
            } else {
                System.out.println(stack.pop());
            }
        } else if (op.equals("size")){
            System.out.println(stack.size());
        } else if (op.equals("empty")) {
           if (stack.isEmpty()) {
            System.out.println(1);
           } else {
            System.out.println(0);
           }
        } else if (op.equals("top")) {
            if (stack.isEmpty()) {
                System.out.println(-1);
            } else {
                System.out.println(stack.peek());
            }
        }
    }
}