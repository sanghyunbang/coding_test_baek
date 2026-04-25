class Solution {
    public String solution(String myString, String pat) {
        int n = myString.length();
        int m = pat.length();
        
        int endId = 0;
        for(int i = n-1; i >= m - 1; i--){
            
            boolean pass = true;
            for (int j = 0; j < m; j++){
                
                if(pat.charAt((m - 1) - j) != myString.charAt(i - j)){
                    pass = false;
                    break;
                }
            }
            
            if (pass) {
                endId = i + 1;
                break;
            }
        }
        
        return myString.substring(0, endId);
    }
}