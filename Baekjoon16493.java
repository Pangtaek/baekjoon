import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon16493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = tokens[0]; // 남은 일 수
        int M = tokens[1]; // 챕터 수

        int[] T = new int[M]; // 챕터 당 소요 일수
        int[] P = new int[M]; // 챕터 당 페이지 수

        for (int chapter = 0; chapter < M; chapter++) {
            tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            T[chapter] = tokens[0];
            P[chapter] = tokens[1];
        }

        int[] dp = new int[N + 1]; // dp[i]: i일 동안 읽을 수 있는 최대 페이지 수

        for (int chapter = 0; chapter < M; chapter++) {
            for (int day = N; day >= T[chapter]; day--) {
                dp[day] = Math.max(dp[day], dp[day - T[chapter]] + P[chapter]);
            }
        }

        bw.write(Integer.toString(dp[N]));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
