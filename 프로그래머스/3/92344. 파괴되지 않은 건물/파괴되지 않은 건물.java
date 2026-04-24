class Solution {
    public int solution(int[][] board, int[][] skills) {
        
        int rNum = board.length;
        int cNum = board[0].length;
        
        int[][] padding = new int[rNum + 1][cNum + 1];
        
        for (int[] skill : skills){
            
            int type = skill[0];
            int fr = skill[1];
            int fc = skill[2];
            int er = skill[3];
            int ec = skill[4];
            int v = skill[5];
            
            if (type == 1) v = -v;
            
            padding[fr][fc] += v;
            padding[fr][ec + 1] -= v;
            padding[er + 1][fc] -= v;
            padding[er + 1][ec + 1] += v;
        }
        
        // 가로 누적합
        for (int r = 0; r <= rNum; r++){
            int sum = padding[r][0];
            for (int c = 1; c <= cNum; c++){
                sum += padding[r][c];
                padding[r][c] = sum;
            }
        }
        
        // 세로 누적합
        for (int c = 0; c <= cNum; c++){
            int sum = padding[0][c];
            for (int r = 1; r <= rNum; r++){
                sum += padding[r][c];
                padding[r][c] = sum;
            }
        }
        
        int ans = 0;
        for (int r = 0; r < rNum; r++){
            for (int c = 0; c < cNum; c++){
                if (board[r][c] + padding[r][c] > 0) ans++;
            }
        }
        
        return ans;
    }
}