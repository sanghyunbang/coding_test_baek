import java.util.*;

class Solution {

    public int maxSubArray(int[] nums) {
        int n = nums.length;

        int maxAns = solutionByDp(n, nums);
        return maxAns;        

    }

    public int solutionByDp(int n, int[] nums) {
        int MIN_NUM = -100_000;
        int[] dp = new int[n + 1];
        dp[0] = MIN_NUM;
        int maxAns = MIN_NUM;

        for (int i = 0; i < n; i++){
            dp[i+1] = nums[i];
        }

        for (int i = 1; i <= n; i++){
            dp[i] = Math.max(dp[i], dp[i] + dp[i-1]);
        }

        for (int m : dp){
            maxAns = Math.max(maxAns, m);
        }

        return maxAns;
    }

    // public int solutionByDivideAndConquer() {

    // }
}