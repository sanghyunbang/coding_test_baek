class Solution {
    
    int answer = 0;
    int[] check = new int[5];
        
    public int solution(int n, int[][] q, int[] ans) {
        
        dfs(1, 0, q, ans, n);
        return answer;
    }
    
    public void dfs(int start, int depth, int[][] q, int[] ans, int n){
        
        // 종료 조건
        if (depth == 5){
            if(isValid(q, ans) ) answer++;
            return;
        }
        
        // 재귀
        for (int num = start; num <= n; num++){
            check[depth] = num;
            dfs(num + 1, depth + 1, q, ans, n);    
        }
        
    }
    
    public boolean isValid(int[][] q, int[] ans){
        int n = q.length;
        for(int i = 0; i < n; i++){
            
            int cnt = 0;
            int[] qq = q[i];
            
            for (int j = 0; j < 5; j++){
                
                int ele = qq[j];
                for (int cc : check){
                    if (cc == ele) cnt++;
                }
            }
            
            if (cnt != ans[i]) return false;
        }
        
        return true;
    }
}