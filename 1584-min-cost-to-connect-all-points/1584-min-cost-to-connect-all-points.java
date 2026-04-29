import java.util.*;
class Solution {
    public int[] parents;
    public int minCostConnectPoints(int[][] points) {
        
        int n = points.length;
        // parents init 세팅
        parents = new int[n];
        for (int i = 0; i < n; i++){
            parents[i] = i;
        }

        // 인접 리스트 
        List<List<Integer>> list = new ArrayList<>();

        // 모든 두 점에 대해서 인접리스트 만들어서 넣기
        for (int i = 0; i < n; i++){
            for (int j = i; j < n; j++){
                List<Integer> ll = new ArrayList<>();
                ll.add(i); // from
                ll.add(j); // to
                ll.add(getDist(points[i], points[j])); // weight
                list.add(ll);
            }
        }

        // weight 기준으로 오름차순으로 정렬하기
        Collections.sort(list, (s1, s2) -> Integer.compare(s1.get(2), s2.get(2)));

        // 크루스칼 시작하기
        int nn = 0;
        int sum = 0;

        for (List<Integer> l : list){
            int u = l.get(0);
            int v = l.get(1);
            int w = l.get(2);

            if(find(u) == find(v)) continue;

            // 만약에 다른거면 sum 올리고 nn도 올리고 union
            sum += w;
            nn++;
            union(u, v);

            if (nn == n-1) break;
        }

        return sum;
    }

    public int find(int x){
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    public void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA != rootB){
            parents[rootA] = rootB;
        }
    }

    public int getDist(int[] a, int[] b) {
        int x = Math.abs(a[0] - b[0]);
        int y = Math.abs(a[1] - b[1]);

        return x + y;
    }
}