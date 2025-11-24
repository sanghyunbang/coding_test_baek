class Solution {
    public boolean solution(int x) {
        
        int origin = x;
        
        int h = 0;
        while (x >= 10) {
            int e = x % 10;
            h += e;
            x = x/10;
        }
        
        h += x;
        
        return (origin % h == 0) ? true : false;
    }
}