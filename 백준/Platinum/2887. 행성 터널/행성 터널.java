//  행성 터널

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static class Edge{
        int f, t, w;
        Edge(int f, int t, int w) {
            this.f = f;
            this.t = t;
            this.w = w;
        }
    }

    public static class Node {
        int id, x, y, z;
        Node(int id, int x, int y, int z){
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static int[] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        for(int i = 0; i <=n; i++){
            parent[i] = i;
        }

        Node[] arr = new Node[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            Node node = new Node(i, x, y, z);
            arr[i] = node;
        }

        List<Edge> list = new ArrayList<>();

        Arrays.sort(arr, (s1, s2) -> Integer.compare(s1.x, s2.x));
        for (int i = 0; i < n-1; i++){
            Node n1 = arr[i];
            Node n2 = arr[i+1];
            Edge edge = new Edge(n1.id, n2.id, n2.x - n1.x);
            list.add(edge);
        }

        Arrays.sort(arr, (s1, s2) -> Integer.compare(s1.y, s2.y));
        for (int i = 0; i < n-1; i++){
            Node n1 = arr[i];
            Node n2 = arr[i+1];
            Edge edge = new Edge(n1.id, n2.id, n2.y - n1.y);
            list.add(edge);
        }

        Arrays.sort(arr, (s1, s2) -> Integer.compare(s1.z, s2.z));
        for (int i = 0; i < n-1; i++){
            Node n1 = arr[i];
            Node n2 = arr[i+1];
            Edge edge = new Edge(n1.id, n2.id, n2.z - n1.z);
            list.add(edge);
        }

        Collections.sort(list, (s1, s2) -> Integer.compare(s1.w, s2.w));

        long sum = 0;
        int cnt = 0;
        for(Edge ed : list){
            if (find(ed.f) != find(ed.t)){
                union(ed.f, ed.t);
                sum += ed.w;
                cnt++;

                if(cnt == n-1) break;
            }
        }

        System.out.print(sum);

    }

    public static int find(int a){
        if (parent[a] == a) return a;

        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b){
        int aa = find(a);
        int bb = find(b);

        if (aa != bb){
            parent[aa] = bb;
        }
    }
}