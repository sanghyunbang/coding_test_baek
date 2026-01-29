import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. BufferedReader 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;

        // 2. 읽어온 줄이 null이 아닐 때까지 (EOF까지) 반복
        while((line = br.readLine()) != null) {
            // 3. 읽은 그대로 출력
            System.out.println(line);
        }

        br.close();

    }
}
