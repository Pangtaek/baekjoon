import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Baekjoon11779 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(br.readLine()); // 도시의 개수
            int m = Integer.parseInt(br.readLine()); // 버스의 개수

            List<List<int[]>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int from = tokens[0];
                int to = tokens[1];
                int cost = tokens[2];
                graph.get(from).add(new int[] { to, cost });
            }
            int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int start = tokens[0]; // 출발 도시
            int end = tokens[1]; // 도착 도시

            // 다익스트라 핵심 변수들
            long[] distance = new long[n + 1];
            int[] prev = new int[n + 1]; // 이전 도시 저장 (경로 복원용)
            Arrays.fill(distance, Long.MAX_VALUE / 2);
            Arrays.fill(prev, -1);
            distance[start] = 0;

            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
            pq.offer(new int[] { start, 0 }); // {도시, 비용}

            while (!pq.isEmpty()) {
                int[] current = pq.poll();
                int city = current[0];
                long cost = current[1];

                if (cost > distance[city])
                    continue; // 최적화: 이미 더 좋은 경로 발견

                for (int[] next : graph.get(city)) {
                    int nextCity = next[0];
                    long nextCost = cost + next[1];

                    if (nextCost < distance[nextCity]) {
                        distance[nextCity] = nextCost;
                        prev[nextCity] = city; // 경로 복원용
                        pq.offer(new int[] { nextCity, (int) nextCost });
                    }
                }
            }

            // 결과 출력
            bw.write(distance[end] + "\n"); // 최소 비용

            // 경로 복원 
            List<Integer> path = new ArrayList<>();
            int pos = end;
            while (pos != -1) {
                path.add(pos);
                pos = prev[pos];
            }

            bw.write(path.size() + "\n"); // 경로 길이
            for (int i = path.size() - 1; i >= 0; i--) {
                bw.write(path.get(i) + " ");
            }
            bw.write("\n");
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
