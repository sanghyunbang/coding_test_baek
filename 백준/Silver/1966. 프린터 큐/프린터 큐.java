//  프린터 큐

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static class Node{
        int order;
        int weight;

        Node(int order, int weight) {
            this.order = order;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i=0; i < N; i++) {

            Deque<Node> q = new ArrayDeque<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>((s1, s2) -> {
                // 내림차순 (weight 큰게 앞에)
                return s2-s1;
            });

            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());

            StringTokenizer stWeight = new StringTokenizer(br.readLine());

            for (int j=0; j<m; j++) {
                int w = Integer.parseInt(stWeight.nextToken());
                Node node = new Node(j, w);
                q.offer(node); // 일반 큐에 노드 집어넣기
                pq.offer(w); // weight 큰거 순으로 넣기 -> 나중에 젤 큰거인지 확인용
            }

            int answer = 0;

            while(!q.isEmpty()){

                // 1. 맨 앞에 있는거 프린트 할 수 있는지 확인
                if(q.peek().weight >= pq.peek()){
                    // 프린트 하기
                    answer++;
                    Node printed = q.poll();
                    pq.poll();

                    if (printed.order == idx) {
                        System.out.println(answer);
                    }
                }

                // 2. 맨 앞에 있는게 프린트 할 수 있는 상황이 아니면 -> 맨 뒤로 보내기
                else {
                    q.offer(q.poll());
                }
            }

        }

    }

}
