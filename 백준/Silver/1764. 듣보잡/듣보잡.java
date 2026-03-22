//  듣보잡

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> heard = new HashSet<>();

        for (int i = 0; i < N; i++){
            heard.add(br.readLine());
        }

        List<String> list = new ArrayList<>();

        for (int i = 0; i < M; i++){

            String s = br.readLine();
            if(heard.contains(s)){
                list.add(s);
            }
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();

        for (String s : list) {
            sb.append(s).append("\n");
        }

        System.out.println(list.size());
        System.out.println(sb);
    }
}