class Solution {
    public int solution(int[][] info, int n, int m) {
        
        // dp 배열 만들기
        // 해당 (a흔적, b흔적)로 체포 안되는게 가능한지
        boolean[][] dp = new boolean[n][m];
        
        // 둘다 아무것도 안훔치면 체포안됨
        dp[0][0] = true;
        
        for (int[] goods: info){
            
            // 초기화 : 모두 다 false인 상태
            boolean[][] next_dp = new boolean[n][m];
                
            // A의 흔적 상태
            for (int i =0; i < n ; i++){    
                // B의 흔적 상태
                for(int j = 0; j < m; j++){
                    // 이전에 true인 것만 고려하면 됨
                    if (dp[i][j] == true){
                        
                        // case1: A가 훔치는 경우
                        if (i + goods[0] < n){
                            // 통과하는 경우만 true로
                           next_dp[i+goods[0]][j] = true;
                        }
                        
                        // case2 : B가 훔치는 경우
                        if (j + goods[1] < m){
                            next_dp[i][j+goods[1]] = true;
                        }
                    }                    
                }
            }
            
            dp = next_dp;
        }
        
        int answer = -1;
        
        for (int i = 0; i < n; i++){
            
            for (int j = 0; j < m; j++){
                
                if(dp[i][j] == true) {
                    
                    return i;
                }
            }
            
        }
        
        return answer;
        
        
        
        
    }
}