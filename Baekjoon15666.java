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

        dfs(answer, 0, M, N, new int[M], data);

        return answer.toString();
    }

    public static void dfs(StringBuilder answer, int currDepth, int depth, int N, int[] arr, int[] data) {

        if (currDepth == depth) {
            for (int num : arr) {
                answer.append(num).append(" ");
            }
            answer.append("\n");

            return;
        }

        int prevNum = -1;
        for (int i = 0; i < N; i++) {
            if (data[i] != prevNum) {
                prevNum = data[i];
                arr[currDepth] = data[i];
                dfs(answer, currDepth + 1, depth, N, arr, data);
            }
        }
    }
}
