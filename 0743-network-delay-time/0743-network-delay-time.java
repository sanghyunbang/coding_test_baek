class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        final int INF = 1 << 10;
        // 0-indexed 평탄 행렬: (n+1)² 대신 n² — 죽은 0행/0열 제거
        int[] g = new int[n * n];
        java.util.Arrays.fill(g, INF);
        for (int[] t : times) {
            int p = (t[0] - 1) * n + t[1] - 1;
            if (t[2] < g[p]) g[p] = t[2];
        }

        int src = k - 1;
        // 미확정 노드를 (id, 잠정거리) 병렬 배열로 압축 유지
        // → 전역 dist[] 자체가 사라지고, 거리 읽기/쓰기가 전부 순차 접근이 됨
        int[] rid = new int[n - 1];
        int[] rd  = new int[n - 1];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (i != src) { rid[cnt] = i; rd[cnt++] = INF; }
        }

        int u = src, du = 0;
        while (cnt > 0) {
            int base = u * n;
            int best = INF, bi = -1;
            // relax + argmin을 한 패스로 융합: 라운드당 cnt회 → 총 n(n-1)/2회
            for (int i = 0; i < cnt; i++) {
                int nd = du + g[base + rid[i]];   // 유일한 gather 접근
                int d = rd[i];
                if (nd < d) { rd[i] = nd; d = nd; }
                if (d < best) { best = d; bi = i; }
            }
            if (best >= INF) return -1;           // 남은 노드 도달 불가
            u = rid[bi];
            du = best;
            rid[bi] = rid[--cnt];                 // swap-remove, O(1)
            rd[bi]  = rd[cnt];
        }
        return du;   // 오름차순 확정 → 마지막 확정 거리가 곧 답 (n==1이면 0)
    }
}