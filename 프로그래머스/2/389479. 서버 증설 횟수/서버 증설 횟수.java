class Solution {
    
    public int[] capa;
    
    public int solution(int[] players, int m, int k) {
        
        capa = new int[24];
        
        
        // 초기화
        for(int i = 0; i < 24; i++) {
            capa[i] = m-1;
        }
        
        int result = 0;
        
        for(int i =0; i < 24; i++){
            
            if (players[i] <= capa[i]) {
                System.out.println("[통과] players"+i+": " +players[i]+ "| capa"+i+": "+capa[i]);
                continue;
            } else {
                while(players[i] > capa[i]){
                    
                    System.out.println("players"+i+": " +players[i]+ "| capa"+i+": "+capa[i]);
                    expand(i, k, m);
                    result++;
                }
            }
        }
        
        return result;
        
    }
    
    // 증설
    public void expand(int t, int k, int m){
        
        for (int i=0; i < k; i++){
            // t: 현재시간 섹터
            int idx = t+i;
            if(idx < 24) {
                capa[idx] += m;
            }
        }
        
    }
}