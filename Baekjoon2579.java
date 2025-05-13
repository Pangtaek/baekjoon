import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] stairs = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        if (n >= 1)
            dp[1] = stairs[1];
        if (n >= 2)
            dp[2] = stairs[1] + stairs[2];
        if (n >= 3)
            dp[3] = Math.max(stairs[1] + stairs[3], stairs[2] + stairs[3]);

        for (int i = 4; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + stairs[i - 1]) + stairs[i];
        }

        bw.write(Integer.toString(dp[n]));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
