class Solution {
    public int[] searchRange(int[] nums, int target) {
        
        int[] answer;

        // 이분탐색 두 번
        // 1. 시작 부분 찾기
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        
        int start = n;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            // 탐색 했는데 중간값이 현재 더 작거나 같다면
            if (nums[mid] >= target) {
                start = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // 2. 여기선 끝부분 찾기

        left = 0;
        right = n - 1;
        int end = n;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            // 탐색 했는데 중간값이 현재 더 작거나 같다면
            if (nums[mid] > target) {
                end = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (start == n || nums[start] != target){
            return new int[]{-1,-1};
        }

        answer = new int[]{start, end - 1};

        return answer;
    }
}