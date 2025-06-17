import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = tokens[0]; // 동전 개수
        int K = tokens[1]; // 만들 금액
        int[] V = new int[N];

        for (int i = 0; i < N; i++) {
            V[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[K + 1];
        dp[0] = 1; // 0원을 만드는 경우는 아무 동전도 안 쓰는 1가지

        for (int coin : V) {
            for (int i = coin; i <= K; i++) {
                dp[i] += dp[i - coin];
            }
        }

        bw.write(Integer.toString(dp[K]));
        bw.newLine();
        
        bw.flush();
        bw.close();
        br.close();
    }
}
