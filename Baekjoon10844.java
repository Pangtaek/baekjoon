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

        for (int len = 2; len <= N; len++) {
            for (int lastNum = 0; lastNum <= 9; lastNum++) {
                long fromLeft = (lastNum > 0) ? dp[len - 1][lastNum - 1] : 0;
                long fromRight = (lastNum < 9) ? dp[len - 1][lastNum + 1] : 0;

                dp[len][lastNum] = (fromLeft + fromRight) % MOD;
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
