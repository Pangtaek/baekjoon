import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon14501 {

    private static class Work {
        public int task;
        public int pay;

        public Work(int task, int pay) {
            this.task = task;
            this.pay = pay;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 일하는 기간
        Work[] works = new Work[N + 2]; // day == N+1까지 처리 가능
        int[] dp = new int[N + 2]; // dp[i]: i일 기준 최대 수익

        for (int day = 1; day <= N; day++) {
            int[] tokens = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            works[day] = new Work(tokens[0], tokens[1]);
        }

        for (int day = 1; day <= N + 1; day++) {
            dp[day] = Math.max(dp[day], dp[day - 1]); // 이전 날까지 수익 반영

            if (day <= N) {
                int nextDay = day + works[day].task;
                if (nextDay <= N + 1) {
                    dp[nextDay] = Math.max(dp[nextDay], dp[day] + works[day].pay);
                }
            }
        }

        bw.write(Integer.toString(dp[N + 1]));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
