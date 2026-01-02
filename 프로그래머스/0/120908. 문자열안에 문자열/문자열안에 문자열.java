class Solution {
    public int solution(String str1, String str2) {
        
        int n = str2.length();
        
        if(str1.length() < n){
            return 2;
        } else if (str1.length() == n) {
            if(str1.equals(str2)){
                return 1;
            } else {
                return 2;
            }
        }
        
        // 여기선 str1의 길이가 str2보다 긴 경우
        int gap = str1.length() - n;
        
        for(int i = 0; i <= gap; i++){
            if (str1.substring(i,i+n).equals(str2)) {
                return 1;
            }
        }
        
        return 2;

    }
}