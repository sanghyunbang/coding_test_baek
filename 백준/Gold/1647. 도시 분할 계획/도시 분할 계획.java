//  도시 분할 계획

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static class Edge{
        int f, t, w;

        Edge(int f, int t, int w){
            this.f = f;
            this.t = t;
            this.w = w;
        }
    }

    public static int[] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        parent = new int[V+1];
        for(int i = 0; i <= V; i++){
            parent[i] = i;
        }

        List<Edge> list = new ArrayList<>();

        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            Edge edge = new Edge(f, t, w);
            list.add(edge);
        }

        Collections.sort(list, (s1, s2) -> Integer.compare(s1.w, s2.w));

        int edgeNum = 0;
        int costSum = 0;
        int maxCost = 0;
        for (Edge e : list){
            if (find(e.f) != find(e.t)){
                union(e.f, e.t);
                edgeNum++;
                costSum += e.w;
                maxCost = Math.max(maxCost, e.w);

                if (edgeNum == V - 1) break;
            }
        }

        // 제일 비싼 edgeCost 거르기
        System.out.print(costSum - maxCost);
    }

    public static int find(int x){
        if(parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    public static void union(int a, int b){
        int aa = find(a);
        int bb = find(b);

        if (aa != bb){
            parent[aa] = bb;
        }
    }
}