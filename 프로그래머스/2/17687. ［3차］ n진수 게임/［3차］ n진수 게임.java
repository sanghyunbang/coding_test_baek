import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        
        List<Character> list = new ArrayList<>(); 
            
        StringBuilder sb = new StringBuilder(t*m);
        
        // 한번에 만들어내는게 m 참가인원
        for(int i = 0 ; i < t*m ; i++) {
            
            String val = Integer.toString(i, n).toUpperCase();
            
            char[] chars = val.toCharArray();
            
            for(char c : chars) {
                list.add(c);    
            } 
        }
        
        // sb 업데이트 하기
        
        for (int i = p-1 ; i < t*m; i += m){
            
            if (sb.length() < t-1) {
                sb.append(list.get(i));
            } else if (sb.length() == t-1){
                sb.append(list.get(i));
                break;
            }
        }
        
        return sb.toString();
        
    }
}