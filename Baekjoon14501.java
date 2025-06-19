import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N + 1]; // 작업량
        int[] P = new int[N + 1]; // 보수

        for (int i = 1; i <= N; i++) {
            int[] tokens = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            T[i] = tokens[0];
            P[i] = tokens[1];
        }

        int[] dp = new int[N + 2]; // N+1일(퇴사일)까지 저장

        for (int day = 1; day <= N; day++) {
            dp[day] = Math.max(dp[day], dp[day - 1]); // 수익 유지

            int endDay = day + T[day];
            if (endDay <= N + 1) {
                dp[endDay] = Math.max(dp[endDay], dp[day] + P[day]);
            }
        }

        int answer = 0;
        for (int i = 1; i <= N + 1; i++) {
            answer = Math.max(answer, dp[i]);
        }

        bw.write(Integer.toString(answer));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
