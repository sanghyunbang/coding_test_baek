//  최단경로

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(br.readLine());

        List<List<int[]>> list = new ArrayList<>();

        // 생성
        for (int i = 0; i <= V; i++){
            List<int[]> ele = new ArrayList<>();
            list.add(ele);
        }

        // 인접 리스트 초기화 하기
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // 서로 다른 두 정점 사이에 간선이 여러개면 그중 최소를 고르는 과정이 있어야 할진데
            // 여기선 일단 다 넣어 놓고 뒤에 min으로 처리
            list.get(l).add(new int[]{r ,w}); // list.get(l); 하면 List<int[]>고 여기에다 add하는 거
        }


        // 거리
        int[] dists = new int[V+1];

        // 1-1. 우선 시작지점은 0 나머진 나 inf
        for(int i = 1; i <= V; i++){
            if(i==K){
                dists[i] = 0;
                continue;
            }
            dists[i] = INF;
        }

        // dists에서 최소 값에 해당하는 에지 부터 시작
        // 배열[1] weight가 작은거 부터 먼저 보여주도록 (오름차순)
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        pq.add(new int[]{K, 0});

        while (!pq.isEmpty()){
            int[] curr = pq.poll();
            int node = curr[0];
            int weight = curr[1];

            // 이전꺼는 아닌지 확인해보기
            if (dists[node] < weight) continue;

            // list에서 node랑 연결된 것들 보면서 업데이트
            for(int[] ar : list.get(node)){
                int nextNode = ar[0];
                int nextWeight = ar[1];

                // nextNode의 dist와 현재 node를 타고 가는 거랑 비교
                if(dists[nextNode] > dists[node] + nextWeight) {
                    // nextNode로의 최단거리 업데이트
                    dists[nextNode] = dists[node] + nextWeight;

                    // 해당 next노드와 weight값 pq에 넣기
                    // (만약 더 작은 동일 두 노드 간 간선 있어도 알아서 업데이트)
                    pq.offer(new int[]{nextNode, dists[nextNode]});
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= V; i++){
            int val = dists[i];
            if (val >= INF){
                sb.append("INF");
            } else {
                sb.append(val);
            }
            sb.append("\n");
        }

        System.out.print(sb);

    }
}