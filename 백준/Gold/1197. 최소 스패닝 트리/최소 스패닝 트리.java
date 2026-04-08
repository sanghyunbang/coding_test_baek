//  최소 스패닝 트리

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static class Edge {
        int from, to, w;

        Edge(int from, int to, int w){
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }

    public static int[] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<Edge> list = new ArrayList<>();

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            Edge edge = new Edge(f, t, w);
            list.add(edge);
        }

        parent = new int[V+1];
        for(int i = 0; i <= V; i++){
            parent[i] = i;
        }

        // 1. 가중치 기준으로 에지리스트를 오름차순 정렬
        Collections.sort(list, (s1, s2) -> Integer.compare(s1.w, s2.w));

        // 2. 가중치가 낮은 에지부터 연결 시도하기
        // 에지를 연결했을 때 사이클이 생기는지 안 생기는지 보기
        int edgeNum = 0;
        int ans = 0;
        for(Edge edge : list){
            if (find(edge.from) != find(edge.to)){
                union(edge.from, edge.to);
                ans += edge.w;
                edgeNum++;

                if(edgeNum == V-1) break;
            }
        }

        System.out.print(ans);
    }

    // 루트 노드 찾기
    public static int find (int a){
        // 만약에 부모가 자기 자신이면 그대로 출력
        if(parent[a] == a) return a;// 2. 1에서 최종적으로 자기 자신인게 나오면 그 값
        return parent[a] = find(parent[a]); // 1. 부모의 부모의 부모의 부모로 계속 올라감
    }

    public static void union (int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            // 한쪽 부모를 다른쪽 부모로 합침
            parent[b] = a;
        }
    }
}