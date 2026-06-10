import java.util.*;

class Solution {
    public boolean isBipartite(int[][] graph) {
        
        /**
            1. bfs 돌면서 인접 노드들 다른 색깔 주기
            2. 다음 노드에서 기존에 색깔 있으면 다른지 확인하고, 없으면 나와 다른 색깔 주기
            2-1. 이전 방문한 애는 무조건 모순 없음.(연결 노드에 다른 색깔 주고, 있으면 확인하기 떄문)
            2-2. 현재 방문한 경우 확인하면 됨. 방문 안한 애들만 색깔 주기 때문에 이전 방문에 영향 없음 
         */

         int n = graph.length;
         int[] colors = new int[n];

         for (int idx = 0 ; idx < n; idx++) {
            if (colors[idx] != 0) continue; // 이미 방문했으면 지나치기 
            
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(idx);
            colors[idx] = 1;

            while (!queue.isEmpty()) {

                int curNode = queue.poll(); // 현재 노드 꺼내기
                int curColor = colors[curNode];

                // 인접 리스트 돌기
                for (int i : graph[curNode]) {

                    if (colors[i] == 0) {
                        // 방문을 아예 안한 경우
                        colors[i] = -curColor;
                        queue.offer(i);

                        // 이전에 방문을 한 경우 -> queue에 안넣음
                    } else if (colors[i] != curColor) {
                        // 이렇게 하면 지금 문제가 아니면 그냥 넘어감
                        continue;    

                    } else if (colors[i] == curColor) {
                        return false;
                    }
                }
            }
         }

         
         // 여기까지 오면 통과
         return true;
    }

}