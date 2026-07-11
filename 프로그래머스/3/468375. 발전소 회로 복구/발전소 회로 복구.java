import java.util.*;

class Solution {
    private static final int INF = 1 << 28;
    private int n,m;
    private String[] g;
    
    public int solution(int h, String[] grid, int[][] panels, int[][] seqs) {
        g = grid;
        n = grid.length;
        m = grid[0].length();
        int k = panels.length;
        
        // 1. 엘리베이터 위치 (모든 층의 구조가 같음)
        int er = 0, ec = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if(grid[i].charAt(j) == '@'){
                    er = i;
                    ec = j;
                }
            }
        }
        
        // 2. 패널 좌표 파싱하기 (행/열만 0-index로)
        int[] pf = new int[k]; int[] pr = new int[k]; int[] pc = new int[k];
        for (int i = 0; i < k; i++){
            pf[i] = panels[i][0];
            pr[i] = panels[i][1] - 1;
            pc[i] = panels[i][2] - 1;
        }
        
        // 3. BFS X (k + 1): 각 패널 위치 + 엘리베이터에서 전체 칸 거리
        int[][][] from = new int[k+1][][];
        for (int i = 0; i < k; i++){
            from[i] = bfs(pr[i], pc[i]);
        }
        from[k] = bfs(er, ec); // 이건 엘리베이터에 각 패널로
        
        // 4. 패널 간 거리 행렬
        // dist도 0-index
        int[][] dist = new int[k][k];
        for (int i = 0; i < k; i++){
            for (int j = 0; j < k; j++){
                if (pf[i]==pf[j]) {
                    dist[i][j] = from[i][pr[j]][pc[j]];
                } else {
                    int a = from[i][er][ec];
                    int b = from[k][pr[j]][pc[j]];
                    dist[i][j] = a + Math.abs(pf[i] - pf[j]) + b; // 층 이동 포함
                }
            }
        }
        
        // 5. 선행 조건 비트마스크 : pre[j] = j 이전에 커져 있어야 할 집합
        int[] pre = new int[k];
        for (int[] s : seqs){
            pre[s[1] - 1] = pre[s[1] - 1] | (1 << (s[0] - 1)); // 기존 pre[index] 에 (s[0]-1) 번째 추가로 켜는 과정
        }
        
        // 6. 비트마스크 DP
        int full = 1 << k; 
        // dp[현재까지 켜진 패널][마지막에 켜진 패널] : [][]상태에서 최소거리
        int[][] dp = new int[full][k];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        
        // 6-1(시작) : 1번 패널(인덱스 0)의 '위치'에서 출발 - 자동 활성화 아님
        // 선행 패널이 없는 경우에 (시작 위치 : 0) 에서 j까지는 dist[0][j]랑 같음
        for (int j = 0; j < k; j++){
            if (pre[j] == 0 && dist[0][j] < INF){
                dp[1 << j][j] = dist[0][j];
            }
        }
        
        for (int mask = 1; mask < full; mask++){
            for (int i = 0; i < k; i++){
                if ((mask & (1 << i)) == 0) continue; // i가 mask에 있어야
                int cur = dp[mask][i];
                if (cur >= INF) continue; // 지금으론 도달 불가능 상태 -> 스킵
                for (int j = 0; j < k; j++){
                    if ((mask & (1 << j)) != 0) continue; // 이미 활성화
                    if ((pre[j] & mask) != pre[j]) continue; // pre가 mask의 부분집합이 아님 -> 충족 못한상태
                    int nd = cur + dist[i][j];
                    if (nd < dp[mask | (1 << j)][j]) {
                        dp[mask | (1 << j)][j] = nd;
                    }
                }
            }
        }
        
        int ans = INF;
        for (int i = 0; i < k; i++){
            ans = Math.min(ans, dp[full - 1][i]);
        }
        return ans;
    }
    
    // 여기 bfs
    // (sr, sc)에서 모든 칸까지의 평면 최단거리.
    private int[][] bfs(int sr, int sc) {
        int[][] d = new int[n][m];
        for (int[] row : d) Arrays.fill(row, INF);
        ArrayDeque<int[]> q = new ArrayDeque<>();
        d[sr][sc] = 0;
        q.add(new int[]{sr, sc});
        int[] dr = {-1,1,0,0}; int[] dc = {0,0,-1,1};
        
        while(!q.isEmpty()){
            int[] c = q.poll();
            for (int t = 0; t < 4; t++){
                int nr = c[0] + dr[t]; int nc = c[1] + dc[t];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (g[nr].charAt(nc)== '#') continue;
                if (d[nr][nc] != INF) continue; // 큐에 넣을 때 방문 확정
                d[nr][nc] = d[c[0]][c[1]] + 1;
                q.add(new int[]{nr, nc});
            }
        }
        
        return d;
    }
}