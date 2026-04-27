import java.util.*;

class Solution {
    int n; // 칼럼 수
    int m; // 행수
    public int solution(String[][] relation) {
        
        
        // 
        n = relation[0].length;
        m = relation.length;
        
        Set<Integer> ansSet = new HashSet<>();
        
        for (int i = 1; i < (1 << n); i++){
            
            // minimality는 ansSet에 포함 여부로 확인
            // & 했을 때 자기값이 그대로 나오는 경우
            boolean minimal = true;
            for (int s : ansSet){
                if((s & i) == s) minimal = false;
            }
            
            if (!minimal) continue;
            
            // [여기선 minimality는 통과]
            if(!isUnique(i, relation)) continue; // uniqueness 통과 못하면 넘기기
            
            // [여기까지 오면 다 통과]
            ansSet.add(i);            
        }
           
        
        return ansSet.size();
    }
    
    public boolean isUnique(int i, String[][] relation){
        
        Set<String> set = new HashSet<>();
        // row 는 고정
        for (int r = 0; r < m; r++){
            StringBuilder sb = new StringBuilder();
            
            for (int c = 0; c < n; c++){
                
                int binInt = (1 << (n-1)) >> c;
                if((binInt & i) == 0) continue; // 0이면 해당 칼럼은 비포함
                
                sb.append(relation[r][c]).append(",");
            }
            set.add(sb.toString());
        }
        
        if (set.size() == m) {
            return true;
        } else {
            return false;
        }
        
    }
}