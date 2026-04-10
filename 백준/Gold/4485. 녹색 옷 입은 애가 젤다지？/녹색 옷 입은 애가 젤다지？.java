//  녹색 옷 입은 애가 젤다지?

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static class Point{
        int r, c, w;

        private Point(int r, int c, int w){
            this.r = r;
            this.c = c;
            this.w = w;
        }

        public static Point of(int r, int c, int w){
            return new Point(r, c, w);
        }
    }

    public static int[] dr = new int[]{0,0,-1,1};
    public static int[] dc = new int[]{1,-1,0,0};
    public static final int INF = 100_000_000;
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tt = 0;

        while(true){
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            tt++;

            // 해당 배열 담아 두기
            int[][] arr = new int[N][N];
            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 거리
            int[][] dists = new int[N][N];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    dists[i][j] = INF;
                }
            }
            dists[0][0] = arr[0][0];

            // 다익스트라 적용하기
            PriorityQueue<Point> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.w, s2.w));

            pq.offer(Point.of(0,0,dists[0][0]));

            while(!pq.isEmpty()){
                Point cur = pq.poll();
                int cr = cur.r;
                int cc = cur.c;
                int cw = cur.w;

                // 이미 반영된 dists보다도 cw가 크다면 garbage
                if(dists[cr][cc] < cw) continue;

                // 현재 노드에서 상하좌우 확인해보기
                for(int i = 0; i < 4; i++){
                    int nr = cr + dr[i];
                    int nc = cc + dc[i];

                    // 해당 좌표가 유의미 한지 확인하기
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                    int nw = arr[nr][nc]; // 유의미 하면 nw도 부여

                    if(dists[nr][nc] > dists[cr][cc] + nw){
                        dists[nr][nc] = dists[cr][cc] + nw;
                        pq.offer(Point.of(nr, nc, dists[nr][nc])); // p가 반드시 포함되고 이어서 하므로 통과한 p만 pq로
                    }
                }
            }
            System.out.printf("Problem %d: %d \n", tt, dists[N-1][N-1]);
        }

    }
}