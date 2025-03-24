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

        int answer = solution(input[0], input[1], arr);

        System.out.println(answer);
    }

    public static int solution(int N, int S, int[] arr) {
        int[] memeory = new int[N];

        dfs(0, memeory, arr, N, S, 0);

        return count;
    }

    public static int count = 0;

    public static void dfs(int depth, int[] memeory, int[] arr, int N, int S, int start) {
        if (depth == N) {
            count++;
            return;
        }

        int sum = 0;
        for (int num : memeory) {
            sum += num;

            if (sum > S) {
                return;
            } else if (sum == S) {
                count++;
                return;
            }
        }

        for (int i = start; i < N; i++) {
            memeory[depth] = arr[i];
            dfs(depth + 1, memeory, arr, N, S, i + 1);
            memeory[depth] = 0;
        }
    }
}
