import java.util.*;

class Solution {
    public static int INF = 1_000_000;
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        // 0. 데이터 담아 놓기
        
        int[][] arr = new int[m][n];
        
        for(int i = 0; i < m; i++){
            Arrays.fill(arr[i], INF);    
        }
        
        for ( int i = 0; i < drops.length; i++){
            int v = i + 1;
            int r = drops[i][0];
            int c = drops[i][1];
            
            arr[r][c] = v;
        }
        
        
        
        // 1. ROW 기준으로 최소값 2차 배열 만들기
        int[][] rowMins = new int[m][n];
        
        for (int r = 0; r < m; r++){
            
            // 인덱스 담아놓을 deque
            Deque<Integer> dq = new ArrayDeque<>();
            
            for (int i = 0; i < n; i++){
                // 만약에 dq 맨 앞에 있는 값이 w-1(window 값) 보다 큰 경우 삭제
                // (궁금) 이게 해당 윈도우 내에서 최솟값일 일은 없나?
                // (대답) 이미 rowMins에 범위가 w - 1 보다 작을 때 반영 됨.
                if(!dq.isEmpty() && dq.peekFirst() <= i - w) dq.pollFirst();
                
                // 내부에 있지만 새로 들어온 거보다 앞이 커지면 더 작은걸 만날 때 까진 다 삭제
                while(!dq.isEmpty() && arr[r][i] <= arr[r][dq.peekLast()]) dq.pollLast();
                
                // 가장 마지막은 크든 작든 무조건 들어가기 때문에 add
                // dq가 Empty면 여기서 부터
                dq.addLast(i);
                
                // 만약에 i가 윈도우 범위 이상인 경우부터는 값 채우기
                if(i >= w - 1) rowMins[r][i - w + 1] = arr[r][dq.peekFirst()];
            }
        }
        
        // 2. col 기준으로 업데이트 하기
        int[][] ansArr = new int[m][n];
        
        // 이게 열
        for (int c = 0; c < n - w + 1; c++){
            
            Deque<Integer> dq = new ArrayDeque<>();
            for (int j = 0; j < m; j++){
                
                if(!dq.isEmpty() && dq.peekFirst() <= j - h) dq.pollFirst();
                while(!dq.isEmpty() && rowMins[dq.peekLast()][c] >= rowMins[j][c]) dq.pollLast();
                
                // dq 마지막은 항상 들어감
                dq.addLast(j);
                
                if(j >= h - 1) ansArr[j - h + 1][c] = rowMins[dq.peekFirst()][c];                
            }
        }
        
        // 3. 답 찾기
        int bestR = 0; 
        int bestC = 0;
        int bestV = 0;
        
        for (int i = 0; i < m - h + 1; i++){
            for (int j = 0; j < n - w + 1; j++){
                
                if(ansArr[i][j] > bestV){
                    bestV = ansArr[i][j];
                    bestR = i;
                    bestC = j;
                }
            }
        }
        
        return new int[]{bestR, bestC};
    }
}