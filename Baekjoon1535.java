import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon1535 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] L = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray(); // 체력 소모
        int[] J = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray(); // 기쁨

        int[] dp = new int[101]; // 체력은 0~100

        for (int person = 0; person < N; person++) {
            for (int life = 100; life >= L[person]; life--) {
                dp[life] = Math.max(dp[life], dp[life - L[person]] + J[person]);
            }
        }

        int answer = 0;
        for (int life = 1; life < 100; life++) { // 체력이 0보다 작으면 안 되므로 1~99까지만
            answer = Math.max(answer, dp[life]);
        }

        bw.write(Integer.toString(answer));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
