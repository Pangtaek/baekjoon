import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Baekjoon5972 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int N = tokens[0]; // 헛간의 개수
            int M = tokens[1]; // 소들의 길

            List<List<int[]>> graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                tokens = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                int from = tokens[0];
                int to = tokens[1];
                int cost = tokens[2];

                // 양방향 그래프
                graph.get(from).add(new int[] { to, cost });
                graph.get(to).add(new int[] { from, cost });
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
            pq.offer(new int[] { 1, 0 });

            int[] distance = new int[N + 1];
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[1] = 0;

            while (!pq.isEmpty()) {
                int[] current = pq.poll();
                int node = current[0];
                int dist = current[1];

                if (dist > distance[node])
                    continue; // 이미 더 좋은 경로가 있음
                if (node == N)
                    break; // N에 대한 최단거리 확정

                for (int[] neighbor : graph.get(node)) {
                    int next = neighbor[0];
                    int cost = neighbor[1];
                    int newDist = dist + cost;

                    if (newDist < distance[next]) {
                        distance[next] = newDist;
                        pq.offer(new int[] { next, newDist });
                    }
                }
            }

            bw.write(distance[N] + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
