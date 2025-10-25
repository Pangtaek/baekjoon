import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 백준 24337번: 가희와 탑
 * https://www.acmicpc.net/problem/24337
 * 
 * 풀이) 그리디
 * 1. a + b - 1 > N 인 경우, 불가능하므로
 *   -1 출력 후 종료
 * 2. 피크(peak) = max(a, b)
 * 3. 왼쪽 경사면(Left Slope): 1 ~ a
 * 4. 패딩(Padding): N - (a + b - 1) 개수만큼 1로 채움
 * 5. 오른쪽 경사면(Right Slope): b - 1 ~ 1
 * 6. 최종 리스트 조합:
 *  - a = 1: [Peak] + [Padding] + [Right Slope]
 *  - a > 1: [Padding] + [Left Slope] + [Peak] + [Right Slope]
 * 7. 최종 리스트 출력
 */

public class Baekjoon24337 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int N = tokens[0];
            int a = tokens[1];
            int b = tokens[2];

            if (a + b - 1 > N) {
                bw.write("-1\n");
                bw.flush();
                return;
            }

            int peak = Math.max(a, b);

            List<Integer> leftList = new ArrayList<>();
            List<Integer> paddingList = new ArrayList<>();
            List<Integer> rightList = new ArrayList<>();

            // 1. Left Slope
            for (int i = 1; i < a; i++) {
                leftList.add(i);
            }

            // 2. Padding
            int paddingCount = N - (a + b - 1);
            for (int i = 0; i < paddingCount; i++) {
                paddingList.add(1);
            }

            // 3. Right Slope
            for (int i = b - 1; i >= 1; i--) {
                rightList.add(i);
            }

            // 4. 최종 리스트 조합
            List<Integer> resultList = new ArrayList<>();

            // a = 1 일 때는 Peak가 맨 앞에 와야 함
            if (a == 1) {
                resultList.add(peak); // [Peak]
                resultList.addAll(paddingList); // [Padding]
                resultList.addAll(rightList); // [Right]
            }
            // a > 1 일 때는 Padding이 맨 앞에 와야 함
            else {
                resultList.addAll(paddingList); // [Padding]
                resultList.addAll(leftList); // [Left]
                resultList.add(peak); // [Peak]
                resultList.addAll(rightList); // [Right]
            }

            // 5. 출력
            StringBuilder sb = new StringBuilder();
            for (int height : resultList) {
                sb.append(height).append(" ");
            }
            bw.write(sb.toString().trim() + "\n");
            bw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}