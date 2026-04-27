import java.util.*;

class Solution {
    public long solution(String expression) {
        
        char[][] ops = new char[6][3];
        ops[0] = new char[]{'*', '+', '-'};
        ops[1] = new char[]{'*', '-', '+'};
        ops[2] = new char[]{'+', '*', '-'};
        ops[3] = new char[]{'+', '-', '*'};
        ops[4] = new char[]{'-', '*', '+'};
        ops[5] = new char[]{'-', '+', '*'};
        
        long answer = 0;
        
        for(char[] op : ops){
            
            // 일단 가장 첫번째 부터
            String s1 = calculated(expression, op[0]);
            String s2 = calculated(s1, op[1]);
            String s3 = calculated(s2, op[2]);
            
            // s3를 long으로 만들고 업데이트
            answer = Math.max(answer, Math.abs(Long.parseLong(s3)));
        }
        
        return answer;
    }
    
    public String calculated(String s, char c){
        
        int n = s.length();
        
        boolean exist = false;
        for (int i = 0; i < n; i++){
            if (s.charAt(i) == c) exist = true;
        }
        
        if(!exist) return s;
        
        List<String[]> st = new ArrayList<>();
            
        // c에 해당하면 ^이걸로 바꿔놓기
        s = s.replace(c, '^');
        
        List<String> vals = new ArrayList<>();
        List<Character> op = new ArrayList<>();
        
        int start = 0;
        for (int i = 0; i < n; i++){
            if(s.charAt(i) == '+' || s.charAt(i) == '*'){
                vals.add(s.substring(start, i));
                op.add(s.charAt(i));
                start = i+1;
            } else if (s.charAt(i) == '-') {
                if (i == 0) continue;
                if (s.charAt(i-1) == '+' || s.charAt(i-1) == '*'|| s.charAt(i-1) == '^') continue;
                
                vals.add(s.substring(start, i));
                op.add(s.charAt(i));
                start = i+1;
            }
        }
        // 연산 앞쪽만 들어가서 뒤쪽은 추가로 또 더해줘야
        vals.add(s.substring(start));
        
        // 이제 vals값 업데이트하기
        for (int id = 0; id < vals.size(); id++) {
            String val = vals.get(id);
            
            for (int i = 0; i < val.length(); i++){
                
                if (val.charAt(i) == '^'){
                    String[] ss = val.split("\\^");
                    Long former = Long.parseLong(ss[0]);
                    Long newV = former;
                    
                    if ( c == '+') {
                        for (int k = 1; k < ss.length; k++){
                            newV += Long.parseLong(ss[k]);
                        }
                    } else if (c == '-') {
                        for (int k = 1; k < ss.length; k++){
                            newV -= Long.parseLong(ss[k]);
                        }
                    } else {
                        for (int k = 1; k < ss.length; k++){
                            newV *= Long.parseLong(ss[k]);
                        }
                    }
                    vals.set(id, newV.toString());                    
                    break;
                }
            }
        }
        
        // 여기에서 합쳐서 출력해주기
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < op.size(); i++){
            sb.append(vals.get(i)).append(op.get(i));
        }
        sb.append(vals.get(vals.size() - 1));
        
        return sb.toString();
    }
}