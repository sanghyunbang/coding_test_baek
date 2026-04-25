import java.util.*;

class Solution {
    public class Node{
        int id;
        int x, y;
        Node left, right;
        
        Node(int id, int x, int y){
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }
    
    public List<Node> list;
    public int[][] answer;
    public int idx;
    
    public int[][] solution(int[][] nodeinfo) {
        
        list = new ArrayList<>();
        
        // 0. 일단 집어 넣기
        for(int i = 0; i < nodeinfo.length; i++){
            Node node = new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]);
            list.add(node);
        }
        
        // 1. 정렬하기 : y축 기준으로 내림차순인데 만약에 y가 같으면 x 기준 오름차순
        list.sort((s1, s2) -> {
                
                if (s1.y == s2.y) {
                    return Integer.compare(s1.x, s2.x); // y같으면 x는 오름차순
                }
                return Integer.compare(s2.y, s1.y);
            }
        );
        
        // 2. 이제 여기에서 트리 만들기 : 각각의 Node에 left, right 넣어주는 것
        Node root = list.get(0); // y가 가장 큰 게 지금 가장 앞에 나와 있는 상황
        
        // 하나하나 누구의 자식인지 채워 넣는 방식으로 가야 함
        for (int i = 1; i < list.size(); i++){
            insertNode(root, list.get(i));
        }
        
        // 이제 여기에서 부터 전위/후위 순회 하기
        answer = new int[2][nodeinfo.length];
        
        // 전위 순회
        idx = 0;
        preorder(root);
        
        // 후위 순회
        idx = 0;
        postorder(root);
        
        // 이제 프린트
        return answer;
        
    }
    
    public void preorder(Node root){
        // 일단 넣고 시작
        answer[0][idx] = root.id;
        idx++; // 인덱스 이제 업로드
        // 이제 왼쪽
        if(root.left != null){
            preorder(root.left);
        }
        // 이제 오른쪽
        if(root.right != null){
            preorder(root.right);
        }
        // 만약에 자식이 null인 상황이면 자기만 넣고 끝나버림
    }
    
    public void postorder(Node root){
        
        // 왼쪽부터 살펴보고, 왼쪽이 없는 경우에만 업데이트
        if(root.left != null){
            postorder(root.left);
        } 
        
        // 이제 오른쪽
        if(root.right != null){
            postorder(root.right);
        }
        
        // 위에서 왼쪽끝 오른쪽 끝까지 쭉 간다음에 이제 해당 값 넣기
        // 이러면 위에 의해서 맨 왼쪽, 오른쪽 다 거치고 나서야 내가 들어감
        answer[1][idx] = root.id;
        idx++; // 인덱스 이제 업로드
    }
    
    public void insertNode(Node parent, Node child){
        // 왼/오 여부 부터
        if(child.x < parent.x){
            // 여기에서 왼쪽 자식이 없으면 넣고 있으면 그 아래 자식한테로 보내기
            if (parent.left == null){
                parent.left = child;
            } else {
                insertNode(parent.left, child);
            }
        } else {
            // 여기에서 오른쪽 자식이 없으면 넣고 있으면 그 아래 자식한테로 보내기
            if (parent.right == null){
                parent.right = child;
            } else {
                insertNode(parent.right, child);
            }
        }
    }
    
    
}