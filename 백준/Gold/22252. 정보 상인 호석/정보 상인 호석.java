//  정보 상인 호석

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int Q = Integer.parseInt(br.readLine());

        Map<String, List<Integer>> map = new HashMap<>();

        long sum = 0L;
        for (int i = 0; i < Q; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            // type1 : add information about gorilla.
            if(type == 1){

                int k = Integer.parseInt(st.nextToken());
                List<Integer> nums = new ArrayList<>();

                for(int j = 0; j < k; j++){
                    nums.add(Integer.parseInt(st.nextToken()));
                }

                map.computeIfAbsent(name, key -> new ArrayList<>()).addAll(nums);
            }

            // type2 : Hosuk buys information from gorilla
            else {
                int reqNum = Integer.parseInt(st.nextToken());
                List<Integer> wants = map.get(name);

                // If there's no information, then nothing comes out
                 if(wants == null || wants.isEmpty()){
                     continue;
                 }

                // 1. Separate cases according to reqNum and size of gorilla's information.
                if (wants.size() <= reqNum) {
                    // buy whole information
                    sum += wants.stream().mapToInt(Integer::intValue).sum();
                    wants.clear();
                } else {
                    // buy from the most expensive
                    Collections.sort(wants, Collections.reverseOrder());
                    List<Integer> subList = wants.subList(0, reqNum);
                    sum += subList.stream().mapToInt(Integer::intValue).sum();

                    wants.subList(0, reqNum).clear();
                }
            }
        }

        System.out.println(sum);
    }
}