//  문자열 반복

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < T ; i++) {
            String l = br.readLine();
            StringTokenizer st = new StringTokenizer(l);

            int R = Integer.parseInt(st.nextToken());
            String ss = st.nextToken();
            int n = ss.length();

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                for (int k =0; k < R; k++) sb.append(ss.charAt(j));
            }
            System.out.println(sb);
        }

    }
}