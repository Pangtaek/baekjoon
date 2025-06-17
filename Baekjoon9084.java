import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon9084 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] coins = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int target = Integer.parseInt(br.readLine());

            int[] dp = new int[target + 1];
            dp[0] = 1; // 0원을 만드는 방법은 아무 동전도 안 쓰는 1가지

            for (int coin : coins) {
                for (int i = coin; i <= target; i++) {
                    dp[i] += dp[i - coin];
                }
            }

            bw.write(Integer.toString(dp[target]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
