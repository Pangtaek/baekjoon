import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 전체 날짜 수
        int[] T = new int[N + 2]; // 각 날짜에 걸리는 상담 일수
        int[] P = new int[N + 2]; // 각 날짜에 얻을 수 있는 이익
        int[] dp = new int[N + 2]; // i일까지 얻을 수 있는 최대 이익

        for (int i = 1; i <= N; i++) {
            int[] tokens = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            T[i] = tokens[0];
            P[i] = tokens[1];
        }

        for (int i = 1; i <= N + 1; i++) {
            // 이전 날짜까지의 최대 이익을 반영
            dp[i] = Math.max(dp[i], dp[i - 1]);

            // 상담이 가능한 경우: 종료일이 퇴사일을 넘지 않아야 함
            if (i + T[i] <= N + 1) {
                dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
            }
        }

        bw.write(Integer.toString(dp[N + 1]));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
