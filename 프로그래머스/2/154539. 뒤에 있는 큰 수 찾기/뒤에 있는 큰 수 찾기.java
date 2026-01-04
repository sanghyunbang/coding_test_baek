import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        
        Stack<Integer> stack = new Stack<>();
        int n = numbers.length;
        
        //순회
        for (int i = 0; i < n-1; i++) {
            // 일단 현재 인덱스를 -1 후보자로 집어 넣기
            stack.push(i);
            
            // 오른쪽이랑 비교해보기
            // 오른쪽이 더 크기 않으면 pop하지 말고 그대로
            if(numbers[stack.peek()] >= numbers[i+1]) {
                continue;
            } else {
                // 오른쪽이 더 크면 오큰수
                // stack 순회하면서 해당 인덱스 값 현재 오큰수로 채우기
                while(numbers[stack.peek()] < numbers[i+1]) {
                    numbers[stack.pop()] = numbers[i+1];
                    
                    if (stack.isEmpty()) break;
                }
            }
        }
        
        // 남아있는 stack 순회하면서 해당 인덱스 -1로 채우기
        while(!stack.isEmpty()){
            numbers[stack.pop()] = -1;
        }
        
        numbers[n-1] = -1;
    
        
        return numbers;
    }
}