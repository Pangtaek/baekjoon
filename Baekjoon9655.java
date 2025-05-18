import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon9655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        boolean[] dp = new boolean[N + 1];

        dp[1] = true;
        dp[2] = false;
        dp[3] = true;

        for (int i = 4; i <= N; i++) {
            dp[i] = !dp[i - 3] || !dp[i - 1];
        }

        bw.write(dp[N] ? "SK" : "CY");
        bw.newLine();

        bw.flush(); 
        bw.close();
        br.close();
    }
}
