import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        boolean[] isComposite = new boolean[end + 1];

        if (end >= 0) isComposite[0] = true;
        if (end >= 1) isComposite[1] = true;

        // sieve 로직
        for (int i = 2; i * i <= end; i++) {
            if (!isComposite[i]) {
                for (int j = i * i; j <= end; j += i) {
                    isComposite[j] = true;
                }
            }
        }

        // 출력 최적화
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (!isComposite[i]) {
                sb.append(i).append("\n");
            }
        }
        System.out.print(sb);
    }
}