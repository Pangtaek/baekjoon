import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon1182 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] arr = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int answer = dfs(0, 0, arr, input[0], input[1]);

        // 공집합 제외 처리 (S가 0일 경우, 아무것도 선택하지 않은 경우 포함됨)
        if (input[1] == 0)
            answer--;

        System.out.println(answer);
    }

    public static int dfs(int index, int sum, int[] arr, int N, int S) {
        if (index == N) {
            return sum == S ? 1 : 0;
        }

        // 현재 원소를 선택한 경우 + 선택하지 않은 경우의 합을 리턴
        return dfs(index + 1, sum + arr[index], arr, N, S)
                + dfs(index + 1, sum, arr, N, S);
    }
}
