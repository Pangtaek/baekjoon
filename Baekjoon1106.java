import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon1106 {
    private static final int MAX_COST = 100_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int C = tokens[0]; // 목표 고객 수
        int N = tokens[1]; // 도시 개수

        int[] W = new int[N]; // 비용
        int[] V = new int[N]; // 고객 수

        for (int i = 0; i < N; i++) {
            tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            W[i] = tokens[0];
            V[i] = tokens[1];
        }

        int[] dp = new int[MAX_COST];

        for (int i = 0; i < N; i++) {
            for (int cost = W[i]; cost < MAX_COST; cost++) {
                dp[cost] = Math.max(dp[cost], dp[cost - W[i]] + V[i]);
            }
        }

        // 최소 비용 중 고객 수 ≥ C 인 것 찾기
        int answer = -1;
        for (int cost = 0; cost < MAX_COST; cost++) {
            if (dp[cost] >= C) {
                answer = cost;
                break; // 가장 먼저 나오는 cost가 최소 비용
            }
        }

        bw.write(Integer.toString(answer));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
