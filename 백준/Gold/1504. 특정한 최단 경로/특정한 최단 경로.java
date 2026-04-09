//  특정한 최단 경로

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static final int INF = 100_000_000;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 1. 인접 리스트 초기화 하기
        List<List<int[]>> list = new ArrayList<>();
        for (int i = 0; i <= V; i++){
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int id1 = l;
            int[] ar1 = new int[]{r,c};
            list.get(id1).add(ar1);

            int id2 = r;
            int[] ar2 = new int[]{l,c};
            list.get(id2).add(ar2);
        }

        // 1. 최단 거리 배열 3가지 케이스로 나누기 : 시작점 / v1 / v2
        List<int[]> dists = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            dists.add(new int[V+1]);
        }

        int[] starts = new int[3];
        starts[0] = 1;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 2; i++){
            starts[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 3; i++){
            int[] dist = dists.get(i);
            Arrays.fill(dist, INF);
            dist[starts[i]] = 0; // 초기 거리

            // pq 활용해서 dist 갱신
            PriorityQueue<int[]> pq = new PriorityQueue<>(
                                        (o1, o2) -> Integer.compare(o1[1], o2[1]));

            pq.offer(new int[]{starts[i], 0});

            while(!pq.isEmpty()){
                int[] cur = pq.poll();
                int curNode = cur[0];
                int curDist = cur[1];

                // pq에서 나온 거리가 dist값에 있는 것 보다 크면 old 버전이라 넘어감
                if(curDist > dist[curNode]) continue;

                for (int[] ele : list.get(curNode)){
                    int nextNode = ele[0];
                    int nextDist = ele[1];

                    if (nextDist + dist[curNode] < dist[nextNode]){
                        dist[nextNode] = nextDist + dist[curNode];
                        pq.offer(new int[]{nextNode, dist[nextNode]});
                    }
                }
            }

        }

        // 1 -> v1 -> v2 -> N
        int firstCase = dists.get(0)[starts[1]] + dists.get(1)[starts[2]] + dists.get(2)[V];
        // 1 -> v2 -> v1 -> N
        int secondCase = dists.get(0)[starts[2]] + dists.get(2)[starts[1]] + dists.get(1)[V];

        int answer = Math.min(firstCase, secondCase);

        System.out.println( ( answer < INF) ? answer : -1);

    }
}