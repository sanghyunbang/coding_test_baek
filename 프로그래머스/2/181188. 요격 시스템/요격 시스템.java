import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        
        // 0. 끝점을 기준으로 정렬하기
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                                    (s1, s2) -> Integer.compare(s1[1], s2[1])
                            );
        
        for (int[] target : targets) {
            pq.offer(target);
        }
        
        int answer = 0;
        
        while(!pq.isEmpty()){
            answer++;
            int[] cur = pq.poll();
            int endPoint = cur[1];
            
            // 여기에서 현재 endPoint기준으로 지울 수 있는 것들 다 없애기
            // start가 endPoint가 작으면 삭제
            while(!pq.isEmpty() && pq.peek()[0] < endPoint){
                pq.poll();
            }
        }
        
        return answer;
    }
}