import java.util.*;

class Solution {

    private static final int INF = 1 << 8;
    public int networkDelayTime(int[][] times, int n, int k) {

        // 1. 인접 리스트 만들기
        List<List<int[]>> list = new ArrayList<>();
        for (int i = 0; i < n + 1; i++){
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < times.length; i++){
            int u = times[i][0];
            int v = times[i][1];
            int w = times[i][2];

            list.get(u).add(new int[]{v, w});
        }

        // 2. PQ 돌리기
        // dists[target] = target까지 누적 최단거리
        int[] dists = new int[n+1];
        Arrays.fill(dists,INF);
        dists[0] = 0; // 0은 없는 곳이라 0으로
        dists[k] = 0;

        // int[] 에는 [target, target까지 누적거리]
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (o1, o2) -> Integer.compare(o1[1], o2[1])
        );

        pq.offer(new int[]{k, 0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int curId = cur[0];
            int curDist = cur[1];

            // 이미 더 우선순위 높은 게 된거라 그대로 넘김
            if (dists[curId] < curDist) continue;

            for(int[] adj : list.get(curId)){
                int nextId = adj[0];
                int nextDist = adj[1] + curDist;

                if (nextDist >= dists[nextId]) continue;
                dists[nextId] = nextDist;
                pq.offer(new int[]{nextId, nextDist});
            }
        }

        int ans = 0;
        for (int v : dists) {
            ans = Math.max(v, ans);
        }

        return (ans == INF) ? -1 : ans;        

    }
}