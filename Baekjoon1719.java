import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon1719 {

    private static final int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int n = tokens[0]; // 집하장의 개수 (200 이하)
            int m = tokens[1]; // 집하장간 경로의 개수 (10,000 이하)

            int[][] table = new int[n][n];
            int[][] path = new int[n][n];

            for (int i = 0; i < n; i++) {
                Arrays.fill(table[i], INF);
                table[i][i] = 0;
                Arrays.fill(path[i], i);
            }

            for (int i = 0; i < m; i++) {
                tokens = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int from = tokens[0] - 1; // 출발지
                int to = tokens[1] - 1; // 도착지
                int cost = tokens[2]; // 비용 (1,000 이하)

                // 양방향 그래프
                table[from][to] = cost;
                table[to][from] = cost;

                path[from][to] = to;
                path[to][from] = from;
            }

            floyd(table, path);

            for (int from = 0; from < n; from++) {
                StringBuilder answer = new StringBuilder();
                for (int to = 0; to < n; to++) {
                    if (from == to)
                        answer.append("- ");
                    else
                        answer.append(path[from][to] + 1 + " "); // +1 추가 필요 (1-based)
                }
                bw.write(answer.toString().trim() + "\n");
            }

            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void floyd(int[][] table, int[][] path) {
        int n = table.length;

        // 경유지
        for (int via = 0; via < n; via++) {
            // 출발지
            for (int from = 0; from < n; from++) {
                // 도착지
                for (int to = 0; to < n; to++) {
                    // 목적지와 도착지가 동일한 경우
                    if (from == to)
                        continue;
                    // 경유지를 갈 수 없는 경우
                    if (table[from][via] == INF || table[via][to] == INF)
                        continue;

                    int newCost = table[from][via] + table[via][to];
                    if (table[from][to] > newCost) {
                        table[from][to] = newCost;
                        path[from][to] = path[from][via];
                    }

                }
            }
        }
    }
}
