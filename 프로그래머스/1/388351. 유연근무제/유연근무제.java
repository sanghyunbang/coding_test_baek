class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        
        int n = schedules.length;
        
        // 6 : 7로 나누면 나머지 1 
        // 7 : 7로 나누면 나머지 0이면 예외
        
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int day = startday;
            boolean pass = true;
            
            int curHour = schedules[i] / 100; // 900 -> 9 
            int curMin = schedules[i] % 100;
            int limit;
            
            if (curMin + 10 <= 59) {
                limit = schedules[i] + 10;
            } else {
                limit = (curHour + 1) * 100 + (curMin + 10 - 60);
            }
            
            for (int time : timelogs[i]) {
                if (day % 7 == 0 || day % 7 == 6) {
                    // day는 올리고 넘어가야
                    day++;
                    continue;
                }
                if (time > limit) pass = false;
                day++;
            }
            
            if (!pass) continue;
            answer++;
        }
        
        return answer;
    }
}