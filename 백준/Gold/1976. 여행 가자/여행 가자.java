//  여행 가자

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N]; // 여기선 인덱스 0부터 허용

        // 초기화 : 자기 자신을 parent로
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        // 기본 union 하기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (st.nextToken().equals("1")) { // String 비교
                    union(i, j);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int check = -1;
        for (int i = 0; i < M; i++) {
            int numPlusOne = Integer.parseInt(st.nextToken());
            int num = numPlusOne - 1;
            if (check == -1) {
                check = find(num);
                continue;
            }
            if( check != find(num)) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    // 합치기 : 동일 루트로 만들어서
    public static void union(int a, int b) {

        int rootOfA = find(a); // a값의 부모 찾기
        int rootOfB = find(b); // b값의 부모 찾기

        if (rootOfA >= rootOfB) {
            parent[rootOfA] = rootOfB;
        } else {
            parent[rootOfB] = rootOfA;
        }
    }

    // x 인덱스의 부모 찾기
    public static int find(int x) {
        // 인덱스와 부모가 같으면 종료 : 자기자신이 부모인셈
        if (parent[x] == x) return x;
        // 재귀 :
        parent[x] = find(parent[x]);
        return parent[x];
    }
}