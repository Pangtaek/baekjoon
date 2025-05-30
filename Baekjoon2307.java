import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Baekjoon2307 {
    static final int INF = 10_001;

    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int N = tokens[0];
        int M = tokens[1];
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];

            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }

        int[] prev = new int[N + 1];
        int[] distance = dijkstraWithTrace(N, graph, prev);

        int answer = -1;

        if (distance[N] == INF) {
            bw.write(Integer.toString(answer));
            bw.newLine();

            bw.flush();
            bw.close();
            br.close();
            return;
        }

        int node = N;
        while (prev[node] != 0) {
            int u = node;
            int v = prev[node];

            int[] distanceWithPolice = dijkstra(N, graph, u, v);

            if (distanceWithPolice[N] == INF) {
                answer = -1;
                break;
            }

            int delay = distanceWithPolice[N] - distance[N];
            answer = Math.max(answer, delay);

            node = prev[node];
        }

        bw.write(Integer.toString(answer));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }

    static int[] dijkstraWithTrace(int N, List<List<Edge>> graph, int[] prev) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);
        distance[1] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
        pq.offer(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (curr.weight > distance[curr.to])
                continue;

            for (Edge neighbor : graph.get(curr.to)) {
                int newWeight = curr.weight + neighbor.weight;
                if (newWeight < distance[neighbor.to]) {
                    distance[neighbor.to] = newWeight;
                    prev[neighbor.to] = curr.to;
                    pq.offer(new Edge(neighbor.to, newWeight));
                }
            }
        }

        return distance;
    }

    static int[] dijkstra(int N, List<List<Edge>> graph, int guardNode1, int guardNode2) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);
        distance[1] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.weight, e2.weight));
        pq.offer(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (curr.weight > distance[curr.to])
                continue;

            for (Edge neighbor : graph.get(curr.to)) {
                if ((curr.to == guardNode1 && neighbor.to == guardNode2) ||
                        (curr.to == guardNode2 && neighbor.to == guardNode1)) {
                    continue;
                }

                int newDist = curr.weight + neighbor.weight;
                if (newDist < distance[neighbor.to]) {
                    distance[neighbor.to] = newDist;
                    pq.offer(new Edge(neighbor.to, newDist));
                }
            }
        }

        return distance;
    }
}
