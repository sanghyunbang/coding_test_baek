//  바이러스

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 노드 수
        int E = Integer.parseInt(br.readLine()); // 엣지 수

        List<List<Integer>> list = new ArrayList<>(); // 0 인덱스는 사용 안함
        // 지금은 ArrayList[N+1]가 null 상태 : 실제 객체 만들어 주기
        for (int i = 0; i < N+1; i++){
            list.add(new ArrayList<>());
        }

        // 연결 된 것끼리 담아둠
        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            // 무방향(양방향) 업데이트
            list.get(l).add(r);
            list.get(r).add(l);
        }

        //  bfs 위한 초기 세팅
        boolean[] visited = new boolean[N+1];
        visited[0] = true;
        visited[1] = true;

        Queue<Integer> que = new ArrayDeque<>();
        que.offer(1);
        int answer = 0;

        // 다 빌 때까지 탐색
        while(!que.isEmpty()){

            int current = que.poll(); // 현재 노드

            // 중요 : 여기서 돌면서 확인
            for(int node : list.get(current)){
                if(visited[node]) continue; // 이미 방문 했으면 안해도 됨
                visited[node] = true; // 해당 노드 방문 표시하고
                que.offer(node); // 넣고
                answer++; // 추가했으면 answer++
            }
        }

        System.out.print(answer);



    }
}