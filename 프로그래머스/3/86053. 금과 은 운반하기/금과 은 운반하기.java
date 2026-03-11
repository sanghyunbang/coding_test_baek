class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        
        // 이분 탐색으로 풀어보기 (최솟값 구하기)
        // 범위: 시간 T
        long left = 0L;
        long right = 400_000_000_000_000L;
        long answer = right; // 최악의 경우로 초기화
        
        while(left <= right){
            // mid가 이분탐색에선 체크 대상
            long mid = left + (right - left) / 2L;
            
            long goldSum = 0L;
            long silverSum = 0L;
            long totalSum = 0L;
            
            for (int i = 0; i < g.length; i++){
                
                long rt = 2L * t[i]; // 왕복
                long cnt = mid / rt;
                
                if (mid % rt >= t[i]) cnt++; // 남은게 편도 만큼 남으면 한 번 더 갈수 있음
                
                // 금 조건
                goldSum += Math.min(cnt * w[i], (long) g[i]);
                
                // 은 조건
                silverSum += Math.min(cnt * w[i], (long)s[i]);
                
                // 통합 조건
                totalSum += Math.min((long) s[i]+g[i], cnt * w[i]);
                
            }
            
            if (goldSum >= (long)a && silverSum >= (long)b && totalSum >= (long)a + b){
                answer = mid;
                right = mid -1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
        
    }
}