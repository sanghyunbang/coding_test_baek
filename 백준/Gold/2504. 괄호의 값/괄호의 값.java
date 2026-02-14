import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {

    // 아이디어
    // 1. mul이 얼마인지 : 외곽 괄호에 따라서
    // 2. base가 얼마인지 : 제일 안의 값
    // 3. 만약에 안의 괄호가 아니면 나눠서 다시 mul을 되돌리면 됨

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s = br.readLine();

    Deque<Character> st = new ArrayDeque<>();

    int total = 0;

    int mul = 1;
    int base = 0;

    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);

        if (c == '(') {
            st.push(c);
            mul *= 2;
        } else if (c == '[') {
            st.push(c);
            mul *= 3;
        } else if (c == ')') {
            if (st.isEmpty() || st.peek() != '(') {System.out.println(0); return;}

            st.pop();
            // 바로 닫힌 경우 -> total에 더하기
            if (i > 0 && s.charAt(i-1) == '(') {
                mul /= 2; // 한번 되돌리기
                base += 2 * mul; // 2곱하기 mul
            } else {
                // 닫히고 난뒤에 다시 닫힌 경우
                mul /= 2;
            }
        } else if (c == ']') {

            if (st.isEmpty() || st.peek() != '[') {System.out.println(0); return;}

            st.pop();
            // 바로 닫힌 경우 -> total에 더하기
            if (i > 0 && s.charAt(i-1) == '[') {
                mul /= 3; // 한번 되돌리기
                base += 3 * mul; // 2곱하기 mul
            } else {
                // 닫히고 난뒤에 다시 닫힌 경우
                mul /= 3;
            }
        }
    }
    
    if(!st.isEmpty()) {System.out.println(0); return;}

    System.out.println(base);

        
    }

}