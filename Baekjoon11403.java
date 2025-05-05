import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon11403 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] graph = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < N; j++) {
                graph[i][j] = tokens[j] == 1 ? true : false;
            }
        }

        for (int mid = 0; mid < N; mid++) {
            for (int from = 0; from < N; from++) {
                for (int to = 0; to < N; to++) {
                    if (graph[from][mid] && graph[mid][to]) {
                        graph[from][to] = true;
                    }
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer.append(graph[i][j] ? 1 : 0).append(" ");
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }
}