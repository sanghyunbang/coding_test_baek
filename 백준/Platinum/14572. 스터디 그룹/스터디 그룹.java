//  스터디 그룹

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static class Student {
        int id ,cnt, lv;
        List<Integer> algos = new ArrayList<>();
        Student(){}
        Student(int id, int cnt, int lv){
            this.id = id;
            this.cnt = cnt;
            this.lv = lv;
        }
    }

    public static int[] state;

    public static int N;
    public static int K;
    public static int D;

    // Student 담아놓을 리스트 만들기
    public static List<Student> list = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // Student's 'N'um
        K = Integer.parseInt(st.nextToken()); // Algorithm 'K'nowledge
        D = Integer.parseInt(st.nextToken()); // 'D'etermined constraint

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int knownCount = Integer.parseInt(st.nextToken());
            int lv = Integer.parseInt(st.nextToken());
            Student student = new Student(i, knownCount, lv);

            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < knownCount; j++){
                student.algos.add(Integer.parseInt(st.nextToken()));
            }
            list.add(student);
        }

        // 레벨 수준에 따라서 정렬
        list.sort((s1, s2) -> Integer.compare(s1.lv, s2.lv));

        // 이제 효율성 탐색해 나가기
        int answer = 0;
        state = new int[K+1];
        int l = 0;
        int r = 0;
        statePlus(r);

        while (l <= r && r < N) {
            int minLv = list.get(l).lv;
            int maxLv = list.get(r).lv;

            // 같아도 오른쪽으로 옮김 왜냐하면 오른쪽도 또 같은 lv이면 포함하는 게 유리하니까
            if ( maxLv - minLv <= D) {
                // 통과한 상황이니까 answer값 업데이트 하기
                answer = Math.max(answer, calE(l, r));

                if (r == N-1) break;

                // r 증가하고 state도 반영
                r++;
                statePlus(r);
            } else {
                // 초과해서 요건 못맞추면 state 반영하고, l올려서 줄이기
                stateMinus(l);
                l++;
            }
        }

        System.out.print(answer);
    }

    public static int calE(int l, int r) {
        int studentNum = r - l + 1;
        int totalAlgos = 0;
        int everyOneKnows = 0;
        for (int i = 1; i <= K; i++){
            if(state[i] == 0) continue;

            totalAlgos++;
            if(state[i] == studentNum){
                 everyOneKnows++;
            }
        }

        return (totalAlgos - everyOneKnows) * studentNum;
    }

    public static void statePlus(int r){
        Student ll = list.get(r);

        for(int algo : ll.algos){
            state[algo]++;
        }
    }

    public static void stateMinus(int l){
        Student ll = list.get(l);

        for(int algo : ll.algos){
            state[algo]--;
        }
    }
}