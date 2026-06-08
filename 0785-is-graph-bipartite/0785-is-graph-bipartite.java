import java.util.*;

class Solution {

    public int[] colors;
    public int n;
    public int[][] graph;

    public int[] parents;
    public Map<Integer, List<Integer>> splitedGraph = new HashMap<>();
    public Set<Integer> rootKeys;

    public boolean isBipartite(int[][] graph) {
        this.graph = graph;
        n = graph.length;
        colors = new int[n];

        getSplitedGraph();

        for (int root : rootKeys) {
            initWithBfs(root);
        }
        
        return check();
    }

    public void initWithBfs(int root) {
        Queue<Integer> que = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        // init
        que.offer(root);
        visited[root] = true;

        while(!que.isEmpty()){
            // current
            int curNode = que.poll();
            int versColor = getVersColor(curNode); // for this, previously color decided

            int[] adjs = graph[curNode];
            for (int adj : adjs) {

                if (visited[adj]) continue;

                visited[adj] = true; // update visit
                colors[adj] = versColor; // update color

                que.offer(adj);
            }
        }       
    }

    public boolean check() {

        for (int i = 0; i < n; i++){
            int[] adjs = graph[i];
            int myColor = colors[i];

            for (int adj : adjs) {
                if (colors[i] == colors[adj]) {
                    return false;
                }
            }
        }

        return true;
    }

    public void getSplitedGraph() {
        
        doUnionFind();

        for (int i = 0; i < n; i++){
            splitedGraph.computeIfAbsent(parents[i], k -> new ArrayList<>()).add(i);
        }

        rootKeys = splitedGraph.keySet();

    }

    public void doUnionFind() {
        
        parents = new int[n];
        
        for(int i = 0; i < n; i++){
            parents[i] = i;
        }

        for (int i = 0; i < n; i++){
            int[] adjs = graph[i];
            for(int adj : adjs) {
                union(i, adj);
            }
        }
    }

    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);

        if(rootX != rootY) {
            parents[y] = rootX; 
        }
    }

    public int find(int x){
        if (parents[x] == x) return x; // if root -> directly return
        return parents[x] = find(parents[x]);
    }

    public int getVersColor(int base){
        if (colors[base]==0){
            return 1;
        } else {
            return 0;
        }
    }
}