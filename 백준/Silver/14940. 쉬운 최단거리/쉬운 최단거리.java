//  쉬운 최단거리

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static int[] dr = new int[]{1,-1, 0, 0};
    public static int[] dc = new int[]{0,0, -1, 1};

    public static class Node{
        int r,c ; // 몇 번째 row에 있는지 // 몇 번째 column에 있는지
        int type; int dist;

        public Node(int r, int c, int type, int dist){
            this.r = r; this.c = c;
            this.type = type; this.dist = dist;
        }

        public Node(int r, int c, int type){
            this.r = r; this.c = c; this.type = type;
        }
    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 세로의 크기 : column 사이즈 -> row의 개수
        int m = Integer.parseInt(st.nextToken()); // 가로의 크기 : row 사이즈 -> column의 개수

        int[][] arr = new int[n][m];

        // 거리 담을 칸. 미리 -1로 점부
        int[][] dists = new int[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                // 일단은 다 -1로 고정
                dists[i][j] = -1;
            }
        }

        // (r,c)
        int[] goal =  new int[2];

        // arr 초기화
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < m; j++){
                int ele = Integer.parseInt(st.nextToken());
                arr[i][j] = ele;

                // 0인 지점은 dists 미리 0으로 맞추기
                if (ele == 0){
                    dists[i][j] = 0;
                }
                // 목표 지점
                if(ele == 2) {
                    dists[i][j] = 0;
                    goal[0] = i;
                    goal[1] = j;
                }
            }
        }

        boolean[][] visited = new boolean[n][m];
        Queue<Node> que = new ArrayDeque<>();

        Node start = new Node(goal[0], goal[1], 2, 0);
        visited[goal[0]][goal[1]] = true;

        que.offer(start);

        while(!que.isEmpty()){

            Node current = que.poll();
            int cr = current.r;
            int cc = current.c;
            int cd = current.dist;

            for(int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                int nd = cd + 1;

                if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc] || arr[nr][nc] == 0) {
                 continue;
                }

                visited[nr][nc] = true;
                dists[nr][nc] = nd;

                Node node = new Node(nr, nc, 1, nd);
                que.offer(node);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++){
                sb.append(dists[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}