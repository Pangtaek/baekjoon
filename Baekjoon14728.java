import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon14728 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = tokens[0]; // 시험 단원의 수
        int T = tokens[1]; // 시험까지 공부할 수 있는 총 시간
        int[] K = new int[N]; // 단원 별 예상 공부 시간
        int[] S = new int[N]; // 단원 문제의 배점

        for (int i = 0; i < N; i++) {
            tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            K[i] = tokens[0];
            S[i] = tokens[1];
        }

        int[] dp = new int[T + 1];

        for (int subject = 0; subject < N; subject++) {
            for (int value = T; value >= K[subject]; value--) {
                dp[value] = Math.max(dp[value], dp[value - K[subject]] + S[subject]);
            }
        }
        
        bw.write(Integer.toString(dp[T]));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
