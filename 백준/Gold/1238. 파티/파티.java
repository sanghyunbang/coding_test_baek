//  파티

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        // 연결 리스트 : 연결된 엣지 - distance가 int[]에 들어감
        // edge마다 연결된 edge들에 대해 해당 int[] 들어가있음
        List<List<int[]>> list = new ArrayList<>();

        // null 아니게 ArrayList 초기화 해놓기
        // M + 1 개까지
        for(int i = 0; i <= M; i++){
            list.add(new ArrayList<>());
        }

        // 본격적으로 list 값 채우기
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int[] info = new int[]{r,d};
            list.get(l).add(info);
        }

        int[] fromX = new int[N+1];
        Arrays.fill(fromX, Integer.MAX_VALUE);
        fromX[X] = 0;
        int[] initX = new int[]{X, 0};

        // [1] X -> 각 노드까지 최단 거리는 다익스트라

        // int[] 구성 : {노드 번호, 시작점으로 부터의 거리}
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));

        pq.offer(initX);

        while(!pq.isEmpty()){
            int[] current = pq.poll();
            int curNode = current[0];
            int curDist = current[1];

            // 쓰레기 값인 경우 넘기기
            if(fromX[curNode] < curDist) continue;

            for(int[] next : list.get(curNode)){

                int nextNode = next[0];
                int nextDist = next[1];

                if(fromX[curNode] + nextDist < fromX[nextNode]){
                    // 우선 fromX 업데이트
                    fromX[nextNode] = fromX[curNode] + nextDist;
                    // 해당 노드와 관련한 정보 pq에 넣기
                    pq.offer(new int[]{nextNode, fromX[nextNode]});
                }
            }
        }


        // [2] 각 노드에서 X까지 최단거리는..? -> 다익스트라 * (N-1) 번
        int[] toX = new int[N+1];
        Arrays.fill(toX, Integer.MAX_VALUE);
        toX[X] = 0;

        for(int i = 1; i <= N; i++){
            // X값은 할 필요 없음
            if(i == X) continue;

            int[] fromI = new int[N+1];
            Arrays.fill(fromI, Integer.MAX_VALUE);
            fromI[i] = 0;
            int[] initV = new int[]{i, 0};
            pq.offer(initV);

            while(!pq.isEmpty()){
                int[] current = pq.poll();
                int curNode = current[0];
                int curDist = current[1];

                // 쓰레기 값인 경우 넘기기
                if(fromI[curNode] < curDist) continue;

                for(int[] next : list.get(curNode)){

                    int nextNode = next[0];
                    int nextDist = next[1];

                    if(fromI[curNode] + nextDist < fromI[nextNode]){
                        // 우선 fromI 업데이트
                        fromI[nextNode] = fromI[curNode] + nextDist;
                        // 해당 노드와 관련한 정보 pq에 넣기
                        pq.offer(new int[]{nextNode, fromI[nextNode]});
                    }
                }
            }
            toX[i] = fromI[X];
        }

        // 최솟값 구하기
        int maxVal = 0;

        for (int i = 1; i <= N; i++){
            // X 자신 제외
            if(i == X) continue;

            int totalDist = fromX[i] + toX[i];
            maxVal = Math.max(maxVal, totalDist);
        }

        System.out.print(maxVal);
    }
}