import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon15665 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] data = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        Arrays.sort(data);


        System.out.println(solution(input[0], input[1], data));
    }
    
    public static String solution(int N, int M, int[] data) {

        StringBuilder sb = new StringBuilder();

        dfs(0, M, N, new int[M], data, sb);

        return sb.toString();
    }
    
    public static void dfs(int depth, int M, int N, int[] arr, int[] data, StringBuilder sb) {

        if (depth == M) {
            for (int num : arr) {
                sb.append(num).append(" ");
            }
            sb.append("\n");

            return;
        }

        int prevNum = -1;
        for (int i = 0; i < N; i++) {
            if (data[i] != prevNum) {
                prevNum = data[i];
                arr[depth] = data[i];
                dfs(depth + 1, M, N, arr, data, sb);
            }
        }
    }
}