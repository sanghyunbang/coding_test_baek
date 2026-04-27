class Solution {
    public int solution(int n) {
        int quo = n / 7;
        int remain = n % 7;
        
        int answer = quo;
        
        if (remain > 0) {
            answer += 1;
        }
        
        return answer;
    }
}