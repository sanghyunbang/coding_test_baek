class Solution {
    public long solution(int a, int b) {
        long answer = 0;
        int small = Math.min(a,b);
        int large = Math.max(a,b);
        
        for (int v = small; v < large + 1; v++){
            answer += v;
        }
        return answer;
    }
}