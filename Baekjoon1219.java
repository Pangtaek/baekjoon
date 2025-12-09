import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon1219 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int N = tokens[0];
            int START = tokens[1];
            int END = tokens[2];
            int M = tokens[1];

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

            int[] maximumCostsByCity = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            long[] distance = new long[N];
            Arrays.fill(distance, Long.MIN_VALUE);
            distance[START] = maximumCostsByCity[START];

            // 벨만포드 알고리즘
            for (int i = 1; i <= N + N; i++) {
                for (int[] edge : edges) {
                    int from = edge[0];
                    int to = edge[1];
                    int cost = edge[2];

                    // 해당 노드에 도달 불가능한 경우
                    if (distance[from] == Long.MIN_VALUE)
                        continue;

                    // 이미 무한이득인 경우
                    if (distance[from] == Long.MAX_VALUE)
                        distance[to] = Long.MAX_VALUE;
                    // 새로운 최대 이득 경로가 생긴 경우
                    else if (distance[to] < distance[from] + maximumCostsByCity[to] - cost) {
                        // N번 이상 갱신되면 무한 이득
                        if (i >= N)
                            distance[to] = Long.MAX_VALUE;
                        // N번 미만인 경우는 새로운 이득값으로 갱신
                        else
                            distance[to] = distance[from] + maximumCostsByCity[to] - cost;
                    }
                }
            }

            if (distance[END] == Long.MIN_VALUE)
                bw.write("gg\n");
            else if (distance[END] == Long.MAX_VALUE)
                bw.write("Gee\n");
            else
                bw.write(String.valueOf(distance[END]) + "\n");

            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
