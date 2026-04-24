import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        
        // 0. 간선 리스트를 인접 리스트로 바꿔서, 화살표를 받는 게 없는 생성된 정점 찾기
        Map<Integer, List<Integer>> Rmap = new HashMap<>(); // to : from -> 역방향 인접 리스트 (누구로부터 받았나?)
        Map<Integer, List<Integer>> map = new HashMap<>(); // from : to -> 일반적인 인접 리스트
        
        for (int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            
            Rmap.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
            Rmap.computeIfAbsent(from, k -> new ArrayList<>());
                
            map.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            map.computeIfAbsent(to, k -> new ArrayList<>());            
        }
        
        Set<Integer> kSet = Rmap.keySet();
        
        int genId = 0;
        
        for (int key : kSet) {
            
            if (Rmap.get(key).size() == 0 && map.get(key).size() > 1) {
                genId = key;
            }
        }
        
        // 1. 생성된 점에서 연결된 부분들 훑어보면서 유형 찾기
        List<Integer> candids = map.get(genId);
        
        int donut = 0;
        int bar = 0;
        int eight = 0;
        
        for (int start : candids) {
            // 만약에 계속 따라가 갔을 때 start가 다시 안나오면 막대
            int next = start + 1; // start가 아닌 아무 값이나 일단 주기
            
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(start);
            
            boolean isEight = false;
            
            // 나눠서 체크하자!
            // [1단계] 8자인지 체크하기
            while(!q.isEmpty() && next != start){
                int cur = q.poll();
                List<Integer> adjs = map.get(cur);
                
                // 8인지 확인 : 8자인 경우만 두 개의 인접 행렬을 가짐
                if (adjs.size() > 1) {
                    eight++;
                    isEight = true;
                    break;
                }
                
                // 막대 끝의 경우는 없을 수도 있음
                if (adjs.size() > 0){
                    q.offer(adjs.get(0));
                    next = adjs.get(0);                    
                }
            }
            
            // 이제 여기서 부턴 8자가 아닌 경우, 두 가지 중 무엇이 맞는지 찾기
            if (!isEight) {
                next = start;
                List<Integer> nn = map.get(next);
                
                while(true){
                    
                    if (nn.size() < 1){
                        bar++;
                        break;
                    }
                    
                    if (nn.get(0) == start) {
                        donut++;
                        break;
                    }
                    
                    next = nn.get(0);
                    nn = map.get(next);
                }
            }
            
        }
                
        // 1) 생성한 정점의 번호
        // 2) 도넛 모양 그래프의 수
        // 3) 막대 모양 그래프의 수
        // 4) 8자 모양 그래프의 수
        return new int[]{genId, donut, bar, eight};
    }
}