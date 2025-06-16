import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = tokens[0]; // 물품의 수
        int K = tokens[1]; // 최대 무게
        int[] W = new int[N]; // 무게
        int[] V = new int[N]; // 가치

        for (int i = 0; i < N; i++) {
            tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            W[i] = tokens[0];
            V[i] = tokens[1];
        }

        int[] dp = new int[K + 1]; // dp[w]: w 무게로 얻을 수 있는 최대 가치

        for (int i = 0; i < N; i++) {
            for (int j = K; j >= W[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - W[i]] + V[i]);
            }
        }

        bw.write(Integer.toString(dp[K]));
        bw.newLine();
        
        bw.flush();
        bw.close();
        br.close();
    }
}
