class Solution {
    public int searchInsert(int[] nums, int target) {
        
        // 존재하면 - > 해당 인덱스
        // 없으면 최솟값

        int n = nums.length;
        int l = 0;
        int r = n - 1; // ex-3 처럼 범위 초과할 수도 있음
        int answer = r;

        while(l <= r){
            int mid = l + (r - l) / 2;

            // l의 위치가 정답인 것 가틈
            // r이 정답일 수도 -> 만약 모든 값보다 작으면

            if (nums[mid] == target){
                return mid;
            } else if (nums[mid] > target){
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        // 이제 l, r과 target값 비교해보기
        // r < l인 상태고, 만약에 target이 r인덱스보다 크면 r + 1 = l 이 정답이고
        // target이 r보다 작으면 r이 정답


        // [주의] r이 -1일 수도 있음
        if (r < 0) {
            return 0;
        }
        if (nums[r] < target) {
            return l;
        } else {
            return r;
        }
    }
}