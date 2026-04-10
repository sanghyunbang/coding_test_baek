//  다리 만들기 2

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static int[] dr = new int[]{1, -1, 0, 0};
    public static int[] dc = new int[]{0, 0, -1, 1};

    public static class Point {
        int r, c;
        private Point(int r, int c){
            this.r = r;
            this.c = c;
        }

        public static Point of(int r, int c){
            return new Point(r, c);
        }

    }

    public static class Node {

    }
    public static int rNum;
    public static int cNum;

    public static int[] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        rNum = Integer.parseInt(st.nextToken());
        cNum = Integer.parseInt(st.nextToken());

        int[][] arr = new int[rNum][cNum];

        for(int i = 0; i < rNum; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < cNum; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<List<Point>> islands = findIslands(arr);

        // 여기에서 섬별로 상하 좌우 최대한 연결해 가면서 다리 후보들 업데이트하고 엣지 리스트

        // 엣지리스틀 위해 섬 id 붙여주기
        // 0 이면 섬이 아닌것
        int[][] isId = new int[rNum][cNum];

        for(int i = 0 ; i < islands.size(); i++){
            int islandsId = i + 1;
            List<Point> ll = islands.get(i);

            for (Point p : ll){
                isId[p.r][p.c] = islandsId;
            }
        }

        // 최소 스패닝 트리
        List<int[]> edgeList = new ArrayList<>();

        for (int i = 0; i < islands.size(); i++){
            List<Point> ll = islands.get(i);

            // 주어진 p들로 부터 상하 좌우 연결 해보기
            for (Point p : ll){
                int cr = p.r;
                int cc = p.c;
                int fId = isId[cr][cc];


                for (int j = 0; j < 4; j++){
                    int nr = cr;
                    int nc = cc;
                    int dist = 0;

                    while (true){
                        nr += dr[j];
                        nc += dc[j];

                        // 1. 범위 밖이면 즉시 종료
                        if(nr < 0 || nr >= rNum || nc < 0 || nc >= cNum) break;

                        // 2. 내 섬(fId)을 다시 만났다면 다리 건설 불가
                        if (isId[nr][nc] == fId) break;

                        // 3. 바다가 아닌 곳(다른 섬)을 만난 경우
                        if (isId[nr][nc] != 0){
                            if (dist >= 2) {
                                edgeList.add(new int[]{fId, isId[nr][nc], dist});
                            }
                            break; // 섬에 닿았으니 그만
                        }

                        // 4. 여기까지 온 경우엔 현재 칸이 바다이므로 거리 증가
                        dist++;
                    }

                }
            }
        }

        // 이제 인접리스트가 주어졌으니 크루스칼 알고리즘 시작해보기

        int nodeNum = islands.size();
        parent = new int[nodeNum + 1]; // 섬 이름이 1부터 시작이라

        // parent 초기화
        for (int i = 0 ; i < parent.length; i++){
            parent[i] = i;
        }

        // 거리가 가장 짧은 것부터 인접 리스트 배열해놓기
        Collections.sort(edgeList, (s1, s2) -> Integer.compare(s1[2], s2[2]));

        // edgeList에서 차례대로 올라가면서 본다
        int cnt = 0; // cnt가 nodeNum - 1 이면 종료
        int distSum = 0;

        for (int[] ele : edgeList){
            int f = ele[0];
            int t = ele[1];
            int dd = ele[2];

            // 해당 두 지점이 같은 루트인지 확인
            if (find(f) == find(t)) continue; // 같으면 그냥 바로 넘어감

            // 다르면 일단 두 개의 루트를 합치기
            union(f, t);
            cnt++;
            distSum += dd; // 해당 거리만큼 합치기

            if(cnt == nodeNum - 1) break;
        }

        // 여기에서 이제 거리 합 출력
        if (cnt == islands.size() - 1 && islands.size() > 0) {
            System.out.println(distSum);
        } else {
            System.out.println("-1");
        }
    }

    public static int find(int x){
        if(parent[x] == x) return x;

        // 경로 압축
        return  parent[x] = find(parent[x]);
    }

    // union
    public static void union(int a, int b) {
        int aa = find(a);
        int bb = find(b);

        if (aa != bb){
            // a의 부모를 b의 루트로 연결
            parent[aa] = bb;
        }
    }

    public static List<List<Point>> findIslands(int[][] arr){

        List<List<Point>> list = new ArrayList<>();
        boolean[][] visited = new boolean[rNum][cNum];

        for (int i = 0; i < rNum; i++){
            for (int j = 0; j < cNum; j++){
                // 해당 영역이 이미 방문 했거나 0이면 패스
                if (visited[i][j] || arr[i][j] == 0) continue;

                // 해당 섬을 담을 리스트
                List<Point> l = new ArrayList<>();

                // 통과하면 방문하고 q 시작하기
                Queue<Point> q = new ArrayDeque<>();
                visited[i][j] = true;
                Point init = Point.of(i, j);

                // 시작 부분
                q.offer(init);
                l.add(init);

                while(!q.isEmpty()){
                    Point P = q.poll();
                    int cr = P.r;
                    int cc = P.c;

                    for (int k = 0; k < 4; k++){
                        int nr = cr + dr[k];
                        int nc = cc + dc[k];

                        // validation 확인
                        if (nr < 0 || nr >= rNum || nc < 0 || nc >= cNum || visited[nr][nc]){
                            continue;
                        }

                        // 방문 업데이트
                        visited[nr][nc] = true;

                        // 해당 값이 1인지 확인
                        if (arr[nr][nc] != 1) continue;

                        // 해당 값이 1이면 l과 que에 넣기
                        Point newP = Point.of(nr, nc);
                        l.add(newP);
                        q.offer(newP);
                    }
                }
                list.add(l);
            }
        }

        return list;
    }
}