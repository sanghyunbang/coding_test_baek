class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int n = absolutes.length;
        
        int answer = 0;
        for (int i = 0 ; i < n ; i++) {
            if (signs[i]){
                answer += absolutes[i];
            } else {
                answer -= absolutes[i];
            }
        }
        return answer;
    }
}