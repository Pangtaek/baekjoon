import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon11657 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int N = tokens[0]; // 정점의 개수
            int M = tokens[1]; // 간선의 개수

            int[][] edges = new int[M][3];

            for (int i = 0; i < M; i++) {
                tokens = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int from = tokens[0];
                int to = tokens[1];
                int cost = tokens[2];

                edges[i][0] = from;
                edges[i][1] = to;
                edges[i][2] = cost;
            }

            long[] distance = new long[N + 1];
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[1] = 0;

            for (int i = 1; i <= N; i++) {
                for (int[] edge : edges) {
                    int from = edge[0];
                    int to = edge[1];
                    int cost = edge[2];

                    if (distance[from] == Integer.MAX_VALUE)
                        continue;

                    if (distance[to] > distance[from] + cost) {
                        distance[to] = distance[from] + cost;
                    }
                }
            }

            boolean isFlag = true;

            for (int[] edge : edges) {
                int from = edge[0];
                int to = edge[1];
                int cost = edge[2];

                if (distance[from] == Integer.MAX_VALUE)
                    continue;

                if (distance[to] > distance[from] + cost) {
                    isFlag = false;
                    break;
                }
            }

            if (isFlag) {
                for (int i = 2; i <= N; i++) {
                    bw.write(String.valueOf(distance[i] == Integer.MAX_VALUE ? -1 : distance[i]) + "\n");
                }
            } else {
                bw.write("-1\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}