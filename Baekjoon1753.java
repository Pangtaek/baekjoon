import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon1753 {
    public static int V, E, K;
    public static List<List<Edge>> graph = new ArrayList<>();
    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        int[] distance = dijkstra();

        for (int i = 1; i <= V; i++) {
            if (distance[i] == INF) {
                System.out.println("INF");
            } else {
                System.out.println(distance[i]);
            }
        }
    }

    public static int[] dijkstra() {
        int[] distance = new int[V + 1];
        Arrays.fill(distance, INF);
        distance[K] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        pq.offer(new Edge(K, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (distance[curr.to] < curr.weight)
                continue;

            for (Edge neighborhood : graph.get(curr.to)) {
                int cost = distance[curr.to] + neighborhood.weight;
                if (cost < distance[neighborhood.to]) {
                    distance[neighborhood.to] = cost;
                    pq.offer(new Edge(neighborhood.to, cost));
                }
            }
        }

        return distance;
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, weight));
        }
    }

    public static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
