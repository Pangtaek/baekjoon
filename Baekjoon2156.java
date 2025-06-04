import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] wines = new int[N + 1]; // 1-based indexing
        int[] dp = new int[N + 1]; // 1-based indexing

        for (int i = 1; i <= N; i++) {
            wines[i] = Integer.parseInt(br.readLine());
        }

        if (N >= 1)
            dp[1] = wines[1];
        if (N >= 2)
            dp[2] = wines[1] + wines[2];
        if (N >= 3)
            dp[3] = Math.max(
                    dp[2],
                    Math.max(wines[1] + wines[3], wines[2] + wines[3]));

        for (int i = 4; i <= N; i++) {
            dp[i] = Math.max(
                    dp[i - 1], // i번째 안 마심
                    Math.max(
                            dp[i - 2] + wines[i], // i-1번째를 안 마심
                            dp[i - 3] + wines[i - 1] + wines[i] // i-2번째를 안 마심
                    ));
        }

        bw.write(Integer.toString(dp[N]));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
