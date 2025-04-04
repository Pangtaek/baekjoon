import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Baekjoon1916 {
    public static int N, M, start, end;
    public static List<List<Edge>> graph = new ArrayList<>();
    public static int[] distance;

    public static class Edge {
        public int to;
        public int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        int[] tokens;
        for (int i = 0; i < M; i++) {
            tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];

            graph.get(from).add(new Edge(to, weight));
        }

        tokens = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        start = tokens[0];
        end = tokens[1];

        distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        dijkstra();

        System.out.println(distance[end]);
    }

    public static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (distance[curr.to] < curr.weight)
                continue;

            for (Edge edge : graph.get(curr.to)) {
                int cost = distance[curr.to] + edge.weight;

                if (cost < distance[edge.to]) {
                    distance[edge.to] = cost;
                    pq.offer(new Edge(edge.to, cost));
                }
            }
        }
    }
}
