class Solution {
    public static int[] diffs;
    public static int[] times;
    public static long limit;
    
    public int solution(int[] diffs, int[] times, long limit) {
        
        // 필드로 
        this.diffs = diffs;
        this.times = times;
        this.limit = limit;
        
        // 숙련도 -> 퍼즐을 풀 때 틀리는 횟수
        
        // 퍼즐 난이도 : diff
        // 숙련도 : level
        
        // 최솟값 구하기 -> 완전탐색말고, DP나 이분탐색 써야 할듯
        
        // 만약에 diffs가 level보다 작거나 같은 경우와 큰 경우로 나뉨
        
        // 이분 탐색 써보기 -> 최솟값 찾기
        
        int r = 100_000;
        int l = 1;
        int answer = r;
        while(l <= r) {
            int mid = (r + l) / 2;
            
            // 성공하면 줄이고 실패하면 늘리고
            if(isPossible(mid)){
                answer = mid;
                // [성공] r 줄이기
                r = mid - 1;                
            } else {
                // [실패] l 늘리기
                l = mid + 1;                
            }
        }
        
        // 여기 나오면 l=r인 상태고 둘중 아무거나 answer하면 답?
        return answer;
    }
    
    public boolean isPossible(int level){
        
        long acc = times[0];
        
        for (int i = 1; i < diffs.length; i++){
            
            int diff = diffs[i];
            int time_cur = times[i];
            int time_prev = times[i-1];
            
            if (diff <= level){
                acc += time_cur; 
            } else {
                acc += (long)(time_cur + time_prev) * (diff - level) + time_cur;
            }
        }
        
        return (acc > limit) ? false : true;
    }
}