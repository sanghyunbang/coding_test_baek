import java.util.*;

class Solution {
    // 1 << 8 (256)은 여전히 너무 작을 수 있으므로 안전한 INF 값을 사용합니다.
    private static final int INF = 0x3f3f3f3f; 

    public int networkDelayTime(int[][] times, int n, int k) {
        // 객체 생성을 최소화하기 위해 배열로 간선(Edge)을 관리합니다. (Static Link List)
        int[] head = new int[n + 1];
        int[] next = new int[times.length + 1];
        int[] to = new int[times.length + 1];
        int[] weight = new int[times.length + 1];
        
        Arrays.fill(head, -1);
        
        // 간선 연결 정보를 배열에 기록 (ArrayList 대비 메모리/속도 극대화)
        for (int i = 0; i < times.length; i++) {
            int u = times[i][0];
            int v = times[i][1];
            int w = times[i][2];
            
            to[i] = v;
            weight[i] = w;
            next[i] = head[u];
            head[u] = i;
        }

        int[] dists = new int[n + 1];
        Arrays.fill(dists, INF);
        dists[k] = 0;

        // 큐에 들어있는지 여부를 체크하여 중복 삽입 방지
        boolean[] inQueue = new boolean[n + 1];
        
        // PriorityQueue 대신 ArrayDeque를 사용하여 정렬 비용 제거
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(k);
        inQueue[k] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            inQueue[cur] = false;

            // head 배열을 이용해 인접 노드 탐색
            for (int e = head[cur]; e != -1; e = next[e]) {
                int nextId = to[e];
                int nextDist = dists[cur] + weight[e];

                if (dists[nextId] > nextDist) {
                    dists[nextId] = nextDist;
                    if (!inQueue[nextId]) {
                        q.offer(nextId);
                        inQueue[nextId] = true;
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (dists[i] == INF) return -1;
            ans = Math.max(ans, dists[i]);
        }

        return ans;
    }
}