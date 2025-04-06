import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Baekjoon1504 {
    public static int N, E;
    public static List<List<Edge>> graph = new ArrayList<>();
    public static final int INF = 200_000_000;

    public static class Edge {
        int to, distance;

        public Edge(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = tokens[0];
        E = tokens[1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];

            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }

        tokens = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int v1 = tokens[0];
        int v2 = tokens[1];

        int[] from1 = dijkstra(1);
        int[] fromV1 = dij`kstra(v1);
        int[] fromV2 = dijkstra(v2);

        int path1 = sumPath(from1[v1], fromV1[v2], fromV2[N]);
        int path2 = sumPath(from1[v2], fromV2[v1], fromV1[N]);

        int answer = Math.min(path1, path2);
        System.out.println(answer >= INF ? -1 : answer);
    }

    public static int sumPath(int... values) {
        int sum = 0;
        for (int v : values) {
            if (v >= INF)
                return INF;
            sum += v;
        }
        return sum;
    }

    public static int[] dijkstra(int start) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.distance));
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (distance[curr.to] < curr.distance)
                continue;

            for (Edge next : graph.get(curr.to)) {
                int cost = distance[curr.to] + next.distance;
                if (cost < distance[next.to]) {
                    distance[next.to] = cost;
                    pq.offer(new Edge(next.to, cost));
                }
            }
        }

        return distance;
    }
}
