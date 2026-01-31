//  카드2

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Deque<Integer> deq = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            deq.offer(i);
        }

        while(deq.size() != 1) {
            // 1. 맨위 버리기
            deq.pop();
            if(deq.size() == 1) break;

            // 2. 앞 뒤 순서 바꾸기
            deq.offer(deq.pop());
        }

        System.out.print(deq.pop());

    }
}