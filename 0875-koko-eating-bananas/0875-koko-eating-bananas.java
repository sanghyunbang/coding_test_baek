import java.util.*;

class Solution {
    int[] piles;
    int h;
    public int minEatingSpeed(int[] piles, int h) {
        
        this.piles = piles;
        this.h = h;

        int l = 1;
        int r = 1_000_000_000;
        int ans = 0;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (can(mid)) {
                ans = mid;
                r = mid -1;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }

    public boolean can(int k) {

        long result = 0;

        for (int pi : piles){
            long quo = pi / k;
            long remain = pi % k;

            if (remain == 0) {
                result += quo;
            } else {
                result += quo + 1;
            }
        }

        if (result <= (long)h) {
            return true;
        } else {
            return false;
        }
    }
}