class Solution {
    public int[] solution(long n) {
        
        int size = (int)(Math.log10(n) + 1.0);
        
        int[] answer = new int[size];
        
        for (int i = 0; i < size; i++){
            answer[i] = (int)(n % 10);
            n /= 10;
        }
        
        return answer;
    }
}