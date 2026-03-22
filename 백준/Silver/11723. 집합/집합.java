//  집합

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static Set<Integer> set = new HashSet<>();

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            String op = st.nextToken();

            if (op.equals("all") || op.equals("empty")){
                operation(op, 0);
            } else {
                int v = Integer.parseInt(st.nextToken());
                operation(op, v);
            }
        }

        System.out.print(sb);
    }

    public static void operation(String s, int v){

        if(s.equals("add")){
            set.add(v);
        } else if (s.equals("remove")){
            set.remove(v);
        } else if (s.equals("check")){
            if (set.contains(v)){
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        } else if (s.equals("toggle")){

            if (set.contains(v)){
                set.remove(v);
            } else {
                set.add(v);
            }
        } else if (s.equals("all")) {

            for (int i = 1; i <=20; i++) {
                set.add(i);
            }
        } else {
            set.clear();
        }
    }
}