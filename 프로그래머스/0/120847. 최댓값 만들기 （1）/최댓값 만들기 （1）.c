#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// qsort를 위한 비교 함수 정의
int compare(const void *a, const void *b){
    int num1 = *(int *)a;
    int num2 = *(int *)b;
    
    if(num1 < num2) return -1;
    if(num1 > num2) return 1;
    return 0;
}

// numbers_len은 배열 numbers의 길이입니다.
int solution(int numbers[], size_t numbers_len) {
    
    // 2. qsort(배열, 길이, 요소크기, 비교함수) 호출
    qsort(numbers, numbers_len, sizeof(int), compare);
    
    // 3. 정렬 된 상태이므로 맨 뒤의 두 수
    // numbers_len은 size_t 타입이지만 인덱스로 바로 사용이 가능
    int max1 = numbers[numbers_len - 1];
    int max2 = numbers[numbers_len - 2];
    
    return max1 * max2;
}