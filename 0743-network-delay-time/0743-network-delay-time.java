class Solution {
    // 0x3f3f3f3f는 덧셈 오버플로우가 나지 않으면서 충분히 큰 무한대 값입니다.
    private static final int INF = 0x3f3f3f3f; 

    public int networkDelayTime(int[][] times, int n, int k) {
        int m = times.length;

        // 1. Static Linked List (포인터 배열을 통한 그래프 표현)
        // 간선 정보를 완전히 평탄화하여 1차원 배열로 관리합니다.
        int[] head = new int[n + 1];
        int[] next = new int[m];
        int[] to = new int[m];
        int[] weight = new int[m];
        
        // head 배열을 -1로 고속 초기화
        for (int i = 0; i <= n; i++) {
            head[i] = -1;
        }
        
        // 간선 연결 정보를 인덱스 포인터 체인으로 기록
        for (int i = 0; i < m; i++) {
            int u = times[i][0];
            to[i] = times[i][1];
            weight[i] = times[i][2];
            next[i] = head[u];
            head[u] = i;
        }

        // 2. 최단 거리 배열 초기화
        int[] dists = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dists[i] = INF;
        }
        dists[k] = 0;

        // 3. 커스텀 고속 원형 큐 (Ring Buffer Queue) 구현
        // 자바 라이브러리 큐를 쓰지 않고, 배열과 비트 연산(&)으로만 동작하는 고속 큐입니다.
        // n의 최댓값이 크지 않으므로 크기는 충분히 넉넉한 2의 거듭제곱(256)으로 선언합니다.
        int[] q = new int[256]; 
        int qMask = 255; // % 연산 대신 비트 연산(&)으로 링 버퍼 인덱스를 순환시켜 속도를 극대화합니다.
        int headPtr = 0;
        int tailPtr = 0;

        // 중복 진입 방지 배열
        boolean[] inQ = new boolean[n + 1];

        // 시작 노드 진입
        q[tailPtr] = k;
        tailPtr = (tailPtr + 1) & qMask;
        inQ[k] = true;

        // 4. SPFA(Shortest Path Faster Algorithm) 루프
        while (headPtr != tailPtr) {
            int cur = q[headPtr];
            headPtr = (headPtr + 1) & qMask;
            inQ[cur] = false;

            int d = dists[cur];
            // 포인터 링크를 타고 이웃 노드를 초고속 스캔
            for (int e = head[cur]; e != -1; e = next[e]) {
                int nextId = to[e];
                int nextDist = d + weight[e];

                if (dists[nextId] > nextDist) {
                    dists[nextId] = nextDist;
                    if (!inQ[nextId]) {
                        q[tailPtr] = nextId;
                        tailPtr = (tailPtr + 1) & qMask;
                        inQ[nextId] = true;
                    }
                }
            }
        }

        // 5. 결과 산출
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int d = dists[i];
            if (d == INF) return -1;
            if (d > ans) ans = d;
        }

        return ans;
    }
}