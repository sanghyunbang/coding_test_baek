//  치킨 배달

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int[] dr = new int[]{-1,1,0,0};
    static int[] dc = new int[]{0,0,-1,1};
    static int[][] arr;
    static int[][][] houseRefs; // 집 객체의 주소를 담을 공간 : 각 칸에 int[](배열 주소)가 들어감

    static List<int[]> houses = new ArrayList<>();
    static List<int[]> chickens = new ArrayList<>();

    static class Store {
        Map<int[], Integer> costs = new HashMap<>();
        int r;
        int c;

        Store(int r, int c){
            this.r = r;
            this.c = c;
        }

        Store(Map<int[], Integer> costs, int r, int c){
            this.costs = costs;
            this.r = r;
            this.c = c;
        }
    }

    static List<Store> list = new ArrayList<>();

    // 여기선 백트랙킹 관련
    static int maxDepth;
    static boolean[] isVisited;

    // 정답 관련
    static Map<int[], Integer> minDists = new HashMap<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        maxDepth = M; // 최대 깊이는 치킨집 개수

        arr = new int[N][N]; // 좌표 정리하는 곳
        houseRefs = new int[N][N][];

        // arr & houses & chickens 전처리
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for( int j = 0; j < N; j++){
                int e = Integer.parseInt(st.nextToken());
                // 1. arr에 채우기
                arr[i][j] = e;

                // 2. 만약 1이거나 2면 해당 좌표 채우기
                if(e==1){
                    int[] coord = new int[]{i,j};
                    houses.add(coord);
                    houseRefs[i][j] = coord; //  생성된 객체의 주소를 배열에 기록!
                } else if(e==2){
                    int[] coord = new int[]{i,j};
                    chickens.add(coord);
                }
            }
        }

        // 치킨집 기준으로 거리 배열
        // c1 = [a1, a2, a3, ..., ah], c2 = [a1, a2, a3,..., ah] 이런식
        // 인덱스는 houses와 chickens 인덱스 기준

        for(int[] coord : chickens) {

            // 기준이 되는 치킨 집
            Store store = new Store(coord[0], coord[1]);

            // bfs 완전 탐색을 통해서 int[] 좌표 집과의 거리 저장
            // 해당 List는 각각 r, c, 지금 store로 부터 거리 정보
            Queue<List<Integer>> que = new ArrayDeque<>();

            // 처음 시작 점 : 해당 r, c와 거리 0
            List<Integer> li = new ArrayList<>();
            li.add(store.r); li.add(store.c); li.add(0);
            que.offer(li);

            boolean[][] visited = new boolean[N][N];

            while(!que.isEmpty()) {
                // 현재 좌표
                List<Integer> current = que.poll();
                int curR = current.get(0);
                int curC = current.get(1);
                int curD = current.get(2);

                for (int i = 0; i < 4; i++){
                    int rr = curR + dr[i];
                    int cc = curC + dc[i];
                    int dd = curD + 1;

                    // 예외 상황
                    if(rr<0 || rr>=N || cc<0 || cc >=N || visited[rr][cc]) continue;

                    // 예외가 아니면 방문
                    visited[rr][cc] = true;

                    // que에 집어 넣기
                    List<Integer> next = new ArrayList<>();
                    next.add(rr); next.add(cc); next.add(dd);
                    que.offer(next);

                    // 만약에 1이면 list 업데이트
                    if(arr[rr][cc] == 1) {
                        Map<int[], Integer> map = store.costs;
                        int[] co = houseRefs[rr][cc];
                        map.put(co, dd); // co에 해당하는 1(집)과의 거리
                    }
                }
            }
            // 전체 다 탐색하고 나면 list에 집어 넣기
            list.add(store);
        }

        // 이제 여기에서 M에 따라서
        // minDists 초기화
        for(int[] house : houses){
            minDists.put(house, Integer.MAX_VALUE);
        }
        isVisited = new boolean[list.size()]; // 백트래킹 관련 방문

        backtracking(0,0);
        System.out.println(answer);
    }

    // 백트랙킹
    public static void backtracking(int depth, int start){

        if(depth == maxDepth){
            // 기저조건
            int sum = 0;
            for(int[] x : houses){

                sum += minDists.get(x);
            }
            answer = Math.min(sum, answer);
            return;
        }

        for(int i = start; i < list.size(); i++){
            // 방문 여부 확인
            if (isVisited[i]) continue;

            // 방문 하기
            isVisited[i] = true;

            // 해당 Store로 minDists 업데이트 하기
            Store s = list.get(i);
            Map<int[], Integer> clone = new HashMap<>(minDists);
            minDistUpdate(s.costs);

            // 백트레킹
            backtracking(depth+1, i+1);

            // minDist 및 방문 되돌리기
            minDists = clone;
            isVisited[i] = false;

        }
    }

    public static void minDistUpdate(Map<int[], Integer> costMap){

        for (int[] key : houses) {
            if(costMap.get(key) < minDists.get(key)){
                minDists.put(key, costMap.get(key));
            }
        }
    }

}

