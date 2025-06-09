import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon17070 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] house = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            int[] row = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 1; j <= N; j++) {
                house[i][j] = row[j - 1];
            }
        }

        int[][][] dp = new int[N + 1][N + 1][3];
        dp[1][2][0] = 1; // 시작: (1,1)-(1,2), 가로 상태

        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                if (house[y][x] == 1)
                    continue;

                // 가로 → 가로 or 대각선
                if (x - 1 >= 1) {
                    dp[y][x][0] += dp[y][x - 1][0] + dp[y][x - 1][2];
                }

                // 세로 → 세로 or 대각선
                if (y - 1 >= 1) {
                    dp[y][x][1] += dp[y - 1][x][1] + dp[y - 1][x][2];
                }

                // 대각선 이동 조건 확인
                if (y - 1 >= 1 && x - 1 >= 1) {
                    if (house[y - 1][x] == 0 && house[y][x - 1] == 0) {
                        dp[y][x][2] += dp[y - 1][x - 1][0]
                                + dp[y - 1][x - 1][1]
                                + dp[y - 1][x - 1][2];
                    }
                }
            }
        }

        int result = dp[N][N][0] + dp[N][N][1] + dp[N][N][2];
        bw.write(Integer.toString(result));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
