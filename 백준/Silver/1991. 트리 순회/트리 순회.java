//  트리 순회

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int[][] tree = new int[26][2]; // tree[parent][0] 왼쪽 자식, 없으면 -1

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int parent = st.nextToken().charAt(0) - 'A';
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            // 자식이 없으면 -1, 있으면 인덱스 (0-25) 저장
            tree[parent][0] = (left == '.') ? -1 : left - 'A';
            tree[parent][1] = (right == '.') ? -1 : right - 'A';
        }

        // 모든 순회는 'A'(인덱스 0에서 시작함)
        preOrder(0);
        System.out.println();
        inOrder(0);
        System.out.println();
        postOrder(0);
    }

    // 전위 순회 (Root -> L -> R)
    static void preOrder(int now) {
        if (now == -1) return;
        System.out.print((char) (now + 'A')); // 'NOW' 먼저
        preOrder(tree[now][0]); // 왼쪽 자식
        preOrder(tree[now][1]); // 오른쪽 자식
    }

    // 중위 순회 (L -> Root -> R)
    static void inOrder(int now) {
        if (now == -1) return;
        inOrder(tree[now][0]); // 왼쪽 자식
        System.out.print((char) (now + 'A')); // 'NOW' 자신 중간
        inOrder(tree[now][1]); // 오른쪽 자식
    }

    // 후위 순회 ( L -> R -> Root)
    static void postOrder(int now) {
        if (now == -1) return;
        postOrder(tree[now][0]); // 왼쪽 자식
        postOrder(tree[now][1]); // 오른쪽 자식
        System.out.print((char) (now + 'A')); // 'NOW' 마지막
    }
}