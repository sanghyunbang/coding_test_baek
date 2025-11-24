import java.util.*;

public class Solution {
    public int solution(int n) {
        
        List<Integer> list = new ArrayList<>();
        
        while (n >= 10) {
            int e = n % 10;
            list.add(e);
            
            n = n/10;
        }
        
        list.add(n);
        
        int answer = 0;
        
        for(int a : list){
            answer += a;    
        }

        return answer;
    }
    
}