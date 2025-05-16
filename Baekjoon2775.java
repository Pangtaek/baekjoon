import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon2775 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine()); // 층
            int N = Integer.parseInt(br.readLine()); // 호

            int[][] dp = new int[K + 1][N + 1];

            // 0층 초기화: i호에는 i명이 산다
            for (int i = 1; i <= N; i++) {
                dp[0][i] = i;
            }

            for (int floor = 1; floor <= K; floor++) {
                for (int room = 1; room <= N; room++) {
                    dp[floor][room] = dp[floor][room - 1] + dp[floor - 1][room];
                }
            }

            bw.write(Integer.toString(dp[K][N]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
