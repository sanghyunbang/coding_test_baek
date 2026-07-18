function twoSum(nums: number[], target: number): number[] {
    
    // 1. 첫 번재 메서드 역할(브루트 포스 방식)
    function bruteForce() : number[] {
        for (let i = 0; i < nums.length; i++){
            for (let j = i + 1; j < nums.length; j++){
                if (nums[i] + nums[j] === target) return [i, j];
            }
        }
        return [];
    }

    // 2. 두 번재 메서드 역할 (최적화 - 해시맵 방식)
    function useHashMap() : number[] {
        const map = new Map<number, number>();
        for (let i = 0; i < nums.length; i++){
            // 짝이 a , b (a > b) 가 답일 때, b에서 a를 발견함
            // a에서 b를 발견하지 못함
            const complement = target - nums[i];
            if(map.has(complement)) return [map.get(complement)!, i];
            map.set(nums[i] , i);
        }
        return [];
    }

    // 해시맵으로 풀기로 결정
    return useHashMap();

};