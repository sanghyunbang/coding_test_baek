//  로프

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(list);

        int answer = 0;

        int M = list.size();

        for(int i = 0; i < M; i++){
            answer = Math.max(answer, (M-i) * list.get(i));
        }

        System.out.println(answer);
    }
}