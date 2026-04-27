class Solution {
    public int solution(int[][] board, int[][] skill) {
        
        int m = board.length;
        int n = board[0].length;
        
        int[][] padding = new int[m+1][n+1];
        
        for (int[] sk : skill){
            int type = sk[0];
            int r1 = sk[1];
            int c1 = sk[2];
            int r2 = sk[3];
            int c2 = sk[4];
            int v = sk[5];
            
            if (type == 1) v = -v;
            
            padding[r1][c1] += v;
            padding[r1][c2 + 1] -= v;
            padding[r2 + 1][c1] -= v;
            padding[r2 + 1][c2 + 1] += v;
        }
        
        // 칼럼방향 : -> 으로 누적합 
        for(int r = 0; r <= m; r++){
            for(int c = 1; c <= n; c++){
                padding[r][c] += padding[r][c-1];
            }
        }
        
        // 로우방향 : (위아래) 으로 누적합 
        for(int r = 1; r <= m; r++){
            for(int c = 0; c <= n; c++){
                padding[r][c] += padding[r-1][c];
            }
        }
        
        // board와 padding더하기
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                board[i][j] += padding[i][j];
            }
        }
        
        // 답 구하기
        int answer = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if (board[i][j] > 0) answer++;
            }
        }        
        
        return answer;
    }
}