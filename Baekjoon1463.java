import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());

        int[] dp = new int[X + 1]; // 1부터 X까지 최소 연산 횟수를 저장하는 배열
        dp[1] = 0; // 1은 연산이 필요 없음

        for (int i = 2; i <= X; i++) {
            dp[i] = dp[i - 1] + 1; // 기본적으로 1을 빼는 연산

            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1); // 2로 나누어지면 최소값 갱신
            }
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1); // 3으로 나누어지면 최소값 갱신
            }
        }

        System.out.println(dp[X]); // X를 1로 만들기 위한 최소 연산 횟수 출력
    }
}
