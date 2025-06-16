import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon7579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = tokens[0];
        int M = tokens[1];

        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); // 메모리
        int[] C = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); // 비용

        int sumCost = Arrays.stream(C).sum(); // 최대 비용 합
        int[] dp = new int[sumCost + 1]; // dp[cost] = 확보 가능한 최대 메모리

        for (int i = 0; i < N; i++) {
            int mem = A[i];
            int cost = C[i];
            for (int j = sumCost; j >= cost; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost] + mem);
            }
        }

        int result = 0;
        for (int i = 0; i <= sumCost; i++) {
            if (dp[i] >= M) {
                result = i;
                break;
            }
        }

        bw.write(Integer.toString(result));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
