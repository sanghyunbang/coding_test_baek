//  이중 우선순위 큐

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int k = Integer.parseInt(br.readLine()); // The number of operations.

            TreeMap<Integer,Integer> map = new TreeMap<>();

            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char op = st.nextToken().charAt(0);
                int n =  Integer.parseInt(st.nextToken());

                if(op == 'I'){
                    map.put(n, map.getOrDefault(n,0) + 1);
                } else if(op == 'D'){
                    if (map.isEmpty()){continue;}
                    if(n==1){ // delete max
                        int maxKey = map.lastKey();
                        int cnt = map.get(maxKey);

                        if(cnt == 1){
                            map.remove(maxKey);
                        } else {
                            map.put(maxKey, cnt - 1);
                        }

                    } else if(n==-1){
                        int minKey = map.firstKey();
                        int cnt = map.get(minKey);

                        if(cnt == 1){
                            map.remove(minKey);
                        } else {
                            map.put(minKey, cnt - 1);
                        }
                    }
                }
            }

            if (map.isEmpty()){
                sb.append("EMPTY\n");
            } else  {
                sb.append(map.lastKey()).append(' ').append(map.firstKey()).append('\n');
            }
        }
        System.out.println(sb);
    }
}