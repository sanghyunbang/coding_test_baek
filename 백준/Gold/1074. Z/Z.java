//  Z

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static int answer = 0;
    public static int r;
    public static int c;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int size = (int) Math.pow(2, N);

        recur(0,0,size);
        System.out.print(answer);

    }

    // sr : start R , sc : start C, ps : present size
    public static void recur(int sr, int sc, int ps) {

        // z탐색 시작 사이즈 : 2
        if (ps == 1){
            // 해당 사이즈면 정답 인거
            return;
        }

        // next size
        int ns = ps / 2;
        int area = ns * ns;

        // 1사분면에 있는 경우
        if(r < sr + ns && c < sc + ns){
            recur(sr, sc, ns);
        } else if(r < sr + ns && c >= sc + ns) {
        // 2사분면에 있는 경우
            answer += area;
            recur(sr, sc + ns, ns);

        } else if(r >= sr + ns && c < sc + ns) {
        // 3사분면에 있는 경우
            answer += 2 * area;
            recur(sr + ns, sc, ns);
        } else {
        // 4사분면에 있는 경우
            answer += 3 * area;
            recur(sr + ns, sc + ns, ns);
        }
    }

}