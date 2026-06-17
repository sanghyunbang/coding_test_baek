import java.util.*;

class Solution {

    class Node {
        int r;
        int c;
        int v;

        public Node (int r, int c, int v) {
            this.r = r;
            this.c = c;
            this.v = v;
        }    
    }

    public int[] dr = {0,0,1,-1};
    public int[] dc = {1,-1,0,0};
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        int m = image.length; // r
        int n = image[0].length; // c

        Queue<Node> queue = new ArrayDeque<>();
        int baseColor = image[sr][sc];

        if(baseColor == color) {
            return image;
        }

        Node init = new Node(sr, sc, baseColor);
        queue.offer(init);
        boolean[][] visited = new boolean[m][n];
        visited[sr][sc] = true;
        image[sr][sc] = color;

        while(!queue.isEmpty()) {
            Node curNode = queue.poll();
            int cr = curNode.r;
            int cc = curNode.c;
            // Q) 여기서 VISIT 하면 안되나?

            for (int i = 0; i < 4; i++){
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }
                if (visited[nr][nc] || image[nr][nc] != baseColor) {
                    continue;
                }


                // 들어갈 때 visit (Q 위에 뽑아낼 떄 VISIT 하면 안되나?) 
                // 예전에 여기서 안해서 에러 났던거 같은데 기억이 안남..
                visited[nr][nc] = true; 
                image[nr][nc] = color;
                
                Node next = new Node(nr, nc, color);
                queue.offer(next);
            }
        }

        return image; 
    }
}