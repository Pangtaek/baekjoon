import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon17845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = tokens[0]; // 공부시간
        int K = tokens[1]; // 과목 수
        int[] I = new int[K]; // 중요도
        int[] T = new int[K]; // 공부시간

        for (int i = 0; i < K; i++) {
            tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            I[i] = tokens[0];
            T[i] = tokens[1];
        }

        int[] dp = new int[N + 1];
        
        for (int subject = 0; subject < K; subject++) {
            for (int important = N; important >= T[subject]; important--) {
                dp[important] = Math.max(dp[important], dp[important - T[subject]] + I[subject]);
            }
        }

        bw.write(Integer.toString(dp[N]));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
