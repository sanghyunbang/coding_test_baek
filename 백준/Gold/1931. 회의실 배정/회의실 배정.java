//  회의실 배정

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<List<Integer>> list = new ArrayList<>();

        // 값 초기화
        for(int i = 0; i < N; i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            List<Integer> ele = new ArrayList<>();
            ele.add(l);
            ele.add(r);

            list.add(ele);
        }

        // 정렬하기
        list.sort((a, b) -> {
                if(!a.get(1).equals(b.get(1))) return Integer.compare(a.get(1), b.get(1));
                return Integer.compare(a.get(0), b.get(0));
            }

        );

        // 처음껀 무조건 넣기 -> 겹치는거 넘어가고 -> 안겹치면 업데이트

        int ans = 1;
        int baseTime = list.get(0).get(1); // 가장 min한 end를 가진 값의 end 타임

        for (int i = 1; i < list.size(); i++){

            // base가 되는 시작시간보다 작은  start를 가지고 있으면 넘김
            if (list.get(i).get(0) < baseTime) continue;

            // 그게 아니라면 해당 회의를 새로운 min으로 임명해서 base값 조절하고 ans도 추가
            ans++;
            baseTime = list.get(i).get(1);
        }

        System.out.print(ans);

    }
}