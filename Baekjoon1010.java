import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon1010 {
    static int[][] dp = new int[31][31]; // 최대 30C30

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int[] tokens = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int N = tokens[0]; // 서쪽 사이트
            int M = tokens[1]; // 동쪽 사이트

            bw.write(Integer.toString(combine(M, N)) + '\n');
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int combine(int n, int r) {
        if (dp[n][r] > 0)
            return dp[n][r];
        if (n == r || r == 0)
            return dp[n][r] = 1;
        return dp[n][r] = combine(n - 1, r - 1) + combine(n - 1, r);
    }
}
