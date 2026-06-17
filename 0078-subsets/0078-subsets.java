import java.util.*;

class Solution {
    int[] nums;
    int n;
    List<List<Integer>> answer = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        n = nums.length;

        // 공집합 셋팅
        answer.add(new ArrayList<>());

        backtracking(0, -1, new ArrayList<>());

        return answer;
    }

    // backtracking으로 구현 가능 할 듯
    // tree를 타고 들어가는 셈
    // 무조건 recentIdx >= level. [1,2]들어가면 [2, 1] 이런건 중복 안되게 없애기 
    public void backtracking(int level, int prevIdx, List<Integer> prev) {

        // 
        if (level > n || prevIdx == n-1) {
            return;
        }

        int curIdx = prevIdx + 1;
        for (int i = curIdx; i < n; i++){
            int num = nums[i];
            
            List<Integer> tempPrev = new ArrayList<>();
            for (int j : prev) {
                tempPrev.add(j);
            }

            tempPrev.add(num);
            List<Integer> current = tempPrev;
            answer.add(current);

            backtracking(level+1, i, current);
        }
        
    }
}