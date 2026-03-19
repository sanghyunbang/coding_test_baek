//  연결 요소의 개수

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    // UNION-FIND 용
    static int[] roots;

    public static void main(String[] args) throws IOException{
        // BFS와 union 파인드 각각으로 풀어보기

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 두 가지 풀이를 하기 위해서 바로 input을 값으로 바꾸지 않고 미리 저장
        int[][] arr = new int[M][2];
        for(int i = 0; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // 0. 무방향 그래프 List로 연결관계 만들어 놓기
        // 하나의 커다란 리스트
        List<List<Integer>> list = new ArrayList<>();

        // 내부 구현 초기화 해놓기
        for (int i = 0; i <= N; i++){
            // i 인덱스에 해당하는 노드가 연결된 여부를 담을 리스트
            list.add(new ArrayList<>());
        }

        // list에 해당 인덱스에 연결된 값을 양방향으로 담기
        for(int[] edge : arr){
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }


        // 1. BFS로 풀어보기
        int bfsAns = 0;

        boolean[] visited = new boolean[N+1]; // 노드 개수 +1 --> 인덱스랑 노드 맞추기용

        visited[0] = true;

        for(int i = 1; i <= N; i++){

            if(visited[i]) continue; // 만약 방문 한거면 넘기기

            Queue<Integer> que = new ArrayDeque<>();
            que.offer(i); // i부터 시작

            while(!que.isEmpty()){
                int current = que.poll();

                for( int node : list.get(current)){
                    if(visited[node]) continue;
                    visited[node] = true; // 일단 방문
                    que.offer(node);
                }
            }
            // 여기 빠져 나왔으면 일단 한 set이 끝난 셈
            bfsAns++;
        }

        // 2. UNION-FIND로 풀어보기

        // root노드를 담을 값이 필요하므로 일단 node개수 +1만큼의 배열
        roots = new int[N+1];

        // 처음에는 자기 값을 루트 값으로
        for (int i = 0; i <= N; i++){
            roots[i] = i;
        }

        for (int[] ar : arr){
            union(ar[0], ar[1]);
        }

        int unionAns = 0;
        for(int i = 1; i <= N; i++){
            if (roots[i] == i) unionAns++;
        }
        System.out.print((unionAns + bfsAns)/2);

    }

    public static int find(int x){
        if(roots[x] == x) return x;
        return roots[x] = find(roots[x]);
    }

    // union은 애초에 연결 된 걸 전제로 행함
    // 모든 간선을 다 돌아야 함
    public static void union(int a, int b){
        int rootA = find(a); // a의 루트를 찾음
        int rootB = find(b); // b의 루트를 찾음

        if (rootA != rootB) {
            roots[rootB] = rootA; // b의 루트를 a아래에 붙임
        }
    }
}