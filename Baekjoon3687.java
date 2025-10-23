import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * [백준 3687] 성냥개비
 * https://www.acmicpc.net/problem/3687
 * 
 * - 문제 풀이
 * 1. 최솟값은 DP로 풀이
 *  - 2~7개의 성냥개비로 만들 수 있는 숫자를 미리 저장
 *  - 8개부터는 dp[i-j]에 arr[j-2]를
 * 뒤에 붙이는 방식으로 계산
 * 2. 최댓값은 그리디로 풀이
 *  - 자릿수를 최대화하기 위해 1을 최대한 많이 사용
 *  - 홀수일 경우 맨 앞자리는 7로 시작
 *  - 나머지는 1로 채우기
 */
public class Baekjoon3687 {

    static long[] dp;
    static int[] arr = { 1, 7, 4, 2, 0, 8 }; // 2~7개로 만들 수 있는 숫자

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int T = Integer.parseInt(br.readLine());

            // DP 테이블 미리 생성 (최대 100개까지)
            initDP();

            for (int i = 0; i < T; i++) {
                int n = Integer.parseInt(br.readLine());

                // 최솟값과 최댓값 출력
                bw.write(dp[n] + " " + findMaximum(n));
                bw.newLine();
            }

            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * DP 테이블 초기화 (최솟값 계산)
     */
    private static void initDP() {
        dp = new long[101];
        Arrays.fill(dp, Long.MAX_VALUE);

        // 초기값 설정 (2~8개)
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6; // 0은 맨 앞에 올 수 없으므로 6
        dp[7] = 8;
        dp[8] = 10; // "1" + "0"

        // DP로 9개부터 100개까지 계산
        for (int i = 9; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {
                // dp[i-j]에 arr[j-2]를 뒤에 붙이기
                String temp = String.valueOf(dp[i - j]) + String.valueOf(arr[j - 2]);
                dp[i] = Math.min(Long.parseLong(temp), dp[i]);
            }
        }
    }

    /**
     * Greedy를 이용한 최댓값 계산
     * 자릿수를 최대화 → 1을 최대한 많이 사용
     */
    private static String findMaximum(int n) {
        StringBuilder sb = new StringBuilder();

        // 홀수면 7로 시작 (3개 사용)
        if (n % 2 == 1) {
            sb.append(7);
            n -= 3;
        }

        // 나머지는 1로 채우기 (2개씩 사용)
        while (n > 0) {
            sb.append(1);
            n -= 2;
        }

        return sb.toString();
    }
}
