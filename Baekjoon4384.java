import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon4384 {
    public static void main(String[] args) throws IOException {
        // 입력 및 출력 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 사람 수 (짝수)
        int[] W = new int[N]; // 각 사람의 몸무게
        int totalWeight = 0; // 전체 몸무게 합

        // 입력 처리 및 전체 몸무게 계산
        for (int i = 0; i < N; i++) {
            W[i] = Integer.parseInt(br.readLine());
            totalWeight += W[i];
        }

        int half = N / 2; // 그룹당 인원 수
        int maxWeight = totalWeight; // 최대 가능한 몸무게 합

        // dp[i][j] = i명을 골라서 몸무게 j를 만들 수 있는지 여부
        boolean[][] dp = new boolean[half + 1][maxWeight + 1];
        dp[0][0] = true; // 0명으로 몸무게 0 만드는 것은 항상 가능

        // 각 사람을 하나씩 고려하며 dp 테이블 갱신
        for (int w : W) {
            // i명을 선택하는 경우를 역순으로 탐색 (중복 선택 방지)
            for (int i = half; i >= 1; i--) {
                // 몸무게 합도 역순으로 탐색
                for (int j = maxWeight; j >= w; j--) {
                    // 이전에 (i-1)명으로 (j-w)를 만들 수 있었다면,
                    // 현재 w를 더해서 i명으로 j를 만들 수 있음
                    if (dp[i - 1][j - w])
                        dp[i][j] = true;
                }
            }
        }

        // 가능한 몸무게 중에서 두 그룹의 차이가 최소가 되는 경우 탐색
        int minDiff = Integer.MAX_VALUE;
        int bestWeight = 0;

        for (int j = 0; j <= maxWeight; j++) {
            if (dp[half][j]) {
                int diff = Math.abs((totalWeight - j) - j);
                if (diff < minDiff) {
                    minDiff = diff;
                    bestWeight = j;
                }
            }
        }

        int other = totalWeight - bestWeight; // 나머지 그룹의 무게
        int a = Math.min(bestWeight, other);
        int b = Math.max(bestWeight, other);

        // 결과 출력
        bw.write(a + " " + b);
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
