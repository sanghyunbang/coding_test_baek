//  덱

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Deque<Integer> deq = new ArrayDeque<>();

        for (int i = 0 ; i < N ; i++){

            String cmd = br.readLine();
            if(cmd.substring(0,4).equals("push")){
                StringTokenizer st = new StringTokenizer(cmd);
                String s = st.nextToken();
                int v= Integer.parseInt(st.nextToken());
                operations(s, v, deq);
            } else {
                operations(cmd, 0, deq);
            }

        }
    }

    public static void operations(String s, int v, Deque<Integer> deq) {

        if (s.equals("push_front")) {
            deq.push(v);
        } else if(s.equals("push_back")){
            deq.offer(v);
        } else if(s.equals("pop_front")){
            if (deq.isEmpty()) {
                System.out.println(-1);
            } else {
                System.out.println(deq.pop());
            }

        } else if(s.equals("pop_back")){
            if (deq.isEmpty()) {
                System.out.println(-1);
            } else {
                System.out.println(deq.pollLast());
            }
        } else if(s.equals("size")){
            System.out.println(deq.size());
        } else if(s.equals("empty")){
            if(deq.isEmpty()){
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        } else if(s.equals("front")){
            if(deq.isEmpty()){
                System.out.println(-1);
            } else {
                System.out.println(deq.peek());
            }
        } else if(s.equals("back")){
            if(deq.isEmpty()){
                System.out.println(-1);
            } else {
                System.out.println(deq.peekLast());
            }
        }
    }
}