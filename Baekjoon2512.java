import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon2512 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int N = Integer.parseInt(br.readLine()); // 지방의 수
            int[] regions = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int M = Integer.parseInt(br.readLine()); // 총 예산

            if (Arrays.stream(regions).sum() <= M) {
                // 전체 요청 합이 예산 범위 내면 최대 요청 금액 출력
                int max = Arrays.stream(regions).max().orElse(1);
                bw.write(String.valueOf(max) + "\n");
            } else {
                int result = binarySearchCap(regions, M);
                bw.write(String.valueOf(result) + "\n");
            }

            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 이분 탐색으로 상한선(caps)을 찾는 메서드
    private static int binarySearchCap(int[] regions, int M) {
        int left = 0;
        int right = Arrays.stream(regions).max().orElse(1);
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            long total = 0;

            for (int region : regions) {
                total += Math.min(region, mid); // 상한선 적용
            }

            if (total <= M) {
                // 예산을 넘지 않기 때문에 상한선을 올릴 수 있음
                answer = mid;
                left = mid + 1;
            } else {
                // 예산 초과 -> 상한선을 낮춤
                right = mid - 1;
            }
        }
        return answer;
    }
}
