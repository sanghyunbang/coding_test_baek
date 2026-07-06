import java.util.*;

class Solution {
    public final int MAX = 1_000_000;
    public int networkDelayTime(int[][] times, int n, int k) {
        
        // 1. 일단 인접 리스트로 만들기
        List<List<int[]>> list = new ArrayList<>();

        for (int i = 0; i < n + 1; i++){
            list.add(new ArrayList<>());
        }

        int l = times.length;

        for (int i = 0; i < l; i++){
            int[] ele = times[i];
            int s = ele[0];
            int t = ele[1];
            int w = ele[2];
            list.get(s).add(new int[]{t, w});
        }

        // 2. 최단 거리 배열 초기화
        int[] dists = new int[n+1];
        Arrays.fill(dists, MAX);
        dists[0] = 0; // 0번째는 안쓰는 인덱스
        dists[k] = 0;

        // int[] : [인덱스, 누적거리]
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));

        pq.offer(new int[]{k,0});

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int curId = curr[0];
            int curDist = curr[1];

            // 방문 여부 확인하기 (Lazy ?)
            if(curDist > dists[curId]) continue;

            for (int[] adj : list.get(curId)) {

                int nextId = adj[0];
                int nextDist = curDist + adj[1];

                // dist비교 : curID노드 거쳐서 가는게 빠르면 그 거리로 업데이트
                if(nextDist < dists[nextId]) {
                    dists[nextId] = nextDist;
                    pq.offer(new int[]{nextId, nextDist});
                } 
            }
        }

        // 이제 dists 배열 훑으면서 최댓값 찾기
        PriorityQueue<Integer> findMax = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        
        for(int i : dists){
            findMax.offer(i);
        }

        int answer = findMax.poll();

        if (answer < MAX) {
            return answer;
        } else {
            return -1;
        }

    }
}