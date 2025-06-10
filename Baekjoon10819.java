import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon10819 {

    private static int N;
    private static int[] A;
    private static boolean[] visited;
    private static int[] permutation; // 순열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        A = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        visited = new boolean[N];
        permutation = new int[N];

        bw.write(Integer.toString(solution()));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }

    private static int solution() {
        int max = 0;

        max = Math.max(max, dfs(0));

        return max;
    }

    private static int dfs(int depth) {
        if (depth == N) {
            int sum = 0;
            for (int i = 0; i < N - 1; i++) {
                sum += Math.abs(permutation[i] - permutation[i + 1]);
            }
            return sum;
        }

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                permutation[depth] = A[i];
                max = Math.max(max, dfs(depth + 1)); // 모든 분기에서 최댓값 계산
                visited[i] = false;
            }
        }

        return max;
    }    
}
