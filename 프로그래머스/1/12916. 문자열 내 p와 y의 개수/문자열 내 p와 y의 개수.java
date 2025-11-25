class Solution {
    boolean solution(String s) {
       char[] arr = s.toCharArray();
        
        int pnum = 0;
        int ynum = 0;
        for (char c : arr){
            if (c == 'p' || c == 'P'){
                pnum ++;
            } else if (c == 'y' || c == 'Y'){
                ynum ++;
            }
        }
        
        return pnum==ynum ? true : false;
    }
    
    
}