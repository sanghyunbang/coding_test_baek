//  괄호

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            Deque<Character> stack = new ArrayDeque<>();
            String s = br.readLine();
            System.out.println(operations(stack, s));
        }

        // 없는데 pop하거나, 끝나고 나서 남거나 -> no
    }

    public static String operations(Deque<Character> stack, String s) {

        char[] ch = s.toCharArray();
        for (char c: ch) {
            if (c == '('){
                stack.push('(');
            } else if(c == ')') {
                if (stack.isEmpty()) return "NO";
                stack.pop();
            }
        }

        return stack.isEmpty() ? "YES" : "NO";
    }
}