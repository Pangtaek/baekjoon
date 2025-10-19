import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * 백준 17404번: RGB거리 2
 * 문제 분류:다이나믹 프로그래밍
 * 풀이:https://www.acmicpc.net/problem/17404
 *
 * 첫 번째 집의 색깔을 고정하고, 마지막 집의 색깔이 첫 번째 집과 다르도록
 * 다이나믹 프로그래밍을 수행하여 최솟값을 구하는 문제
 */

public class Baekjoon17404 {

    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;
    private static final int INF = 1000001; // 충분히 큰 값 (최대 1000 * 1000 + 1)

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int N = Integer.parseInt(br.readLine());
            int[][] cost = new int[N][3];

            // 입력 받기
            for (int i = 0; i < N; i++) {
                int[] inputs = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                cost[i][RED] = inputs[0];
                cost[i][GREEN] = inputs[1];
                cost[i][BLUE] = inputs[2];
            }

            int answer = INF;

            // 첫 번째 집을 각 색깔로 고정하여 3번 실행
            for (int firstColor = 0; firstColor < 3; firstColor++) {
                int result = solution(cost, N, firstColor);
                answer = Math.min(answer, result);
            }

            bw.write(String.valueOf(answer));
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution(int[][] cost, int N, int firstColor) {
        int[][] dp = new int[N][3];

        // 첫 번째 집 초기화: 지정된 색깔만 선택 가능, 나머지는 불가능
        for (int color = 0; color < 3; color++) {
            if (color == firstColor) {
                dp[0][color] = cost[0][color];
            } else {
                dp[0][color] = INF; // 선택할 수 없게 큰 값으로 설정
            }
        }

        // 2번째 집부터 N-1번째 집까지 일반적인 DP
        for (int house = 1; house < N; house++) {
            dp[house][RED] = Math.min(dp[house - 1][GREEN], dp[house - 1][BLUE]) + cost[house][RED];
            dp[house][GREEN] = Math.min(dp[house - 1][RED], dp[house - 1][BLUE]) + cost[house][GREEN];
            dp[house][BLUE] = Math.min(dp[house - 1][RED], dp[house - 1][GREEN]) + cost[house][BLUE];
        }

        // 마지막 집은 첫 번째 집과 다른 색깔만 선택 가능
        int result = INF;
        for (int lastColor = 0; lastColor < 3; lastColor++) {
            if (lastColor != firstColor) { // 첫 번째 집과 다른 색깔만
                result = Math.min(result, dp[N - 1][lastColor]);
            }
        }

        return result;
    }
}
