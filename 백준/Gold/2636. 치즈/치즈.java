//  치즈

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static int[] dCols = new int[] {1,-1,0,0}; // horizon
    public static int[] dRows = new int[] {0,0,1,-1}; // vertical
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rowNum = Integer.parseInt(st.nextToken());
        int colNum = Integer.parseInt(st.nextToken());

        // [ [row], [row], ... row 개수 만큼] 식으로 배열 생성 : int[row수][column수]

        int[][] arr = new int[rowNum][colNum];

        // 배열 초기화 하기
        for(int i = 0; i < rowNum; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < colNum; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // bfs 탐색 하기
        int time = 0;
        List<Integer> chNum = new ArrayList<>(); // 시간별 치즈 개수
        int cheeseNum = 0;

        while(time == 0 || cheeseNum > 0) {

            cheeseNum = 0; // 매번 다시 cheeseNum을 0으로 초기화
            // bfs 위한 준비
            Queue<String> que = new ArrayDeque<>();
            String current = "0 0";

            boolean[][] visited = new boolean[rowNum][colNum];
            visited[0][0] = true; // 0, 0은 우선 방문

            List<String> chList = new ArrayList<>(); // bfs 한번에 들어가는 chList들

            que.offer(current);

            // 이게 끝나면 bfs 한번이 끝난 것
            while(!que.isEmpty()){

                String[] parts = que.poll().split(" ");
                for(int i = 0; i < 4; i++){
                    int dCIdx = dCols[i];
                    int dRIdx = dRows[i];

                    // 탐색
                    int[] prevCur = new int[2];
                    prevCur[0] = Integer.parseInt(parts[0]);
                    prevCur[1] = Integer.parseInt(parts[1]);

                    int curCIdx = prevCur[0] + dCIdx;
                    int curRIdx = prevCur[1] + dRIdx;

                    // 0. 범위 밖이거나, visited 한 경우에는 넘어가기
                    if(curCIdx < 0 || curCIdx >= colNum || curRIdx < 0 || curRIdx >= rowNum || visited[curRIdx][curCIdx]) {
                        continue;
                    }

                    // 1. 일단 해당 탐색 영역을 visited로 변환
                    visited[curRIdx][curCIdx] = true;

                    // 2. 해당 값이 1인지 확인하기
                    if (arr[curRIdx][curCIdx] == 1) {
                        chList.add(curCIdx + " " + curRIdx); // 1 이면 추가 탐색은 안함 -> 큐에 안들어감
                        cheeseNum++; // 치즈 개수 추가
                    } else {
                        // 0 이면 이후 탐색할 후보로 등록
                        String coord = curCIdx + " " + curRIdx;
                        que.offer(coord);
                    }

                }

            }

            // BFS가 끝난 직후, 녹일 치즈가 있는지 확인
            if (cheeseNum == 0) break;

            // bfs 끝나고 나면 1) arr 및 2) 시간, 3) 시간 별 치즈 개수 등 업데이트하기
            for(String s : chList){
                String[] sParts = s.split(" ");
                int cId = Integer.parseInt(sParts[0]);
                int rId = Integer.parseInt(sParts[1]);
                arr[rId][cId] = 0;
            }

            // 현재 시간 cheese
            chNum.add(cheeseNum);
            time++; // 시간 업데이트
        }

        System.out.println(chNum.size());
        System.out.println(chNum.get(chNum.size()-1));


    }
}