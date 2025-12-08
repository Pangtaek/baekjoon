import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon11404 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(br.readLine()); // 도시의 개수
            int m = Integer.parseInt(br.readLine()); // 버스의 개수

            int[][] distance = new int[n][n];
            for (int city = 0; city < n; city++) {
                Arrays.fill(distance[city], Integer.MAX_VALUE);
                distance[city][city] = 0;
            }

            for (int bus = 0; bus < m; bus++) {
                int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int from = tokens[0] - 1;
                int to = tokens[1] - 1;
                int cost = tokens[2];

                distance[from][to] = Math.min(distance[from][to], cost);
            }

            // 플로이드 와샬
            for (int via = 0; via < n; via++) {
                for (int from = 0; from < n; from++) {
                    for (int to = 0; to < n; to++) {
                        if (from == to)
                            continue;

                        if (distance[from][via] == Integer.MAX_VALUE || distance[via][to] == Integer.MAX_VALUE)
                            continue;

                        distance[from][to] = Math.min(distance[from][to], distance[from][via] + distance[via][to]);
                    }
                }
            }

            StringBuilder answer;
            for (int from = 0; from < n; from++) {
                answer = new StringBuilder();
                for (int to = 0; to < n; to++) {
                    if (distance[from][to] == Integer.MAX_VALUE) // 갈 수 없는 경우
                        answer.append(0).append(" ");
                    else
                        answer.append(distance[from][to]).append(" ");
                }
                bw.write(answer.toString().trim() + "\n");
            }

            // 출력
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}