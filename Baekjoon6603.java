import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Baekjoon6603 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<int[]> data = new ArrayList<>();

        while (true) {
            String input = br.readLine();
            if (input.equals("0")) {
                break;
            } else {
                int[] arr = Arrays.stream(input.split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                data.add(arr);
            }
        }

        System.out.println(solution(data));
    }

    public static String solution(List<int[]> data) {
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < data.size(); i++) {
            int[] numbers = Arrays.copyOfRange(data.get(i), 1, data.get(i).length);
            dfs(0, 6, 0, new int[6], numbers, answer);

            if (i != data.size() - 1) {
                answer.append("\n");
            }
        }

        return answer.toString();
    }

    public static void dfs(
            int curr,
            int depth,
            int start,
            int[] arr,
            int[] data,
            StringBuilder memory) {

        if (curr == depth) {
            for (int num : arr) {
                memory.append(num).append(" ");
            }
            memory.append("\n");
            return;
        }

        for (int i = start; i < data.length; i++) {
            arr[curr] = data[i];
            dfs(curr + 1, depth, i + 1, arr, data, memory);
        }
    }
}
