import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon10942 {
    static int[] data;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        data = new int[N + 1];
        int[] input = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        for (int i = 1; i <= N; i++) {
            data[i] = input[i - 1];
        }

        // DP 배열 초기화
        dp = new boolean[N + 1][N + 1];

        // 길이 1
        for (int i = 1; i <= N; i++) {
            dp[i][i] = true;
        }

        // 길이 2
        for (int i = 1; i < N; i++) {
            if (data[i] == data[i + 1]) {
                dp[i][i + 1] = true;
            }
        }

        // 길이 3 이상
        for (int len = 3; len <= N; len++) {
            for (int start = 1; start <= N - len + 1; start++) {
                int end = start + len - 1;
                if (data[start] == data[end] && dp[start + 1][end - 1]) {
                    dp[start][end] = true;
                }
            }
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            int[] tokens = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int S = tokens[0];
            int E = tokens[1];
            sb.append(dp[S][E] ? "1\n" : "0\n");
        }

        System.out.print(sb);
    }
}
