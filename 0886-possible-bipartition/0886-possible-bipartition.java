import java.util.*;

class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        /**
            1. 연결 된 것 끼리 그래프 연결하기
                1-1. 인접 리스트 만들어서 undirect하게 넣기
            2. bfs로 탐색해서 bipartition 여부 2-coloring으로 확인하기
         */

        // 1. 그래프 연결하기
        // 1-0. 기본 세팅
         List<Set<Integer>> adjList = new ArrayList<>();
         for (int i = 0; i <= n; i++) {
            adjList.add(new HashSet<>());
         }

        // 1-1. 인접 리스트 만들기
         for (int[] dislike : dislikes){
            int l = dislike[0]; 
            int r = dislike[1];

            adjList.get(l).add(r);
            adjList.get(r).add(l);
         }

         //2. bfs 탐색

         int[] colors = new int[n+1];
         
         for (int i = 1; i <= n; i++){
            if (colors[i] != 0) continue; // 이미 방문한 셈

            Queue<Integer> queue = new ArrayDeque<>();
            colors[i] = 1;
            queue.offer(i);

            while(!queue.isEmpty()) {
                int curNode = queue.poll();
                int curColor = colors[curNode];

                // 인접 노드들 뽑아 내기
                for (int adj : adjList.get(curNode)) {

                    if (colors[adj] == 0) {
                        colors[adj] = -curColor;
                        queue.offer(adj);
                    } else if (colors[adj] == curColor) {
                        return false;
                    }
                }
            }
         }

         // 여기까지 나오면 true
         return true;         
    }
}