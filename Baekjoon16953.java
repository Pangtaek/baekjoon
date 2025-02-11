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
        int answer = 1;

        while (target > start) {
            if (target % 10 == 1) {
                target /= 10;
            } else if (target % 2 == 0) {
                target /= 2;
            } else {
                return -1;
            }
            answer++;
        }

        return (target == start) ? answer : -1;
    }
}
