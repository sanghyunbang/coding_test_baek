import java.util.*;

class Solution {
    
    private long splitLimit;
    private Map<String, Long> memo = new HashMap<>();
    
    public long solution(int dist_limit, int split_limit) {
        this.splitLimit = (long) split_limit;
        
        return dp(1L, 1L, dist_limit);
        
    }
    
    // dp(n, m, d) 반환값 : 현재 레벨에 n개 노드, 분배도 m, 앞으로 d개 분배노드에 더 쓸수 있는 상황에서 최대 리프
    private long dp(long n, long m, int d) {
        
        // 기저조건 1 : 분배 노드 예산이 없음 -> 전부 리프
        if(d == 0) return n;
        
        // 기저조건 2 : 자식 수 최소값 2로도 분배도 초과 -> 전부 리프
        if(m * 2 > splitLimit) return n;
        
        // n 캡핑 : n > d면 분배 노드로 쓸 수 있는건 최대 d개
        // 나버지 n-d는 결국 리프가 됨
        long extraLeaves = 0L;
        if(n > d) {
            extraLeaves = n - d;
            n = (long) d;
        }
        
        // 메모제이션 키
        String key = n + "," + m + "," + d;
        if(memo.containsKey(key)) {
            return memo.get(key) + extraLeaves;
        }
        
        // 기본값 : k=0 (현재 레벨 전부 리프)
        long best = n;
        
        for (int c : new int[]{2,3}) {
            
            // 자식 수 c를 선택했을 때 다음 레벨 분배도
            if ( m * c > splitLimit) break;
            
            // k 최적화 : k를 다 돌지 않고 최댓값만
            long k = n;
            long leafsNow = n - k;
            
            long nextN = k*c; // 다음레벨 노드 수
            long nextM = m*c; // 다음 레벨 분배도
            int nextD = d - (int) k; //남은 예산
            
            long total = leafsNow + dp(nextN, nextM, nextD);
            best = Math.max(best, total);
        }
        
        memo.put(key, best);
        return best + extraLeaves;
        
    }
}