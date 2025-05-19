import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon9657 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        boolean[] dp = new boolean[N + 1]; // true: 상근이 승, false: 창영이 승

        // 초기값 설정
        if (N >= 1)
            dp[1] = true; // 1: 상근 승
        if (N >= 2)
            dp[2] = false; // 2: 창영 승
        if (N >= 3)
            dp[3] = true; // 3: 상근 승
        if (N >= 4)
            dp[4] = true; // 4: 상근 승

        for (int i = 5; i <= N; i++) {
            dp[i] = !dp[i - 1] || !dp[i - 3] || !dp[i - 4];
        }

        bw.write(dp[N] ? "SK" : "CY");
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
