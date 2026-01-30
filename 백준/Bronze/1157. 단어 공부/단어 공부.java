//  단어 공부

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 대문자로 변환
        String st = br.readLine().toUpperCase();

        int n = st.length();

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i ++) {
            if( map.get(st.charAt(i)) == null) {
                map.put(st.charAt(i), 1);
            } else {
                map.put(st.charAt(i), map.get(st.charAt(i))+1);
            }
        }

        Deque<Character> maxStack = new ArrayDeque<>();

        for (char c = 'A'; c <= 'Z'; c++) {
            if(map.get(c) == null) {
                continue;
            } else {
                if(maxStack.isEmpty()){
                    maxStack.push(c);
                } else {
                    if (map.get(c) > map.get(maxStack.peek())){
                        maxStack.clear();
                        maxStack.push(c);
                    } else if (map.get(c).equals(map.get(maxStack.peek()))){
                        maxStack.push(c);
                    }
                }
            }

        }

        if(maxStack.size() >1) {
            System.out.print('?');
        } else {
            System.out.print(maxStack.peek());
        }

    }
}