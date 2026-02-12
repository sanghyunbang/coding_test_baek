//  트리의 지름

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static class Node{
        int to;
        int weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static List<List<Node>> adj;
    static int maxDiameter = 0;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 1. 인접 리스트 만들고 초기화 해두기
        adj = new ArrayList<>();
        for (int i = 0; i<= n; i++) {
            adj.add(new ArrayList<>());
        }

        // 간선 정보 입력
        for (int i = 0 ; i < n - 1 ; i++ ) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 양방향으로 주기 (일반화)
            adj.get(parent).add(new Node(child, weight));
            adj.get(child).add(new Node(parent, weight));
        }

        // 2. DFS 시작
        calculateDiameter(1, -1);

        System.out.println(maxDiameter);
    }

    // curr을 꺾이는 지점으로 했을 때, 자식 중 가장 diameter가 큰 경우의 diameter를 뱉어주는 함수
    static int calculateDiameter(int curr, int prev) {
        // 문제 예시 그림은 이진트리로 되어 있으나 사실 이진트리라는 보장 없음
        // 여러 자식 경로들 중에서 두 개를 비교하고 그 중 가장 큰걸 return 해줄 것임
        int firstMax = 0;
        int secondMax = 0;

        // 0-1. 로직 고안 : 내 자식들 살펴보기
        for (Node node : adj.get(curr)) {

            // 1. 넘어갈 조건 선택 : 부모 노드는 건너뜀
            // 일종의 종료 조건임. leaf에 도달하면 prev 밖에 없기 때문
            if (node.to == prev) continue;

            // 해당 노드를 꺾는부분으로 하는 diameter 구하기
            // (중요!) 내 자식 중 하나를 선택 -> 그 자식을 꺾는걸로 했을 때 가장 d + 걔랑 나랑 weight
            int diameter = calculateDiameter(node.to, curr) + node.weight;

            if (diameter > firstMax) {
                secondMax = firstMax;
                firstMax = diameter;
            } else if (diameter > secondMax) {
                secondMax = diameter;
            }
        }

        // 이렇게 하고 나면, 여기선 전역변수 업데이트
        maxDiameter = Math.max(maxDiameter, firstMax + secondMax);

        // 0-2.
        return firstMax;
    }

}