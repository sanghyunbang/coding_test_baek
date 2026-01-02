import java.util.*;

class Solution {
    public String solution(String p) {
        return recursive(p);
    }
    
    public String recursive(String s) {
        
        if (s.equals("")) return "";
        
        String[] uv = splitUV(s);
        String u = uv[0];
        String v = uv[1];
        
        if (isRight(u)) {
            return u + recursive(v);
        } else {
            // 4-1
            StringBuilder sb = new StringBuilder();
            sb.append('(');
            // 4-2
            sb.append(recursive(v));
            // 4-3
            sb.append(')');
            // 4-4
            String mid = u.substring(1, u.length() - 1);
            
            for (int i = 0 ; i < mid.length(); i++){
                char c = mid.charAt(i);
                if(c == '(') sb.append(')');
                if(c == ')') sb.append('(');                
            }
            
            // 4-5
            
            return sb.toString();            
        }
        
        
        
    }
    
    public String[] splitUV(String s){
        int l = 0;
        int r = 0;
        int i = 0;
        
        char[] ch = s.toCharArray();
        
        while (i < ch.length) {
            
            if (ch[i] == '(') l++;
            if (ch[i] == ')') r++;
            i++;
            
            if (l==r) break;
        }
        
        String u = s.substring(0,i);
        String v = s.substring(i);
        
        return new String[] {u, v};
        
    }
    
    public boolean isRight(String s) {
        
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            
            if(c == '('){
                stack.push('(');
            } else {
                if(stack.isEmpty()) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        
        return stack.isEmpty();
        
    }
}