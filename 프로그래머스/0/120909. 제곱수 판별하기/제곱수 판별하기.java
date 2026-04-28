class Solution {
    public int solution(int n) {
        int answer = 2;
        
        for (int i = 1; i <= n ; i++){
            long x = i * i;
            long y = (long) n;
            
            if (x == y) {
                answer = 1;
                break;
            }
        }
        
        return answer;
    }
}