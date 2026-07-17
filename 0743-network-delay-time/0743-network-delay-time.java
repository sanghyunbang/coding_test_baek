class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        final int INF = 1 << 28;
        final int S = n + 1;

        // 1. 평탄 인접 행렬. 최대 10,201칸(40KB) — 밀집 그래프엔 이게 정답.
        int[] g = new int[S * S];
        java.util.Arrays.fill(g, INF);           // SIMD 인트린식으로 벡터화됨
        for (int[] t : times) {                  // 행 포인터 역참조 1회로 3개 필드 읽기
            int p = t[0] * S + t[1];
            if (t[2] < g[p]) g[p] = t[2];        // 중복 간선은 최솟값만
        }

        int[] dist = new int[S];
        java.util.Arrays.fill(dist, INF);
        dist[k] = 0;

        // 2. 미확정 노드 압축 리스트 (boolean visited[] 대신)
        int[] rest = new int[n];
        for (int i = 0; i < n; i++) rest[i] = i + 1;
        int cnt = n;

        int best = 0;
        while (cnt > 0) {
            // argmin: 확정될수록 리스트가 짧아짐 → 총 n²/2 = 5,000회
            best = INF;
            int bi = -1;
            for (int i = 0; i < cnt; i++) {
                int d = dist[rest[i]];
                if (d < best) { best = d; bi = i; }
            }
            if (best >= INF) return -1;          // 남은 노드 도달 불가 → 즉시 탈출

            int u = rest[bi];
            rest[bi] = rest[--cnt];              // swap-remove, O(1)

            // relax: g의 한 행을 연속 스캔 → JIT가 vpminsd로 벡터화 가능
            int base = u * S;
            for (int v = 1; v <= n; v++) {
                int nd = best + g[base + v];
                if (nd < dist[v]) dist[v] = nd;
            }
        }
        return best;   // 오름차순 확정 → 마지막 확정 거리가 곧 최댓값
    }
}