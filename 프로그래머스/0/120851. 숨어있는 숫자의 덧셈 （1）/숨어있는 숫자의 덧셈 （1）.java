class Solution {
    public int solution(String my_string) {
        int answer = 0;
        
        char[] ch = {'1','2','3','4','5','6','7','8','9'};
        
        for (int i = 0; i < my_string.length(); i++){
            for (char c : ch){
                if (c == my_string.charAt(i)){
                    answer += c - '0';
                    break;
                }
            }
        }
        
        return answer;
    }
}