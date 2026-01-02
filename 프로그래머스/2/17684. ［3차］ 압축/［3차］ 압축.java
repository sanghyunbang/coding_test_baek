import java.util.*;

class Solution {
    public int[] solution(String msg) {
        
        int n = msg.length();

        Map<String, Integer> dict = new HashMap<>();
        for(char c = 'A'; c <= 'Z'; c++){
            dict.put(String.valueOf(c), c-'A'+1);
        }
        
        int nextMax = 26;        
        
        int i = 0;
        
        List<Integer> out = new ArrayList<>();
        
        while(i < n) {
            
            int j = i + 1;
            String w = msg.substring(i,j);
            
            // 2. w 찾기
            while(j<=n && dict.containsKey(msg.substring(i,j))){
                w = msg.substring(i,j);
                j++;
            }
            
            // 3. w에 해당하는 색인 번호 출력 -> 담기
            out.add(dict.get(w));
            
            // 4.
            if(j<=n){
                String wc = msg.substring(i,j);
                dict.put(wc, ++nextMax);
            }
            
            i += w.length();           
            
        }
        
        int[] answer = new int[out.size()];
        
        for (int l = 0; l < out.size(); l++){
            answer[l] = out.get(l);
        }
        
        return answer;
       
    }
}