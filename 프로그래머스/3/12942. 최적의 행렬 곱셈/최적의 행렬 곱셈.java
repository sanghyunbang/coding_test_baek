class Solution {
    public int solution(int[][] ma) {
        
        int n = ma.length;
        
        // DP 생성해서 풀기 : i부터 j까지 곱했을 떄의 최솟값
        int[][] dp = new int[n][n];
        
        // k로 끊어서 앞이랑 뒤, 그리고 mergeCost를 포함해서 dp를 업데이트 해야 한다.
        // 이 때 문제는, 앞에 끊어진 행렬곱이 미리 계산되어 있어야 한다는 점.
        // 일단 행렬 '2'개 곱은 mergeCost만 바로 하면 됨. 행렬 1개 곱은 연산이 없으므로 0이기 떄문
        // 3개는 2개랑 1 개가 필요함
        // 4개는 3, 2, 1개가 필요함 -> 이런식을 고려할 때 미리 적은 개수의 곱만큼을 미리 업데이트 해놔야 함
        
        // gap은 행렬 곱의 행렬 개수 - 1이므로 n-1까지 가능
        for(int gap = 1; gap < n; gap++){
            
            // 시작점은 n - gap - 1 까지 가능. 왜냐하면 j가 gap만큼 i보다 커야하는데 이때 n-1번째가 가능해야 하기 때문
            // j는 i+gap으로 고정
            for (int i = 0; i < n - gap; i++){
                int j = i + gap;
                int minVal = Integer.MAX_VALUE;
                for (int k = i; k < j; k++){
                    // i번째와 k번째가 곱해진 상태 / 여기에 j 번째 곱해지면
                    int mergeCost = ma[i][0] * ma[k][1] * ma[j][1]; 
                    minVal= Math.min(dp[i][k] + dp[k+1][j] + mergeCost, minVal);
                }
                dp[i][j] = minVal;
            }
        }
        
        return dp[0][n-1];
    }
}