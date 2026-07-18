import java.util.*;

class Solution {
    private static final long INF = 1L << 60;
    private int a , b , n;
    int[] g,s,w,tt;
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        
        this.a = a; this.b = b; this.g = g; this.s = s; this.w = w; this.tt = t;
        
        n = g.length;
        
        long lo = 0;
        long hi = INF;

        // 최솟값 구하기 문제
        long ans = -1;
        
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            if(can(mid)){
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1; 
            }
        }
        
        return ans;
    }
    
    public boolean can(long T){
        
        long gold = 0L;
        long silver = 0L;
        long sum = 0L;
        
        for (int i = 0; i < n; i++){   
            // 1. 해당 T로 왕복 최대 횟수 : k 구하기
            int t = tt[i]; 
            long k = (T/t + 1) / 2;
            
            // 2. cap : 주어진 시간동안 해당 도시(i)가 나를 수 있는 상한
            // (주의 : INF에 가까운 T때문에 오버플로우 발생)
            
            // 최대를 해당 도시에 있는 모든 광물을 다 올기는 경우로 잡아보기
            long available = (long) g[i] + (long)s[i];
            long requiredTrip = available / w[i];
            if (available % w[i] !=0 ) requiredTrip++;
            
            // long cap = k * w[i];에서 w[i]로 오버플로어 나는거 막기
            
            long cap;
            if (k > requiredTrip) {
                cap = requiredTrip * w[i];
            } else {
                cap = k * w[i];
            }
            
            
            // 3. 조건 별 값 업데이트 (g, s, sum)
            // gold
            gold += Math.min(cap, g[i]);
            
            // silver
            silver += Math.min(cap, s[i]);
            
            // sum
            sum += Math.min(cap, g[i] + s[i]);            
        }
        
        if (gold >= a && silver >= b && sum >= (long)a + (long)b) {
            return true;
        } else {
            return false;
        }
        
    }
}