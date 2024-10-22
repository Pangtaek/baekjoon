import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon14517 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = s.length();
        int[][] dp = new int[n][n];
        int mod = 10007;

        // 각 문자 하나로 팰린드롬
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1; // 각 문자는 팰린드롬
        }

        // 두 문자 조합
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = 3; // aa 또는 bb의 경우
            } else {
                dp[i][i + 1] = 2; // 서로 다른 경우 (a, b)
            }
        }

        // 세 문자 이상 조합
        for (int len = 2; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = (dp[i + 1][j] + dp[i][j - 1] + 1) % mod; // 팰린드롬 추가
                } else {
                    dp[i][j] = (dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1] + mod) % mod; // 중복 제거
                }
            }
        }

        System.out.println(dp[0][n - 1]);
    }
}
