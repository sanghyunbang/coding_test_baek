//  좌표 압축

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> sorted = new TreeSet<>();
        for(int i = 0; i < N; i++){
            sorted.add(arr[i]);
        }

        // (값, 해당 값 순서)
        Map<Integer, Integer> map = new HashMap<>();

        int m = sorted.size();

        int id = 0;

        for(int v : sorted) {
            map.put(v, id);
            id++;
        }

        StringBuilder sb = new StringBuilder();

        for (int v : arr) {
            sb.append(map.get(v)).append(" ");
        }

        System.out.print(sb);

    }
}