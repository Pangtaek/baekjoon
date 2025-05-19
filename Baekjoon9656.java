import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon9656 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        boolean[] dp = new boolean[N + 1];

        if (N >= 1)
            dp[1] = true;
        if (N >= 2)
            dp[2] = false;
        if (N >= 3)
            dp[3] = true;

        for (int i = 4; i <= N; i++) {
            dp[i] = !dp[i - 3] || !dp[i - 1];
        }

        bw.write(dp[N] ? "CY" : "SK");
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
