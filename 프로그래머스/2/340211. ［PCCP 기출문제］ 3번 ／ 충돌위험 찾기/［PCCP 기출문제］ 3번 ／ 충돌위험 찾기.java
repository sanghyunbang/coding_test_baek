import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        List<List<Integer>> xs = new ArrayList<>();
        List<List<Integer>> ys = new ArrayList<>();
        
        int maxT = 0;
        
        // i번 로봇의 r, c경로
        for(int i = 0; i < routes.length; i++) {
            List<Integer> x = new ArrayList<>();
            List<Integer> y = new ArrayList<>();
            
            // 진짜 시작점 t=0일 때 딱 한 번 추가
            int[] firstPoint = points[routes[i][0] - 1];
            x.add(firstPoint[0]);
            y.add(firstPoint[1]);
            
            for (int j = 1; j < routes[i].length; j++){
                
                // 아래 이동 하고 나서의 i가 됨
                int fromId = routes[i][j-1] - 1;
                int toId = routes[i][j] - 1;
                int[] from = points[fromId];
                int[] to = points[toId];
                             
                int rr = from[0];
                int cc = from[1];
                
                // R 이동 (부호 주의: to[0] 방향으로 가야 함)
                while (rr != to[0]) {
                    rr += (rr < to[0]) ? 1 : -1;
                    x.add(rr);
                    y.add(cc);
                }
                
                // C 이동
                while (cc != to[1]) {
                    cc += (cc < to[1]) ? 1 : -1;
                    x.add(rr);
                    y.add(cc);
                }
                
            }
            
            // maxT갱신하기
            maxT = Math.max(maxT, x.size());
            
            // 이제 여기에서 해당 로봇 x와 y 넣기
            xs.add(x);
            ys.add(y);
        }
        
        // 이제 여기에서 겹치는 구간 찾아보기
        int ans = 0;
        
        for (int t = 0; t < maxT; t++){
            
            // 특정 시간 t에 어떤 좌표에 로봇이 몇대 있는지
            int[][] map = new int[101][101];
            
            for(int i = 0; i < routes.length; i++){
                if (t < xs.get(i).size()){
                    int curR = xs.get(i).get(t);
                    int curC = ys.get(i).get(t);
                    
                    map[curR][curC]++;
                }
            }
            
            // 이번 시간 t에 2대 이상 겹친 칸 세기
            for (int r = 1; r <= 100; r++){
                for (int c = 1; c <= 100; c++){
                    if (map[r][c] >= 2) ans++;
                }
            }
        }
        
        // 위험한 상황의 횟수를 return
        return ans;
    }
}