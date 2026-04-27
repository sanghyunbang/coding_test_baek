import java.util.*;

class Solution {
    
    public static int[] dr = new int[]{1, -1, 0, 0};
    public static int[] dc = new int[]{0, 0, 1, -1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for (int i = 0; i < 5; i++){
            answer[i] = solve(places[i]);
        }
        return answer;
    }
    
    // place에 대해서 허용 여부 알려주는 함수
    public int solve(String[] place){
        
        // 0. 5 * 5로 만들기
        char[][] arr = new char[5][5];
        
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                arr[i][j] = place[i].charAt(j);
            }
        }
        
        // 1. 이제 bfs돌리면서 길이 2까지 탐색하기
                
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                
                // 사람인 경우에만! 사람 아니면 문제
                if (arr[i][j] != 'P') continue;
                
                int minDist = Integer.MAX_VALUE;
                int[] start = new int[]{i, j, 0};
                Queue<int[]> q = new ArrayDeque<>();
                q.offer(start);
                
                boolean[][] visited = new boolean[5][5];
                visited[i][j] = true; // 시작점은 무조건 방문
                
                while(!q.isEmpty()){
                    int[] cur = q.poll();
                    int r = cur[0];
                    int c = cur[1];
                    int d = cur[2];
                    
                    // 만약에 이미 d가 2보다 크거나 같으면 다음을 볼 필요 없음
                    if(d >= 2) continue;
                    
                    // 이제 여기에서 사방 탐색
                    for (int k = 0; k < 4; k++){
                        int nr = r + dr[k];
                        int nc = c + dc[k];
                        int nd = d + 1;   
                        
                      
                        // [ 범위 ] 범위 초과 여부
                        if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;
                        // [ 방문여부 ] 방문 했으면 그냥 넘어가기
                        if(visited[nr][nc]) continue;
                        
                        
                        // 1) 막혀 있으면 아무것도 안함
                        // 2) 빈 테이블이면 q에 넣기
                        // 3) p면 q에 넣고 minDist 갱신하기
                        
                        if (arr[nr][nc] == 'X') {
                            continue;
                        } else if (arr[nr][nc] == 'O') {
                            int[] next = new int[]{nr, nc, nd};
                            visited[r][c] = true; // 넣을 때 방문
                            q.offer(next);                   
                        } else {
                            int[] next = new int[]{nr, nc, nd};
                            visited[r][c] = true; // 넣을 때 방문
                            q.offer(next);
                            minDist = nd;
                        }
                        
                        // 여기에서 만약 minDist가 2이하인게 나오면 그냥 바로 종료
                        if (minDist <= 2){
                            return 0;
                        }
                    }
                }
            }
        }
        
        // 여기까지 나왔으면 잘 된 것
        return 1;
        
    }
}