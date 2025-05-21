import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon10844 {
    public static void main(String[] args) throws IOException {
        final int MOD = 1_000_000_000;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N + 1][10];

        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                long fromLeft = (j > 0) ? dp[i - 1][j - 1] : 0;
                long fromRight = (j < 9) ? dp[i - 1][j + 1] : 0;

                dp[i][j] = (fromLeft + fromRight) % MOD;
            } 
        }        

        long result = 0;
        for (int i = 0; i <= 9; i++) {
            result = (result + dp[N][i]) % MOD;
        }

        bw.write(Long.toString(result));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
