//  숨바꼭질 3

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static int MAX = 100_001;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // 해당 인덱스 거리까지 가는데 걸린 시간
        // 갈 수 있는 최대 거리는 100_000
        int[] times = new int[MAX];
        Arrays.fill(times, MAX);
        times[start] = 0;

        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerFirst(start);

        while(!dq.isEmpty()){

            int curDot = dq.poll();

            if (curDot == end){
                System.out.print(times[curDot]);
                return;
            }

            int e = 2 * curDot;
            if(e < MAX && times[e] > times[curDot]){
                times[e] = times[curDot];
                dq.offerFirst(e);
            }

            int e1 = curDot + 1;
            int e2 = curDot - 1;

            if ( e1 < MAX && times[e1] > times[curDot] + 1){
                times[e1] = times[curDot] + 1;
                dq.offerLast(e1);
            }

            if ( e2 >= 0 && times[e2] > times[curDot] + 1){
                times[e2] = times[curDot] + 1;
                dq.offerLast(e2);
            }
        }
    }
}