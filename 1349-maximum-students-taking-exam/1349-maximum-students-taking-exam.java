import java.util.*;

class Solution {
    public static final int[] dr = {-1,-1,0,0,1,1};
    public static final int[] dc = {-1,1,-1,1,-1,1};

    public int m;
    public int n;
    public int V;
    public int totalSeats;

    public int S;
    public int T;

    public int[][] capacity;

    public int maxStudents(char[][] seats) {
        m = seats.length; // row
        n = seats[0].length; // col
        V = m * n + 2; // s, t 도 포함

        S = 0;
        T = V-1;

        capacity = new int[V][V]; 
        totalSeats = 0;

        // 일단 bipartite graph로 짝수열 / 홀수열 쪼개고 컨닝 가능한 자리만
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++){
                
                // 0. 현재가 '#' 아닌것 확인
                char ele = seats[r][c];
                if (ele == '#') continue;
                totalSeats++; // 비어 있지 않은 자리 수

                // 1. 홀수열 짝수열 구분
                // 1-1.홀수인 경우에는 t랑만 연결하고 바로 넘기기
                if(c % 2 != 0) {
                    capacity[getIdx(r,c)][V-1] = 1;
                    continue;    
                }
                // source와 연결은 미리 해두기
                capacity[0][getIdx(r,c)] = 1; // s - 짝 연결

                // 1-2. 짝수인 경우에 홀수랑 연결
                for(int i = 0; i < 6; i++) {                   

                    int nr = r + dr[i];
                    int nc = c + dc[i]; // dc가 무조건 +-1이라 -> 홀수로

                    // 일단 범위 내 및 '#' 아닌 것 확인
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || seats[nr][nc] == '#') {
                        continue;
                    }

                    capacity[getIdx(r,c)][getIdx(nr,nc)] = 1; // 짝 - 홀 연결
                }
            }
        }

        int maxFlow = 0;

        // 이제 여기서 bfs 기반 ford fulkerson 코드 짜보기
        while(true) {
            
            // PATH 찾기 : Edmond-Karp 알고리즘 형태로 BFS 기반으로 돌리기 
            int[] parents = bfs();

            // Path 가 없으면 종료 시키기
            if (parents == null) break;

            // path가 있으면 여기에서 이제 capacity 업데이트
            // Q) backward flow인지 forward flow인지에 따라서..?
            // 여긴 그냥 capacity 하나뿐인데?
            // Ford Fulkerson 공부했을 때는 backward인지 forward인지에 따라서 업데이트가 달랐던거 같은데

            int curId = T;
            while(curId != S) {
                
                int parent = parents[curId];
                // 부모 -> 자식 (그냥 G)
                int[] forward = {parent, curId};
                int[] backward = {curId, parent};

                // forward 방향으로는 capacity --
                // backward 방향으로 capacity++
                capacity[forward[0]][forward[1]]--;
                capacity[backward[0]][backward[1]]++;

                // 이제 curId를 부모로 올림
                curId = parent;
            }

            maxFlow++;
        }

        return totalSeats - maxFlow;
    }

    // s -> t로 가는 path가 residual G 상에 있는지 확인해서 해당 경로 return
    public int[] bfs(){
        int[] parents = new int[V];
        Arrays.fill(parents, -1);
        parents[S] = S;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(S);

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == T) return parents;
            
            for(int next = 0; next < V; next++){
                // 1) 이미 방문한 곳인지 2) 잔여 용량이 존재하는지 확인
                if (parents[next] != -1 || capacity[cur][next] == 0) continue;

                // 여기서부턴 방문 안했고, 
                // cur -> next잔여 용량도 있으니 parents 업데이트
                parents[next] = cur; // next의 부모가 cur

                queue.offer(next);
            }
            
        }

        return null; // 여기까지 와버리면 T연결 못한 것
    }


    // helper 메서드
    public int getIdx(int r, int c) {
        return n * r + c + 1;
    }
}