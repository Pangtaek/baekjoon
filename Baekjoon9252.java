import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon9252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input1 = br.readLine();
        String input2 = br.readLine();

        int len1 = input1.length();
        int len2 = input2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        // 1. LCS 길이 계산
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (input1.charAt(i - 1) == input2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 2. LCS 문자열 추적
        StringBuilder lcs = new StringBuilder();
        int i = len1;
        int j = len2;

        while (i > 0 && j > 0) {
            if (input1.charAt(i - 1) == input2.charAt(j - 1)) {
                lcs.append(input1.charAt(i - 1));
                i--;
                j--;
            } else {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }

        // 3. 출력
        bw.write(Integer.toString(dp[len1][len2]));
        bw.newLine();

        if (lcs.length() > 0) {
            bw.write(lcs.reverse().toString());
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
