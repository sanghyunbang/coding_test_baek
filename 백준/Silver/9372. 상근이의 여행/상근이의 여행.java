//  상근이의 여행

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int edge = 0;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스
        int T = Integer.parseInt(br.readLine());

        for ( int i = 0 ;i < T ; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 국가의 수
            int M = Integer.parseInt(st.nextToken()); // 비행기의 종류

            List<List<Integer>> graph = new ArrayList<>(); // 연결 배열

            // 초기화 : N+1 개
            for(int e = 0; e < N + 1; e++){
                graph.add(new ArrayList<>()); // 주의! : set은 이미 있을 때 수정이므로 지금은 못씀
            }

            for (int j = 0; j < M; j++){
                st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                // 양방향으로 채우기
                graph.get(l).add(r);
                graph.get(r).add(l);
            }

            visited = new boolean[N+1];
            visited[1] = true;

            dfs(graph, 1);
            System.out.println(edge);

            // edge 초기화
            edge = 0;
        }
    }

    public static void dfs(List<List<Integer>> list, int node) {

        // 리프 노드면 종료
        // 해당 노드와 연결된 노드 모으기
        List<Integer> linked = list.get(node);

        // 연결 되어 있고 && 방문도 안한 곳
        List<Integer> noVisit = new ArrayList<>();

        for(int i : linked){
            if(visited[i]) continue;
            noVisit.add(i);
        }

        if (noVisit.size()==0){
            // 종료 조건 만족 시, 끝냄
            return;
        }

        for (int i : noVisit){

            // 주의 : noVisit이 방문한 적이 없는걸 보장하지 못함 순환에서
            if(!visited[i]) edge++;

            // 방문 업데이트 하기
            visited[i] = true;

            // 찍어보기
            // System.out.println("whether next node " +i+ " is visited :" + visited[i]);
            // System.out.printf("current node : %d, next node : %d, current edge num : %d \n", node, i ,edge);

            dfs(list, i);
        }
    }
}