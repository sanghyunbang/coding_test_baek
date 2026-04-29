class Solution {

    int[] parents;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // 단순 union find 문제 -> 같은 root 값을 가지는지만 확인하면 됨

        // 각각의 노드 별로 루트 값을 담을 칸
        parents = new int[n];

        for (int i = 0; i < n; i++){
            parents[i] = i; // 처음에 자기 자신을 부모로
        }

        for (int[] edge : edges){
            int f = edge[0];
            int t = edge[1];

            union(t, f);
        }

        if (find(source) == find(destination)){
            return true;
        } else {
            return false;
        }

    }

    public int find(int x){
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    public void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB){
            parents[rootB] = rootA;
        }
    }

}