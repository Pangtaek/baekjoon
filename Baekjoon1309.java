import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1309 {
    public static void main(String[] args) throws IOException {
        final int MOD = 9901;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N + 1][3];

        dp[1][0] = 1; // 사자 없음
        dp[1][1] = 1; // 왼쪽에 사자
        dp[1][2] = 1; // 오른쪽에 사자
        
        for (int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
        }

        int result = (dp[N][0] + dp[N][1] + dp[N][2]) % MOD;
        bw.write(result + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
