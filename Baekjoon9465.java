import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[][] sticker = new int[2][N];

            for (int row = 0; row < 2; row++) {
                sticker[row] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            int result = solution(N, sticker);
            bw.write(Integer.toString(result));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int solution(int N, int[][] sticker) {
        if (N == 0)
            return 0;
        if (N == 1)
            return Math.max(sticker[0][0], sticker[1][0]);

        int[][] dp = new int[2][N];

        // 초기화
        dp[0][0] = sticker[0][0];
        dp[1][0] = sticker[1][0];
        dp[0][1] = dp[1][0] + sticker[0][1];
        dp[1][1] = dp[0][0] + sticker[1][1];

        for (int i = 2; i < N; i++) {
            dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + sticker[0][i];
            dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + sticker[1][i];
        }

        return Math.max(dp[0][N - 1], dp[1][N - 1]);
    }
}
