import java.util.*;

class Solution {
    private static final int INF = 1 << 28;
    String[] g;
    int n, m;
    public int solution(int h, String[] grid, int[][] panels, int[][] seqs) {
        
        g = grid;
        n = grid.length;
        m = grid[0].length();
        int k = panels.length;
        
        // 0-1. 엘리베이터 위치 찾기 (0-index)
        int er = -1;
        int ec = -1;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if(grid[i].charAt(j) == '@') {
                    er = i;
                    ec = j;
                }
            }
        }
        
        // 0-2. panel 위치 f, r, c 로 따로 구분해서 넣어놓기
        int[] pf = new int[k]; 
        int[] pr = new int[k];
        int[] pc = new int[k];
        
        for (int i = 0; i < k; i++){
            pf[i] = panels[i][0];
            // 이것들은 dist에서 인덱스로 쓰이니까 0-index로
            pr[i] = panels[i][1] - 1; 
            pc[i] = panels[i][2] - 1;
        }
        
        // 1. from : 각각의 패널과, 엘리베이터에서 모든 통로 까지 거리
        int[][][] from = new int[k+1][][];
        for (int i = 0; i < k; i++){
            from[i] = bfs(pr[i], pc[i]);
        }
        from[k] = bfs(er, ec);
        
        // 2. dist : 각각 패널에서 -> 다른 패널로 거리 (엘리베이터 까지 이제는 고려)
        int[][] dist = new int[k][k];
        
        for (int i = 0; i < k; i++){
            for (int j = 0; j < k; j++){
                if (pf[i] == pf[j]) {
                    dist[i][j] = from[i][pr[j]][pc[j]];
                } else {
                    int a = from[i][er][ec];
                    int b = Math.abs(pf[i] - pf[j]) + from[k][pr[j]][pc[j]];
                    dist[i][j] = a + b;
                }
            }
        }
        
        // 3. pre[i] : i를 실행하기 위한 선행 조건
        int[] pre = new int[k];
        for (int[] seq : seqs){
            int latter = seq[1] -1; // 0 - index
            int former = seq[0] -1;
            
            pre[latter] |= (1 << former);
        }
        
        // 4. dp 돌리기 : 순서에 따른 경우의 수 분기를 없앨 수 있음
        // dp[현재까지 켜진 패널 상태][마지막으로 켠 패널]
        int[][] dp = new int[1 << k][k];
        
        // dp기본은 INF로 (dp에서 정의 불가능한 부분 막아놓기도 됨)
        for (int[] d : dp){
            Arrays.fill(d, INF);
        }
        
        // CASE 1) 초기 pre에 없는 경우
        for (int i = 0; i < k; i++){
            if (pre[i] == 0) {
                // [i만 켜진 상태][i로] 가는 최소 거리 = dist[1<<i][i];
                dp[1 << i][i] = dist[0][i];  
            }
        }
        
        // CASE 2) pre가 있는 경우
        // dp[][i를 마지막으로 킴] -> dp[j추가로 킨 상태][j를 마지막으로 킴] 업데이트
        for (int mask = 0; mask < (1 << k); mask++){
            for (int i = 0; i < k; i++){
                
                // mask에 i가 없으면 정의상 불가능 
                if ( (mask & (1 << i)) != (1 << i)) continue;

                // i가 켜질 수 있는 선행 조건이 만족된 상태인지
                if ( (pre[i] & mask) != pre[i]) continue;
                
                
                for(int j = 0; j < k; j++){                
                    // 1. j가 현재 상태에 이미 켜져 있다면 중복이므로 패스
                    if ((mask & (1 << j)) != 0) continue;
                    // 2.j가 켜질 수 있는 선행 조건이 만족된 상태인지
                    if ( (pre[j] & mask) != pre[j]) continue;
                    
                    int nState = mask | (1 << j);
                    int nDist = dp[mask][i] + dist[i][j];
                    
                    // i를 타고 가는게 더 짧으면 업데이트
                    if ( nDist < dp[nState][j]) {
                        dp[nState][j] = nDist;
                    }
                }
            }
        }
        
        int answer = INF;
        
        for (int i = 0; i < k; i++){
            answer = Math.min(answer, dp[(1 << k) - 1][i]);
        }
        
        return answer;
    }
    
    public int[][] bfs(int sr, int sc){
        
        int[][] d = new int[n][m];
        for (int[] row : d) {
            Arrays.fill(row, INF);    
        }
            
        d[sr][sc] = 0; // 자기 자신 거리는 0
        
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];        
        
        q.offer(new int[]{sr, sc});
        visited[sr][sc] = true;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
            
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            int cr = cur[0];
            int cc = cur[1];
            
            for (int i = 0; i < 4; i++){
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                
                // 범위내 및 안막혀야 함
                if (nr < 0 || nr >= n || nc < 0 || nc >= m || g[nr].charAt(nc) == '#') {
                    continue;
                }
                // 방문 안함
                if (visited[nr][nc] == true) {
                    continue;
                }
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc});
                d[nr][nc] = d[cr][cc] + 1;                
            }
            
        }
         
        return d;
    }
}