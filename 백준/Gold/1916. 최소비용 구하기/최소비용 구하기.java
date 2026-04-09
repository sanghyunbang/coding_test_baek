//  최소비용 구하기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // 1. [인접 리스트] : 리스트 간 정보를 담아두는 칸
        // 1-1. 인접 리스트 초기화 셋팅 하기
        List<List<int[]>> list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        // 1-2. 인접 리스트 채우기
        for (int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int id = Integer.parseInt(st.nextToken());
            int linked = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            int[] ele = new int[]{linked, cost};
            list.get(id).add(ele);
        }

        // 2. [최소거리 배열 & 우선 순위 큐] : 동적으로 거리 업데이트 하는 거 담을 자료구조
        // 2-1. 최소거리 초기화 셋팅
        // 최소 거리가 담길 배열
        int[] dists = new int[N+1];
        Arrays.fill(dists, INF);

        // (추가) : 여기서 시작점, 끝점 추출
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        dists[start] = 0;

        // 2-2. PQ 초기화 셋팅
        // int[] 는 (노드번호, 거리)
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                                (o1, o2) -> Integer.compare(o1[1], o2[1]));

        // 초기에 시작점 넣기
        pq.offer(new int[]{start, 0});


        // 3. [본격적으로 pq를 활용해 최소거리 배열 업데이트]
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curCost = cur[1];

            // 해당 노드를 쓸 수 있는지 없는지 판단 -> 기존 dists와 Cost 비교
            // 현재 코스트가 거리보다 크다면 갱신이 안된 버려진 큐
            if(curCost > dists[curNode]) continue;

            // 여기에서 다시 해당 인접 리스트 활용해서 next 큐 생성 여부 판단
            for(int[] ele : list.get(curNode)){
                int nextNode = ele[0];
                int nextCost = ele[1];

                // next 노드 최소거리 갱신여부 판단
                if (dists[nextNode] > dists[curNode] + nextCost){
                    dists[nextNode] = dists[curNode] + nextCost;
                    pq.offer(new int[]{nextNode, dists[nextNode]});
                }
            }
        }

        // 4. 정답 출력
        System.out.println(dists[end]);
    }
}