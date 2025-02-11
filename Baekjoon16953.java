import java.util.*;
import java.io.*;

public class Baekjoon16953 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.println(solution(input));
    }

    public static int solution(int[] input) {
        int start = input[0];
        int target = input[1];

        Queue<long[]> queue = new LinkedList<>();
        queue.offer(new long[] { start, 1 }); // 시작 숫자, 연산 횟수

        while (!queue.isEmpty()) {
            long[] curr = queue.poll();
            long number = curr[0];
            long count = curr[1];

            // 목표 값에 도달하면 연산 횟수 반환
            if (number == target) {
                return (int) count;
            }

            // 연산 수행: 2배
            if (number * 2 <= target) {
                queue.offer(new long[] { number * 2, count + 1 });
            }

            // 연산 수행: 끝에 1 추가
            if (number * 10 + 1 <= target) {
                queue.offer(new long[] { number * 10 + 1, count + 1 });
            }
        }

        return -1; // 목표 값에 도달할 수 없는 경우
    }
}
