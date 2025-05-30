import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int N = tokens[0]; // 표의 크기
        int M = tokens[1]; // 합을 구해야 하는 횟수
        int[][] table = new int[N + 1][N + 1]; // 1-indexed
        int[][] dp = new int[N + 1][N + 1];

        for (int row = 1; row <= N; row++) {
            table[row] = new int[N + 1];
            tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int col = 1; col <= N; col++) {
                table[row][col] = tokens[col - 1];
                dp[row][col] = dp[row - 1][col] + dp[row][col - 1]
                        - dp[row - 1][col - 1] + table[row][col];
            }
        }

        for (int i = 0; i < M; i++) {
            tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int x1 = tokens[0];
            int y1 = tokens[1];
            int x2 = tokens[2];
            int y2 = tokens[3];

            int result = dp[x2][y2] // 전체 영역
                    - dp[x1 - 1][y2] // 위쪽 제외
                    - dp[x2][y1 - 1] // 왼쪽 제외
                    + dp[x1 - 1][y1 - 1]; // 중복 제거 (위+왼쪽 교차 영역) 

            bw.write(Integer.toString(result));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
