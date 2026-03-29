//  N과 M (9)

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    public static int maxDepth = 0;
    public static int maxIdx = 0;
    public static StringBuilder sb = new StringBuilder();
    public static Map<Integer, Integer> map;
    public static List<Integer> list;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        maxDepth = m;

        map = new TreeMap<>((o1, o2) -> Integer.compare(o1, o2));

        st = new StringTokenizer(br.readLine());
        for(int i=0; i < n; i++){
            int key = Integer.parseInt(st.nextToken());
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        // 크기 순으로 배열된 키 값(중복 제거된 상태)
        list = new ArrayList<>(map.keySet());
        maxIdx = list.size();

        backtracking(0, new StringBuilder());

        System.out.print(sb);
    }

    public static void backtracking(int depth, StringBuilder s){

        // 기저조건(종료 조건)
        if(depth == maxDepth){
            sb.append(s).append("\n");
            return;
        }

        for(int i=0; i < maxIdx; i++){
            // sb 값 바뀜
            StringBuilder tempS = new StringBuilder(s);

            // map 값 바뀜
            int curKey = list.get(i);
            int curVal = map.get(curKey);
            int tempVal = curVal;

            if(curVal > 0) {
                s.append(curKey).append(" ");
                map.put(curKey, curVal - 1);
                backtracking(depth + 1, s);
            }

            s = tempS;
            map.put(curKey, tempVal);
        }
    }
}