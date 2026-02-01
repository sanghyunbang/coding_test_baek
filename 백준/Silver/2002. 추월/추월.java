//  추월

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static class Car {
        String id;
        int inOrder;

        Car(String id, int inOrder){
            this.id = id;
            this.inOrder = inOrder;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        // 1. 들어갈 때 기준 순서
        for (int i = 0; i < N; i++) {
            map1.put(br.readLine(), i);
        }

        // 2. 나온 순서들 담아두는 리스트
        List<Car> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String id = br.readLine();
            Car car = new Car(id, map1.get(id));
            list.add(car);
        }

        // 3. 여기에서 list는 순서대로 이전 진입 순서 정보를 가진 상태로 담겨있음
        // 뭔가 오큰수랑 비슷한거 같은데
        int minVal = Integer.MAX_VALUE;

        int answer = 0;

        for(int i = N-1; i >= 0; i--){

            int currentInOrder = list.get(i).inOrder;

            if(currentInOrder > minVal){
                // 내 뒤에 나보다 일찍 들어온 차가 존재하는 경우
                answer++;
            }

            minVal = Math.min(minVal, currentInOrder);
        }

        System.out.print(answer);
    }
}