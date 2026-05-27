import java.util.*;

class Solution {
    int M;
    int N;
    
    // 유일성 충족하는 리스트 뽑아내기
    List<Integer> unique = new ArrayList<>();
    String[][] relation;
    
    public int solution(String[][] relation) {
        
        N = relation.length; // 튜플 수
        M = relation[0].length; // 칼럼 수
        this.relation = relation;
        
        // 1. 칼럼 조함 PowerSet
        for (int i = 0; i < (1 << M); i++){
            uniqueness(i);
        }
        
        return uniqueAndMin();
    }
    
    public void uniqueness(int x) {
        
        for (int i = 0; i < N; i++){
            StringBuilder base = new StringBuilder();
            
            for (int c = 0 ; c < M; c++){
                // c 칼럼이 들어가는지 확인
                if ( (x & (1 << c)) != 0) {
                    base.append("-").append(relation[i][c]);
                }
            }
            
            for (int j = i+1; j < N; j++){
                StringBuilder compare = new StringBuilder();
                
                for (int c = 0 ; c < M; c++){
                    // c 칼럼이 들어가는지 확인
                    if ( (x & (1 << c)) != 0 ) {
                        compare.append("-").append(relation[j][c]);
                    }
                }
                
                if (base.toString().equals(compare.toString())) return;
            }            
        }
        
        unique.add(x); // 통과 한 경우 unique에 넣기
    }
    
    public int uniqueAndMin() {
        int answer = 0;
        int nn = unique.size();
        
        for (int i = 0; i < nn; i++){
            int base = unique.get(i);
            boolean minimal = true;
            
            for (int j = 0; j < nn; j++){
                if (j == i) continue;
                int comp = unique.get(j);
                
                int xor = base ^ comp;
                int check = comp & xor;
                
                if (check == 0) minimal = false;
            }
            
            if (minimal) answer++;
        }
        
        return answer;
    }
    
}