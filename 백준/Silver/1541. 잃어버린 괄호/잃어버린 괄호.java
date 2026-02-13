//  트리의 독립집합

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 전략 : -()되는 값을 가장 크게 만들기
        // 방법) -와 -사이는 자른다 -> -가 +가 되지 않게 만들기
        // 방법) +는 가능하면 -로 만든다

        //알고리즘 설계
        // 1단계 : -를 만나기 전까지는 +를 하며 -를 찾는다.
        // 2단계 : -가 있으면 괄호를 연다고 상정하고 이후 연산을 추적한다.
        // 3단계 : 다른 -를 만나면 괄호를 닫는다.

        // 입력으로 주어지는 식의 길이는 제한적(<50)

        String input = br.readLine();

        // 1. "-"기준으로 자르기
        String[] arr = input.split("-");

        // 2. 맨 앞은 양수고 나머지는 - 대상
        int n = arr.length; // arr의 길이 구하고 거기서 -1 한개 -될 대상들
        int answer = 0;

        if (n == 1) {
            for(int i = 0; i < n; i++) {

                String[] ele = arr[i].split("\\+");

                // 괄호 안의 값들을 다 -하기
                for(String s : ele) {
                    answer += Integer.parseInt(s);
                }

        }
        } else {
            // 앞부분은 플러스
            String[] pp = arr[0].split("\\+");
            for (String s : pp) {
                answer += Integer.parseInt(s);
            }

            // 마이너스
            for(int i = 1; i < n; i++) {
                String[] ele = arr[i].split("\\+");

                // 괄호 안의 값들을 다 -하기
                for(String s : ele) {
                    answer -= Integer.parseInt(s);
                }
            }
        }

        System.out.println(answer);

    }
}