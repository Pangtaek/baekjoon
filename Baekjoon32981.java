import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon32981 {
    static final int MAX = 10_000_001;
    static final int MOD = 1_000_000_007;
    static int[] dp = new int[MAX];

    public static void main(String[] args) throws IOException {
        init(); // ✅ 딱 한 번만 미리 dp 계산

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int Q = Integer.parseInt(br.readLine());

        while (Q-- > 0) {
            int N = Integer.parseInt(br.readLine());
            bw.write(Integer.toString(dp[N]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void init() {
        dp[1] = 5;
        dp[2] = 20;
        for (int i = 3; i < MAX; i++) {
            dp[i] = (int) ((dp[i - 1] * 5L) % MOD);
        }
    }
}
