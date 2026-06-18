import java.util.*;

class Solution {
    public boolean canPartition(int[] nums) {

        int n = nums.length;
        int tot = 0;
        int target = 0;
        for (int num : nums) {
            tot += num;
        }

        if (tot % 2 == 0){
            target = tot / 2;
        } else {
            return false;
        }

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums){
            for (int t = target ; t >= num; t--){
                dp[t] = dp[t] || dp[t - num];
            }
        }
        
        if (dp[target]) {
            return true;
        } else {
            return false;
        }
    }
}