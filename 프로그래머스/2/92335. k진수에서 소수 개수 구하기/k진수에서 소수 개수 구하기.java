class Solution {
    public int solution(int n, int k) {
        
        String baseK = Integer.toString(n, k);
        
        // 어떤 경우건 맨 앞이 0인 경우는 없다
        // 0이 연속될 때, P0이 또 다른 소수가 될 경우는 없다(10의 배수므로)
        // 0을 기준을 싹다 구분하면 된다.
        
        String[] parts = baseK.split("0+");
        
        int answer = 0;
        for(String s : parts){
            long v = Long.parseLong(s);
            
            if(isPrime(v)) answer++;
        }
        
        return answer;
    }
    
    public boolean isPrime(long x){
        if(x < 2) return false;
        if(x==2) return true;
        if (x % 2 == 0) return false;
        
        // 짝수는 할 필요 없으니까
        for(long i=3; i*i <= x; i+=2){
            if (x%i == 0) return false;
        }
        return true;
        
    }
    
}