import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        
        String[] lang = new String[]{"cpp", "java", "python", "-"};
        String[] role = new String[]{"backend", "frontend", "-"};
        String[] rank = new String[]{"junior", "senior", "-"};
        String[] food = new String[]{"chicken", "pizza", "-"};
        // 일단 map으로 만들어 놓기
        Map<String, List<Integer>> map = new HashMap<>();
        
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 2; j++){
                for (int k = 0; k <= 2; k++){
                    for (int l = 0; l <= 2; l++){
                        String st = lang[i]+" "+role[j]+" "+rank[k]+" "+food[l];
                        map.put(st, new ArrayList<>());                        
                    }
                }
            }
        }
        
        for (String s : info){
            String[] st = s.split(" ");
            String[] la = new String[]{st[0], "-"};
            String[] ro = new String[]{st[1], "-"};
            String[] ra = new String[]{st[2], "-"};
            String[] fo = new String[]{st[3], "-"};
            int v = Integer.parseInt(st[4]);
            for (int i = 0; i <= 1; i++) {
                for (int j = 0; j <= 1; j++){
                    for (int k = 0; k <= 1; k++){
                        for (int l = 0; l <= 1; l++){
                            String key = la[i]+" "+ro[j]+" "+ra[k]+" "+fo[l];
                            map.get(key).add(v);
                        }
                    }
                }
            }
        }
        
        // 정렬 한번 싹 하기
        for (String key : map.keySet()){
            Collections.sort(map.get(key));
        }
        
        int[] answer = new int[query.length];

        // 이제 query활용해서 이진탐색으로 개수 구하기
        for(int i = 0; i < query.length; i++) {
            String s = query[i].replace(" and "," ");
            String[] st = s.split(" ");
            String key = st[0]+" "+st[1]+" "+st[2]+" "+st[3];
            
            int minBase = Integer.parseInt(st[4]);
            answer[i] = getResult(map.get(key), minBase);
        }
        
        return answer;
    }
    
    public int getResult(List<Integer> list, int base) {
        // 정렬되어 있는 list에서 base 이상인 값이 몇 개인지 구하는 작업
        // [주의] 동일 숫자가 여러개 있을 때 가장 왼쪽 인덱스를 구해야 함
        
        // 1. 리스트가 null이거나 비어있는 경우에 대한 예외 처리 (필수!)
        if (list == null || list.isEmpty()) {
            return 0;
        }
        
        int l = 0;
        int r = list.size() - 1;
        int ans = list.size();
        while(l <= r){
            int mid = (l + r) / 2;
            // 성립 하는 경우
            if (list.get(mid) >= base){
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        
        return list.size() - ans;

    }
    
    
}