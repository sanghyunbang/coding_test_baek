import java.util.*;

class Solution {
    class Node{
        int id, x, y;
        Node left, right;
        
        Node(int id, int x, int y){
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }
    
    public int idx;
    public int[][] answer;
    public List<Node> list;
    
    public int[][] solution(int[][] nodeinfo) {
        
        // 0. 일단 y축 기준으로 정렬, y 축 같으면 x축 기준으로 정렬해보기
        list = new ArrayList<>();
        
        // 일단은 리스트에 다 집어 넣기
        for (int i = 0; i < nodeinfo.length; i++){
            Node node = new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]);
            list.add(node);
        }
        
        // 리스트 정렬
        list.sort((s1, s2) -> {
                    if (s1.y == s2.y){
                        return Integer.compare(s1.x, s2.x);
                    }
                    return Integer.compare(s2.y, s1.y);
                }
        );

        // 1. 이제 Node 별로 left, right에 값 넣어서 트리 연결 
        // 루트로부터 타고 들어가면서 해당 노드가 누구네 자식인지 확인하고 넣기
        for (int i = 1; i < list.size(); i++){
            makeTree(list.get(0), list.get(i));
        }
        
        
        // 이제 트리들도 다 채워졌으니 전위 순회, 후위 순회 하기
        answer = new int[2][nodeinfo.length];
 
        idx = 0;
        preorder(list.get(0));
        
        idx = 0;
        postorder(list.get(0));
        
        
        return answer;
    }
    
    public void preorder(Node root){        
        // 일단 채워넣기
        answer[0][idx] = root.id;
        idx++; // 채우고 나면 idx올리기
        
        
        // 왼쪽 자식이 있으면 왼쪽 으로 가기
        if(root.left != null) {
            preorder(root.left);
        }
        
        // 오른쪽 자식이 있으면 오른쪽으로 가기
        if(root.right != null){
            preorder(root.right);
        }
    }
    
    public void postorder(Node node){ 
        if(node.left != null){
            postorder(node.left);
        }
        
        if(node.right != null){
            postorder(node.right);
        }
        
        answer[1][idx] = node.id;
        idx++;        
    }
    
    public void makeTree(Node parent, Node child){
        // 일단 left인지 right인지 부터 확인
        // 왼쪽 자식의 경우
        if (parent.x > child.x){
            if(parent.left == null){
                parent.left = child;
            } else {
                makeTree(parent.left, child);
            }
        } else {
            if(parent.right == null){
                parent.right = child;
            } else {
                makeTree(parent.right, child);
            }
        }
    }
}