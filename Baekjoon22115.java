import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon22115 {
    private static final int INF = 100_001; // 충분히 큰 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] tokens = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]); // 커피의 수
        int K = Integer.parseInt(tokens[1]); // 목표 카페인
        int[] C = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); // 커피 별 카페인

        int[] dp = new int[K + 1]; // dp[i] = i mg의 카페인을 얻기 위해 마신 최소 커피 수
        Arrays.fill(dp, INF);
        dp[0] = 0; // 0mg 카페인을 얻는 데는 커피를 안 마셔도 되므로 0

        for (int coffee = 0; coffee < N; coffee++) {
            for (int coffeine = K; coffeine >= C[coffee]; coffeine--) {
                dp[coffeine] = Math.min(dp[coffeine], dp[coffeine - C[coffee]] + 1);
            }
        }

        int answer = (dp[K] == INF) ? -1 : dp[K];
        bw.write(Integer.toString(answer));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
