//  토마토

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static class Fruit {
        int r, c;

        Fruit(int r, int c) {
            this.r = r; this.c = c;
        }
    }

    public static int maxDay = 0;

    public static int[] dr = new int[]{1,-1,0,0};
    public static int[] dc = new int[]{0,0,1,-1};

    public static int[][] matures;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        matures = new int[r][c];

        // 일단 -1 불가능 날로 초기화
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                matures[i][j] = -1;
            }
        }

        int[][] arr = new int[r][c];

        // Mature로 시작하는 (1)인 값들이 여러개인데 -> 그것들에가 각각 bfs를 돌리고,
        // 이후에 최솟값 days로 없데이트 하는 식
        List<Fruit> firsts = new ArrayList<>();

        for (int i = 0; i < r; i++){
             st = new StringTokenizer(br.readLine());
             for(int j = 0; j < c; j++){
                int ele = Integer.parseInt(st.nextToken());
                arr[i][j] = ele;

                // -1인 경우도 첫날에 matures로 간주
                if( ele == -1) {
                    matures[i][j] = 0;
                }

                // 1인 경우는 mature이고 동시에 first 갱신
                if(ele == 1) {
                    Fruit fruit = new Fruit(i, j);
                    firsts.add(fruit);

                    matures[i][j] = 0;
                }
             }
        }

        boolean[][] visited = new boolean[r][c];
        Queue<Fruit> que = new ArrayDeque<>();

        for(Fruit first: firsts){
            que.add(first);
        }

        while(!que.isEmpty()){

            Fruit current = que.poll();
            int cr = current.r;
            int cc = current.c;
            int cd = matures[cr][cc];

            for (int i = 0; i < 4; i++){
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if (nr < 0 || nr >= r || nc < 0 || nc >= c || visited[nr][nc] || arr[nr][nc] == -1) continue;

                // 일단 방문하고
                visited[nr][nc] = true;

                // 익은 날짜 업데이트 (중요!)
                if(matures[nr][nc] == -1){
                    matures[nr][nc] = cd + 1;
                } else {
                    matures[nr][nc] = Math.min(matures[nr][nc], cd + 1);
                }

                // que에 넣기
                Fruit nf = new Fruit(nr, nc);
                que.add(nf);
            }
        }

        int minus = 0;
        int maxDay = 0;

        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++){
                int check = matures[i][j];

                if (check == -1) {
                    minus++;
                } else {
                    maxDay = Math.max(maxDay, check);
                }
            }
        }

        if (minus > 0){
            System.out.print(-1);
        } else {
            System.out.print(maxDay);
        }



    }
}