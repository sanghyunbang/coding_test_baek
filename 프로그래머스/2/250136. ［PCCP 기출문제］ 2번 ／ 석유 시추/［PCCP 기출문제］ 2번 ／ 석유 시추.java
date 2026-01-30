import java.util.*;

class Solution {
    
    class Node{
        int x; // 행
        int y; // 열
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    int[] dx = {1,-1,0,0};
    int[] dy = {0,0,1,-1};
    
    boolean[][] visited;
        
    public int solution(int[][] land) {
        int n = land.length;
        int m = land[0].length;
        visited = new boolean[n][m];
        
        int[] result = new int[m];
        
        for(int i=0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(land[i][j] == 1 && !visited[i][j]){
                    bundleWithBfs(i, j, land, result);
                }
            }
        }
        
        int answer = 0;
        
        for (int i = 0; i < m; i++) {
            answer = Math.max(answer,result[i]);
        }
        
        return answer;
    }
    
    public void bundleWithBfs(int startX, int startY, int[][] land, int[] result){
        
        Deque<Node> q = new ArrayDeque<>();
        q.offer(new Node(startX, startY));
        
        visited[startX][startY] = true;
        int sum = 0;
        Set<Integer> columns = new HashSet<>();
        
        while(!q.isEmpty()){
                
            Node base = q.poll();
            sum ++;
            columns.add(base.y);
            
            for(int i = 0; i < 4; i++){
                
                int curX = base.x + dx[i];
                int curY = base.y + dy[i];
                
                if(curX >= 0 && curX < land.length && curY >=0 
                   && curY < land[0].length && !visited[curX][curY] && land[curX][curY] == 1) {
                    visited[curX][curY] = true; // 방문표시
                    q.offer(new Node(curX, curY)); // 넣기
                    
                }  
            }    
        }
        
        for (int i : columns) {
            result[i] += sum;
        }
        
    }
}