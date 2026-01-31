//  스택 수열

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int cnt = 0; // 최근에 push한 수
    static boolean isNo = false;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Deque<Integer> stack = new ArrayDeque<>();

        List<Character> list = new ArrayList<>();

        for (int i =0; i < n ; i++) {
            if(isNo) break;

            int target = Integer.parseInt(br.readLine());
            operation(target, stack, list);
        }

        StringBuilder sb = new StringBuilder();

        if (isNo) {
            System.out.print("NO");
        } else{
            for(char c : list) {
                sb.append(c).append('\n');
            }
        }

        System.out.println(sb);

    }

    public static void operation(int target, Deque<Integer> stack, List<Character> list){

//        if(stack.isEmpty()) return;

        // 1. cnt와 비교해서 값을 넣을지 결정
        while(cnt < target){
            cnt++; // 이게 완료되며 cnt == target인 상태도 들어감
            doPush(cnt, stack, list); // push하면 + 넣기
        }

        // 2. stack의 top에 있는 값이 target에 해당하는지 보고 늘어놓기
        if (stack.peek().equals(target)){
            doPop(stack, list);
        } else {
            // 3. cnt도 target보다 크거나 같아서 이미 들어간 상황이고, top에 해당 값이 없는 경우
            if (cnt >= target) isNo = true;
        }

    }

    public static void doPush(int v, Deque<Integer> stack, List<Character> list){
        stack.push(v);
        list.add('+'); // push하면 + 더하기
    }

    public static void doPop(Deque<Integer> stack, List<Character> list){
        stack.pop();
        list.add('-'); // pop하면 - 더하기
    }
}