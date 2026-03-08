import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        // 1. 오름차순 정렬
        Arrays.sort(numbers);
        
        // 2. 가장 큰 두수
        int n = numbers.length;
        int answer = numbers[n-1] * numbers[n-2];
        return answer;
    }
}