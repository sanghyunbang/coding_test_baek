import java.util.*;

class Solution {
    public long solution(String expression) {
        
        char[][] opcc = {
            {'+', '-', '*'}, {'+', '*', '-'},
            {'-', '+', '*'}, {'-', '*', '+'},
            {'*', '+', '-'}, {'*', '-', '+'}
        };
        
        List<Long> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        
        // 처음에 숫자들
        int n = expression.length();
        
        // [ex]. 12 + 13 -> 12 + --> 근데 뒤에 13은?
        StringBuilder num = new StringBuilder();
        for (char c : expression.toCharArray()){
            if (c == '+' || c == '-' || c == '*'){
                ops.add(c);
                nums.add(Long.parseLong(num.toString()));
                num.setLength(0);
            } else {
                num.append(c);    
            }
        }
        nums.add(Long.parseLong(num.toString()));
        
        // 1. 여기서부터 이제 코드 시작
        

        long answer = 0;
        
        for(char[] cc : opcc){
            List<Long> numC = new ArrayList(nums);
            List<Character> opsC = new ArrayList(ops);
            for (char c : cc){
                calc(numC, opsC, c);               
            }
            answer = Math.max(answer, Math.abs(numC.get(0)));
        }
        return answer;
    }
    
    public void calc(List<Long> numC, List<Character> opsC, char c){
        
        // 100 200 300 500 20
        // 100 500 500 20
        for (int i = 0; i < opsC.size(); i++){
            if (opsC.get(i) == c){
                long v = helper(numC.remove(i), numC.remove(i), c);
                numC.add(i, v);
                opsC.remove(i);
                i--; // 해당 인덱스 이제 다시 확인해야 하는데 -> 여기서 빼야 위로 올라가서 ++ 해서 원래 idx
            }
        }
    }
    
    public long helper(long a, long b, char c){
        if (c == '+'){
            return a + b;
        } else if (c == '-'){
            return a - b;
        } else {
            return a * b;
        }
    }
    
}