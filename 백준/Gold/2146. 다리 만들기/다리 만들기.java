//  다리 만들기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static class Point{
        int r, c, isla, ext;

        // 생성자는 private로 감춰서 팩토리 메서드 사용을 강제
        private Point(int r, int c, int isla, int ext){
            this.r = r; this.c = c; this.isla = isla; this.ext = ext;
        }

        // 가장 일반적인 팩토리 메서드 of
        public static Point of(int r, int c, int isla, int ext){
            return new Point(r, c, isla, ext);
        }

        // 상황별 의미를 부여한 메서드
        // 처음 생성
        public static Point init(int r, int c, int isla){
            return new Point(r, c, isla, 0);
        }

        // 확작 생성
        public static Point expended(int r, int c, int isla, int curr){
            return new Point(r, c, isla, curr + 1); // 확장을 1 증가
        }
    }

    public static final int[] dr = new int[]{1, -1, 0, 0};
    public static final int[] dc = new int[]{0, 0, 1, -1};
    public static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];

        for (int r = 0; r < N; r++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++){
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // List<Point> 형태인 island들이 담긴 리스트
        List<List<Point>> islands = new ArrayList<>();

        // 초기화 하기 -> bfs 써서
        islands = getIslandsList(arr);

        // 여기서 부터 확장해 나가면서 다른 섬에 만나는 경우가 있을 때 까지
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < islands.size(); i++){
            answer = Math.min(answer, findByBfs(arr, islands.get(i)));
        }

        System.out.println(answer);
    }

    public static int findByBfs(int[][] arr, List<Point> island){

        // 확장해 나가다가 다른 섬에 닿으면 바로 종료하고 ext 출력
        boolean[][] visited = new boolean[N][N];
        boolean[][] isBase = new boolean[N][N];


        // 이제 Que만들고 확장
        Queue<Point> que = new ArrayDeque<>();


        // 지금 기준이 되는 섬들은 true로 체크해놓기
        for(Point p : island){
            isBase[p.r][p.c] = true;
            que.offer(p);
            visited[p.r][p.c] = true;
        }

        int minVal = 100_000_000;

        while(!que.isEmpty()){
            Point curPoint = que.poll();
            int cr = curPoint.r;
            int cc = curPoint.c;
            int ce = curPoint.ext;

            // 범위 내에 들어와 있고 방문 안된건지 체크
            for(int i = 0; i < 4; i++){
                 int nr = cr + dr[i];
                 int nc = cc + dc[i];
                 int ne = ce +1;

                 if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || isBase[nr][nc]){
                    continue;
                 }

                 // 여기에서 혹시 해당 값이 다른 섬인지 확인 -> 처음 만나자 마자 바로 출력
                 // 무조건 여기서 종료 될텐데, while문 밖에도 return 을 만들어 놔야 하는데 고민
                 if (arr[nr][nc] == 1){
                    minVal = ce;
                    return minVal;
                 }

                 // 범위 내 && 방문 안했었고 && base도 아닌 경우
                 Point next = Point.of(nr, nc, 0, ne);
                 visited[nr][nc] = true; // 일단 방문 체크
                 que.offer(next); // 해당 값 집어넣기
            }
        }

        return minVal;
    }

    public static List<List<Point>> getIslandsList(int[][] arr){

        // List<Point> 형태인 island들이 담긴 리스트
        List<List<Point>> islands = new ArrayList<>();
        boolean[][] visited = new boolean[N][N]; // 방문 배열

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){

                // 0-1. 방문 한거면 바로 넘기기
                if(visited[i][j]) continue;
                visited[i][j] = true;

                // 0-2. 만약에 섬이 아니면 방문만 하고 바로 넘어가기
                if (arr[i][j] == 0) continue;

                // 자료구조 초기화
                Queue<Point> que = new ArrayDeque<>(); // bfs 순환에 쓸 큐

                // 시작 부분 준비해놓기
                List<Point> list = new ArrayList<>(); // 섬에 해당하면 담을 리스트 (섬 별로 새로 생성)
                Point init = Point.init(i, j, arr[i][j]); // 초기 포인트
                list.add(init); // 리스트에 섬 담기
                que.offer(init); // que에도 넣기

                while(!que.isEmpty()){
                    Point cur = que.poll();
                    int rr = cur.r;
                    int cc = cur.c;
                    int ext = cur.ext;

                    for (int k = 0; k < 4; k++){
                        int nr = rr + dr[k];
                        int nc = cc + dc[k];

                        // cur을 기준으로 상하 좌우 탐색을 하고 1에 해당하는 경우만 q에 담고 나머지는 pass
                        if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;

                        // 방문은 가능 영역이면 무조건 해두고
                        visited[nr][nc] = true;

                        // 해당 영역이 섬이 아니면 패스
                        if (arr[nr][nc] == 0) continue;

                        // 여기까지 오면 섬인 셈
                        // 리스트에 섬 담고 que에도 넣기
                        Point point = Point.of(nr, nc, 1, ext);
                        list.add(point);
                        que.offer(point);
                    }

                }
                islands.add(list);
            }
        }

        return islands;
    }
}