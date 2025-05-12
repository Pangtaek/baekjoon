import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon11066 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            int[] prefixSum = new int[K + 1];
            int[] chapters = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int i = 0; i < K; i++) {
                prefixSum[i + 1] = prefixSum[i] + chapters[i];
            }

            int[][] dp = new int[K + 1][K + 1];

            for (int len = 2; len <= K; len++) {
                for (int i = 1; i <= K - len + 1; i++) {
                    int j = i + len - 1;
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(
                                dp[i][j],
                                dp[i][k] + dp[k + 1][j] + prefixSum[j] - prefixSum[i - 1]);
                    }
                }
            }

            bw.write(dp[1][K] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
