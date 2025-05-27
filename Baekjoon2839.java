import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon2839 {
    public static void main(String[] args) throws IOException {
        final int INF = 5001;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];

        Arrays.fill(dp, INF); 
        if (N >= 3)
            dp[3] = 1;
        if (N >= 5)
            dp[5] = 1;

        for (int i = 6; i <= N; i++) {
            dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
        }

        bw.write(dp[N] >= INF ? "-1" : Integer.toString(dp[N]));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
