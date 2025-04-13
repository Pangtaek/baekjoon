import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon1389 {
    public static final int INF = 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tokens = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int N = tokens[0]; // 유저의 수
        int M = tokens[1]; // 친구 관계 수

        int[][] distance = new int[N + 1][N + 1];

        // 초기화
        for (int i = 1; i <= N; i++) {
            Arrays.fill(distance[i], INF);
            distance[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            tokens = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int a = tokens[0];
            int b = tokens[1];
            distance[a][b] = 1;
            distance[b][a] = 1;
        }

        // 플로이드-워셜 알고리즘
        for (int k = 1; k <= N; k++)
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= N; j++)
                    if (distance[i][j] > distance[i][k] + distance[k][j])
                        distance[i][j] = distance[i][k] + distance[k][j];

        int answer = 0;
        int minSum = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                sum += distance[i][j];
            }

            if (sum < minSum) {
                minSum = sum;
                answer = i;
            }
        }

        System.out.println(answer);
    }
}
