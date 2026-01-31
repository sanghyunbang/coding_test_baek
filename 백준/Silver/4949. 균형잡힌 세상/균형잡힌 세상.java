//  균형잡힌 세상

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;

        while(!(line = br.readLine()).equals(".")) {
            System.out.println(operation(line));
        }


    }

    public static String operation(String s) {
        char[] ch = s.toCharArray();

        Deque<Character> stack = new ArrayDeque<>();

        for (char c: ch) {
            if (c == '(' || c == '[') stack.push(c);
            if (c == ')'){
                if (stack.isEmpty() || stack.peek() == '[') return "no";
                stack.pop();
            }
            if (c == ']'){
                if (stack.isEmpty() || stack.peek() == '(') return "no";
                stack.pop();
            }
        }

        return stack.isEmpty() ? "yes" : "no";
    }
}