import java.util.*;

class Solution {
    
    static class Node {
        int idx; // 나의 사원 번호
        int myLeader; // 나의 팀장
        List<Integer> myTeam; // (팀장인 경우) 나의 팀원
        int sales; // 매출액
        
        Node(int idx, int myLeader, List<Integer> myTeam, int sales){
            this.idx = idx; this.myLeader = myLeader; this.myTeam = myTeam; this.sales = sales;
        }
        
        Node(int idx, int sales){
            this.idx = idx; this.sales = sales;
        }
    }
    
    public int solution(int[] sales, int[][] links) {        
        // 구현 하는게 문제 : 일단 맨 밑의 노드 부터 진행을 해야 할 것 같은데
        // leaf 노드 인지 판단을 어떻게 하지? -> myTeam이 null(혹은 empty)인 경우
        
        int n = sales.length;
        
        // 해시로 idx를 기준으로 Node들을 집어 넣어놔도 되나?
        Map<Integer, Node> tree = new HashMap<>();
                
        // sales
        for (int i = 0; i < n ; i++){
            // tree에 매출 관련 정보만 담은 노드를 담아 보기
            Node node = new Node(i+1, sales[i]);
            tree.put(i+1, node);            
        }
        
        for( int[] relation : links ){
            int leader = relation[0];
            int member = relation[1];
            
            // 업데이트할 노드 가져오기
            Node leaderNode = tree.get(leader);
            Node memberNode = tree.get(member);
            
            // memberNode의 팀장 업데이트
            memberNode.myLeader = leader;
            
            // leaderNode의 팀원 업데이트
            if (leaderNode.myTeam == null || leaderNode.myTeam.isEmpty()){
                leaderNode.myTeam = new ArrayList<>();
            }
            
            leaderNode.myTeam.add(member);
        }
        
        // ============= 여기까지 자료구조 완료==============
        
        // 이제부터 할 것 : dp[idx][0] dp[idx][1] 배열을 만들고 '후위 순회 DFS'로 리프부터 계산해서 올라오기
        
        int[][] dp = new int[n+1][2];
        
        dfs(1, tree, dp);
        
        return Math.min(dp[1][0], dp[1][1]);
    }
    
    private void dfs(int u, Map<Integer, Node> tree, int[][] dp){
        Node node = tree.get(u);
        
        // 리프 노드면 -> 이것만
        if (node.myTeam == null || node.myTeam.isEmpty()){
            dp[u][1] = node.sales;
            dp[u][0] = 0;
            return;
        }
        
        // 여기서 부터는 리프 노드가 아닌 경우
        
        int attendSum = node.sales; // (초기값) 해당 노드가 들어가면 우선 자기자신은 포함
        int absentSum = 0; // (초기값) 해당 노드가 포함 안되는 case면 0
        boolean hasAttendChild = false; // 0 케이스에서 쓰일 제약조건
        int extra = Integer.MAX_VALUE;
        
        for (int v : node.myTeam){
            
            dfs(v, tree, dp);
            
            attendSum += Math.min(dp[v][0], dp[v][1]);
            
            if(dp[v][0] < dp[v][1]){
                absentSum += dp[v][0];
                extra = Math.min(extra, dp[v][1] - dp[v][0]);
            } else {
                absentSum += dp[v][1];
                hasAttendChild = true;
            }
        }
        
        dp[u][1] = attendSum;
        dp[u][0] = absentSum + (hasAttendChild ? 0 : extra);
        
    }
}




