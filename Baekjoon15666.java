import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon15666 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] data = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        System.out.println(solution(input[0], input[1], data));
    }

    public static String solution(int N, int M, int[] data) {
        StringBuilder answer = new StringBuilder();
        dfs(answer, 0, M, 0, new int[M], data);
        return answer.toString();
    }

    public static void dfs(StringBuilder answer, int currDepth, int depth, int start, int[] arr, int[] data) {
        if (currDepth == depth) {
            for (int num : arr) {
                answer.append(num).append(" ");
            }
            answer.append("\n");
            return;
        }

        int prevNum = Integer.MIN_VALUE;
        for (int i = start; i < data.length; i++) {
            if (data[i] != prevNum) {
                prevNum = data[i];
                arr[currDepth] = data[i];
                dfs(answer, currDepth + 1, depth, i, arr, data);
            }
        }
    }
}
