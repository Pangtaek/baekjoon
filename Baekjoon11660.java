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
        int[][] table = new int[N][N];
        int[][] dp = new int[N + 1][N + 1];

        for (int row = 0; row < N; row++) {
            table[row] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        // 초기값 설정: 1행
        for (int i = 1; i <= N; i++) {
            dp[1][i] = dp[1][i - 1] + table[0][i - 1];
        }

        // 초기값 설정: 1열
        for (int row = 2; row <= N; row++) {
            dp[row][1] = dp[row - 1][1] + table[row - 1][0];
        }

        // DP 계산
        for (int row = 2; row <= N; row++) {
            for (int col = 2; col <= N; col++) {
                dp[row][col] = dp[row][col - 1] + dp[row - 1][col];
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

            int result = dp[x2][y2] - dp[x1 - 1][y1 - 1];
            bw.write(Integer.toString(result));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
