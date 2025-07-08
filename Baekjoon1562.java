import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1562 {

    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[][][] dp = new int[N + 1][10][1 << 10]; // dp[자리 수][현재 숫자][비트마스크]

        // 초기값 설정: 첫 자리에 1~9만 가능
        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int len = 2; len <= N; len++) {
            for (int digit = 0; digit <= 9; digit++) {
                for (int mask = 0; mask < (1 << 10); mask++) {
                    int newMask = mask | (1 << digit);

                    if (digit > 0) {
                        dp[len][digit][newMask] = (dp[len][digit][newMask] + dp[len - 1][digit - 1][mask]) % MOD;
                    }
                    if (digit < 9) {
                        dp[len][digit][newMask] = (dp[len][digit][newMask] + dp[len - 1][digit + 1][mask]) % MOD;
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i <= 9; i++) {
            answer = (answer + dp[N][i][(1 << 10) - 1]) % MOD;
        }

        bw.write(Integer.toString(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
