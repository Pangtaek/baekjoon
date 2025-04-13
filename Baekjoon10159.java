import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon10159 {
    public static int N; // 서로 다른 물건의 개수(1~N)
    public static int M; // 미리 측정된 물건 쌍의 개수
    public static int[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        distance = new int[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
            distance[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            int[] tokens = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

            // obj1.weight > obj2.weight
            int obj1 = tokens[0];
            int obj2 = tokens[1];

            distance[obj2][obj1] = 1;
        }

        for (int k = 1; k <= N; k++)
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= N; j++)
                    if (distance[i][k] + distance[k][j] < distance[i][j])
                        distance[i][j] = distance[i][k] + distance[k][j];

        for (int i = 1; i <= N; i++) {
            int count = 0;
            for (int j = 1; j <= N; j++) {
                if (distance[i][j] == Integer.MAX_VALUE)
                    count++;
            }
            System.out.println(count);
        }
    }
}
