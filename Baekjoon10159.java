import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon10159 {
    public static int N, M;
    public static int[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        distance = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
            distance[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            int[] tokens = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int heavier = tokens[0]; // a > b
            int lighter = tokens[1];
            distance[heavier][lighter] = 1;
        }

        // Floyd-Warshall
        for (int k = 1; k <= N; k++)
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= N; j++)
                    if (distance[i][k] != Integer.MAX_VALUE && distance[k][j] != Integer.MAX_VALUE)
                        distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);

        // 정답 출력
        for (int i = 1; i <= N; i++) {
            int unknown = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j)
                    continue;
                if (distance[i][j] == Integer.MAX_VALUE && distance[j][i] == Integer.MAX_VALUE)
                    unknown++;
            }
            System.out.println(unknown);
        }
    }
}
