import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon2294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = tokens[0];
        int K = tokens[1];
        int[] V = new int[N];

        for (int i = 0; i < N; i++) {
            V[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[K + 1];
        Arrays.fill(dp, K + 1); // 불가능한 큰 값으로 초기화
        dp[0] = 0; // 0원을 만드는 데 필요한 동전 개수는 0개

        for (int coin : V) {
            for (int i = coin; i <= K; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        bw.write(Integer.toString((dp[K] == K + 1 ? -1 : dp[K])));
        bw.newLine();
        
        bw.flush();
        bw.close();
        br.close();
    }
}
