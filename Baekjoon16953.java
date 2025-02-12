import java.util.*;
import java.io.*;

public class Baekjoon16953{
    public static Queue<Data> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int start = input[0];
        int end = input[1];

        System.out.println(solution(start, end));
    }

    public static int solution(int start, int end) {
        queue.offer(new Data((long) start, end, 1));

        while (!queue.isEmpty()) {
            Data current = queue.poll();

            if (current.data == current.target) {
                return (int) current.count;
            }

            if (current.data * 2 <= end) {
                queue.offer(new Data(current.data * 2, end, current.count + 1));
            }
            if (current.data * 10 + 1 <= end) {
                queue.offer(new Data(current.data * 10 + 1, end, current.count + 1));
            }
        }

        return -1; // 목표 숫자로 도달할 수 없는 경우
    }

    public static class Data {
        long data;
        int target;
        long count;

        public Data(long data, int target, long count) {
            this.data = data;
            this.target = target;
            this.count = count;
        }
    }
}
