//  별자리 만들기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static class Edge{
        int f, t;
        double w;
        Edge(int f,int t,double w){
            this.f = f;
            this.t = t;
            this.w = w;
        }
    }

    public static int[] parent;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        for(int i = 0; i <= n; i++){
            parent[i] = i;
        }

        double[] X = new double[n+1];
        double[] Y = new double[n+1];

        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            X[i] = Double.parseDouble(st.nextToken());
            Y[i] = Double.parseDouble(st.nextToken());
        }

        List<Edge> list = new ArrayList<>();

        for (int i = 0; i < n-1; i++){
            for (int j = i+1; j < n; j++){
                double dist = (X[i]-X[j]) * (X[i]-X[j]);
                dist += (Y[i]-Y[j]) * (Y[i]-Y[j]);
                dist = Math.sqrt(dist);

                Edge edge = new Edge(i, j, dist);
                list.add(edge);
            }
        }

        Collections.sort(list, (s1, s2) -> Double.compare(s1.w, s2.w));

        int cnt = 0;
        double sum = 0;
        for (Edge ed: list){
            if(find(ed.f) != find(ed.t)) {
                union(ed.f, ed.t);
                cnt++;
                sum += ed.w;

                if(cnt == n-1) break;
            }
        }

        System.out.printf("%.2f", sum);
    }

    public static int find(int a){
        if(parent[a] == a) return a;

        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b){
        int aa = find(a);
        int bb = find(b);

        if (aa != bb) {
            parent[aa] = bb;
        }
    }




}