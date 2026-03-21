import java.util.*;

class Solution {
    boolean[] infected;
    int infNum = 0; // result 값
    int maxDepth;
    int nodes;

    List<List<Integer>> listA = new ArrayList<>();
    List<List<Integer>> listB = new ArrayList<>();
    List<List<Integer>> listC = new ArrayList<>();
    
    public int solution(int n, int infection, int[][] edges, int k) {
        
        // 감염 초기화
        infected = new boolean[n+1];
        infected[infection] = true;
        maxDepth = k;
        nodes = n;    
        
        // 리스트 속에 또 리스트 있어야 하니까. 초기화 해놓기
        // 주의 : 노드 번호하면 거기에 맞게 나와야 하니까 n+1개로
        for (int i = 0; i < n+1; i++) {
            listA.add(new ArrayList<>());
            listB.add(new ArrayList<>());
            listC.add(new ArrayList<>());            
        }
        
        // 이제 edge연결관계 채우기
        for(int[] edge : edges){
            int l = edge[0];
            int r = edge[1];        
            int type = edge[2];

            //무방향(양방향) 넣기
            if(type == 1){
                listA.get(l).add(r);
                listA.get(r).add(l);
            } else if(type == 2) {
                listB.get(l).add(r);
                listB.get(r).add(l);
            } else {
                listC.get(l).add(r);
                listC.get(r).add(l);
            }
        }
        
        backtracking(0,0);
        
        return infNum;

    }
    
    // bfs 돌려서 감염 시뮬
    public int bfs(List<List<Integer>> graph){
        
        int newInf = 0;

        Queue<Integer> que = new ArrayDeque<>();

        // 완전 탐색 
        boolean[] visited = new boolean[nodes + 1];
        
        // 감염 전파 시키기
        for (int i = 1; i <= nodes; i++){
            
            // 방문했으면 통과
            if(visited[i]) continue;            
            // i는 감염 시작점
            if(!infected[i]) continue; // 감염 안됐으면 통과 -> 전파 시작점이 안됨            
            // 감염체가 엮이지 않은 곳은 애초에 방문조차 할 필요 없음
            
            // 여기선 방문도 안했고 감염이 된 경우
            que.offer(i);

            visited[i] = true;
            
            while(!que.isEmpty()) {
                int now = que.poll();
                newInf++; // 감염을 확인하고 감염자 수 1 더하기

                List<Integer> conns = graph.get(now);

                // 연결 된 것들 다 감염시키기고 que에 집어넣기
                for(int conn: conns){
                    
                    // 이미 방문했으면 패스 (순환 트리일 경우 무한히 도는거 방지)
                    if(visited[conn]) continue;
                    
                    // 방문 이력이 없는 곳이면
                    visited[conn] = true;
                    infected[conn] = true;
                    que.offer(conn);
                }
                
            }    
        }
        
        return newInf;
    }
    
    //backtracking
    public void backtracking(int depth, int val) {
        
        // 만약 k만큼 다 탐색하면
        if (depth == maxDepth) {
            infNum = Math.max(infNum, val);
            return;
        }
        
        // 순열 조합
        for (int i = 1; i<= 3; i++){
            
            // 초기 값
            boolean[] tempArr = new boolean[nodes+1];
            for(int j = 0; j < tempArr.length; j++){
                tempArr[j] = infected[j];
            }
            
            int plus = 0;
            if (i==1){
                plus = bfs(listA);        
            } else if (i==2){
                plus = bfs(listB);        
            } else{
                plus = bfs(listC);        
            }
            
            int myVal = infNum + plus;
                        
            backtracking(depth + 1, plus);
            
            // 여기서 다시 되돌리기
            for(int j = 0; j < tempArr.length; j++){
                infected[j] = tempArr[j];
            }            
        }
        
    }
}