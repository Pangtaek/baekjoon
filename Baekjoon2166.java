import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*  
 * 백준 2166번: 다각형의 면적
 * https://www.acmicpc.net/problem/2166
 * 
 * - 풀이법: 기하학, 구현
 * 1. 신발끈 공식을 이용하여 다각형의 면적을
 *    계산한다.
 * 2. 다각형의 각 꼭짓점 좌표를 입력받아
 *   면적을 계산하고 출력한다.
 * - 시간복잡도: O(N)
 * - 공간복잡도: O(N)
 * - 주의사항: 다각형의 면적은 항상 양수이므로 절댓값을 취한다.
 *           결과는 소수점 첫째 자리까지 출력한다.
 * - 참고자료: https://en.wikipedia.org/wiki/Shoelace_formula
 */
public class Baekjoon2166 {

    public static class Position2D {
        private long x;
        private long y;

        public Position2D(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public long getX() {
            return x;
        }

        public long getY() {
            return y;
        }
    }

    // 신발끈 공식으로 다각형 면적 계산
    public static double calculatePolygonArea(Position2D[] positions) {
        int n = positions.length;
        long sum = 0;

        for (int i = 0; i < n; i++) {
            int next = (i + 1) % n; // 마지막 점은 첫 번째 점과 연결
            sum += positions[i].getX() * positions[next].getY();
            sum -= positions[next].getX() * positions[i].getY();
        }

        return Math.abs(sum) / 2.0;
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int N = Integer.parseInt(br.readLine());
            Position2D[] positions = new Position2D[N];

            for (int i = 0; i < N; i++) {
                int[] input = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                positions[i] = new Position2D(input[0], input[1]);
            }

            // 면적 계산 및 출력
            double area = calculatePolygonArea(positions);
            bw.write(String.format("%.1f\n", area));
            bw.flush();

        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}
