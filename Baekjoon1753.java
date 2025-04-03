import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Baekjoon1753 {
    public static int V, E, K;
    public static List<List<Edge>> graph = new ArrayList<>();

    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        int[] distance = dijkstra();

        System.out.println(distance);
    }

    public static int[] dijkstra() {
        int[] distance = new int[V];
        Arrays.fill(distance, INF);
        distance[K] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        pq.offer(new Edge(K, 0));

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

        return distance;
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tokens = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        V = tokens[0];
        E = tokens[1];
        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            tokens = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int from = tokens[0] - 1;
            int to = tokens[1];
            int weight = tokens[2];

            graph.get(from).add(new Edge(to, weight));
        }
    }

    public static class Edge {
        public int to;
        public int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}