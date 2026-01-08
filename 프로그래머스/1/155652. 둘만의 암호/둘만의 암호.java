import java.util.*;

class Solution {
    public String solution(String s, String skip, int index) {
        
        char[] skipArr = skip.toCharArray();
        Set<Character> skipSet = new HashSet<>();
        
        for (char c : skipArr) {
            skipSet.add(c);
        }
        
        // 리스트 만들어 놓기
        // a 부터 시작되고 skip은 제외된 상태로 들어가 있는 arraylist
        List<Character> lst = new ArrayList<>();
        
        for (char c = 'a'; c <= 'z'; c++){
             if (skipSet.contains(c)) continue;
             lst.add(c);
        }
        
        // 여기에 맵 만들기 : 글자로 인덱스 조회 목적
        Map<Character, Integer> map = new HashMap<>();
        for (int i =0; i < lst.size(); i++){
            map.put(lst.get(i), i);
        }
        
        StringBuilder sb = new StringBuilder();
        
        int m = lst.size();     
        // 이전 글자 변환하기
        for (int i = 0 ; i < s.length(); i++){
            char before = s.charAt(i);
            int beforeIdx = map.get(before);
            int afterIdx = (beforeIdx + index >= m) ? (beforeIdx + index) % m : beforeIdx + index;
            sb.append(lst.get(afterIdx));
        }
                 
        
        String answer = sb.toString();
        return answer;
    }
}