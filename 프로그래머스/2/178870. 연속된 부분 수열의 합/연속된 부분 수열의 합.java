class Solution {
    public int[] solution(int[] sequence, int k) {
        int n = sequence.length;
        int left = 0;
        int right = 0;
        int sum = 0;
        
        int minLen = Integer.MAX_VALUE;
        int[] answer = new int[2];

        // right 포인터를 이동시키며 탐색
        for (right = 0; right < n; right++) {
            sum += sequence[right];

            // 합이 k보다 크면 left를 당겨서 합을 줄임
            while (sum > k && left <= right) {
                sum -= sequence[left];
                left++;
            }

            // 합이 k와 일치할 때
            if (sum == k) {
                int currentLen = right - left;
                // 더 짧은 구간을 찾은 경우에만 업데이트
                // (같을 때는 업데이트 안 하므로 자연스럽게 앞쪽 인덱스 유지)
                if (currentLen < minLen) {
                    minLen = currentLen;
                    answer[0] = left;
                    answer[1] = right;
                }
            }
        }
        
        return answer;
    }
}