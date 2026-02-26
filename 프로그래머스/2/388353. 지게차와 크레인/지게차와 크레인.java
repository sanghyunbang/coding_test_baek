import java.util.*;

class Solution {
    
    class Node{
        int x, y;
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    boolean[][] isOuter; // 바깥 물이 들어찰 수 있는 곳 : 바깥이고 빈 상태
    boolean[][] isEmpty;
    char[][] element;
    
    // 위-아래-오-왼
    int[] dx = {0,0,1,-1};
    int[] dy = {1,-1,0,0};
    
    int rNum, cNum;
    
    public int solution(String[] storage, String[] requests) {
        
        rNum = storage.length; 
        cNum = storage[0].length();
        
        // 0. 초기화 하기. 외곽은 'a'로 넣고 empty
        // 패딩 고려해서 +2개
        isOuter = new boolean[rNum+2][cNum+2];
        isEmpty = new boolean[rNum+2][cNum+2];
        element = new char[rNum+2][cNum+2];
        
        for (int i = 0; i < rNum+2; i++){
            for(int j = 0; j < cNum + 2; j++){
                
                if(i == 0|| j == 0 || i > rNum || j > cNum){
                    isOuter[i][j] = true;
                    isEmpty[i][j] = true;
                    element[i][j] = 'a';
                } else {
                    element[i][j] = storage[i-1].charAt(j-1);
                }
            }
        }
        
        for (String req : requests){
            int nn = req.length();
            char e = req.charAt(0);
             
            if (nn == 1) {
                // 1. 지게차 : 외곽만 순회
                porklift(e);
                // 2. 외곽 관련해서 업데이트 한번                
                doUpdate();
            } else { 
                // 1. 크레인 : 전체 순회해서 비우기 O(N*M)
                for (int i = 0; i < rNum + 2; i++){
                    for (int j = 0; j < cNum + 2; j++){
                        if (e == element[i][j]) {
                            isEmpty[i][j] = true;
                        }
                    }
                }
                
                // 2. 외곽 관련해서 업데이트 한번                
                doUpdate();
            }   
        }
        
        int answer = 0;
        
        for (int i = 0; i < rNum + 2; i++){
            for (int j = 0; j < cNum + 2; j++){
                if (!isEmpty[i][j]) {
                    System.out.println("i"+i+"j"+j+": "+element[i][j]);
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    public void porklift(char c){
        // helper 재사용
        Deque<Node> queue = fisrtQueue();
        
        // 상하좌우 탐색
        while(!queue.isEmpty()){
            Node node = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                
                int curX = node.x + dx[i]; // column
                int curY = node.y + dy[i]; // row
                
                // 1. 범위 이내인지
                if (curX < 0 || curY < 0 || curX > cNum+1 || curY > rNum+1) {
                    continue;
                }
                
                // 2. 해당 글자가 맞는지
                if(c != element[curY][curX]) continue;
                
                // 3. 범위내고 해당 글자가 맞으면 외곽이랑 맞닿으므로 지게차가 지울 수 있음
                isEmpty[curY][curX] = true;
                
            }
        }
        
                
    }
    
    // 외곽 업데이트 방법 1. BFS 기반
    public void doUpdate(){
                
        // 1. 큐로 outer인 애들 먼저 모아보기
        Deque<Node> queue = fisrtQueue();
        
        // 2. bfs 돌리기          
        // (주의) 방문 노드는 안가도록 : 아니면 계속 더해짐
        boolean[][] visited = new boolean[rNum+2][cNum+2];
        
        // 3. 여기서 본격적인 bfs 기반 업데이트
        while(!queue.isEmpty()){
            Node node = queue.poll();
            
            // 방문 업데이트
            visited[node.y][node.x] = true; // [row][column] 순서
                
            for (int i = 0; i < 4; i++) {
                
                int curX = node.x + dx[i]; // column
                int curY = node.y + dy[i]; // row
                
                // 1. 범위 이내인지
                if (curX < 0 || curY < 0 || curX > cNum+1 || curY > rNum+1) {
                    continue;
                }
                
                // 2. 비어있는게 맞는지
                if(!isEmpty[curY][curX]) continue;
                
                // 3. 이전에 방문 한 적이 없어야 함
                if (visited[curY][curX]) continue;
                
                // 4. 그러면 이제 queue에 isOuter에 추가하고 queue에 넣어주기
                Node ele = new Node(curX, curY);
                isOuter[curY][curX] = true;
                queue.offer(ele);                
            }
        }
        
    }
        
    // helper : 초기 큐 만드는 메서드
    public Deque<Node> fisrtQueue(){
        
        // 1. 큐로 outer인 애들 먼저 모아보기
        Deque<Node> queue = new ArrayDeque<>();
  
        for (int i = 0; i < rNum + 2; i++){
            for(int j = 0; j < cNum + 2; j++){
                if(isOuter[i][j]){
                    Node node = new Node(j, i); // j가 x(columm 번째), i 가 y(row 번째)
                    queue.offer(node);
                }
            }
        }
        return queue;
    }        
    
}