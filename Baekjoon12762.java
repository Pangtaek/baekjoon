import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * 롤러코스터
 * https://www.acmicpc.net/problem/12762
 * 
 * 풀이법: DP
 * 양방향에서 최장 감소 부분 수열(LDS)을 구하여
 * 최대 기둥 개수를 찾음
 * 
 * 시간복잡도: O(N^2)
 */
public class Baekjoon12762 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int N = Integer.parseInt(br.readLine());
            int[] pillars = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int answer = solve(N, pillars);

            bw.write(String.valueOf(answer));
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 롤러코스터 문제 해결
     * 양방향에서 LDS(최장 감소 부분 수열)를 구하여
     * 최대 기둥 개수를 찾음
     */
    private static int solve(int N, int[] pillars) {
        if (N == 1)
            return 1;

        // 왼쪽에서 오른쪽: 각 위치까지의 최장 감소 수열 길이
        int[] leftToRight = new int[N];
        // 오른쪽에서 왼쪽: 각 위치부터의 최장 감소 수열 길이
        int[] rightToLeft = new int[N];

        // 왼쪽→오른쪽 LDS 계산
        for (int i = 0; i < N; i++) {
            leftToRight[i] = 1; // 자기 자신
            for (int j = 0; j < i; j++) {
                // 감소 조건: 이전 값이 더 크면
                if (pillars[j] > pillars[i]) {
                    leftToRight[i] = Math.max(leftToRight[i], leftToRight[j] + 1);
                }
            }
        }

        // 오른쪽→왼쪽 LDS 계산
        for (int i = N - 1; i >= 0; i--) {
            rightToLeft[i] = 1; // 자기 자신
            for (int j = N - 1; j > i; j--) {
                // 감소 조건: 이전 값(오른쪽)이 더 크면
                if (pillars[j] > pillars[i]) {
                    rightToLeft[i] = Math.max(rightToLeft[i], rightToLeft[j] + 1);
                }
            }
        }

        // 각 위치에서 최댓값 계산
        int maxPillars = 0;
        for (int i = 0; i < N; i++) {
            // 왼쪽에서 i까지 + 오른쪽에서 i까지 - 중복(i) 1개
            int total = leftToRight[i] + rightToLeft[i] - 1;
            maxPillars = Math.max(maxPillars, total);

            // 한쪽 방향만 선택하는 경우도 고려
            maxPillars = Math.max(maxPillars, leftToRight[i]);
            maxPillars = Math.max(maxPillars, rightToLeft[i]);
        }

        return maxPillars;
    }
}
