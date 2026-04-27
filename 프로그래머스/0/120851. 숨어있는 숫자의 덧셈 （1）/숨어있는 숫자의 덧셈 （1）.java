class Solution {
    public int solution(String my_string) {
        int answer = 0;
        
        String s = my_string.replaceAll("[^0-9]","");
        
        for (int i = 0; i < s.length(); i++){
            answer += s.charAt(i) - '0';
        }
        
        return answer;
    }
}