class Solution {
    
    public int solution(int n, int[] money) {
        
        int[] dp = new int[n+1];
        
        // 0원 만드는 경우만 1로 업데이트 된 상황
        dp[0] = 1;
        
        for (int m : money) {
            for (int i = m; i < n+1; i++){
                dp[i] = dp[i] + dp[i-m];
            }
        }
        
        return dp[n];
    }
}