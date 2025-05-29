import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Baekjoon1238 {
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

        int[] tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int N = tokens[0]; // 정점의 개수
        int M = tokens[1]; // 간선의 개수
        int X = tokens[2]; // 모이는 정점
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokens = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];

            graph.get(from).add(new Edge(to, weight));
        }

        int[] reverseDistance = dijkstra(graph, X);
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            int[] distance = dijkstra(graph, i);
            int cost = distance[X] + reverseDistance[i];

            if (cost > max) {
                max = cost;
            }
        }

        bw.write(Integer.toString(max));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }

    static int[] dijkstra(List<List<Edge>> graph, int start) {
        int[] distance = new int[graph.size()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (distance[curr.to] < curr.weight)
                continue;

            for (Edge neighbor : graph.get(curr.to)) {
                int newWeight = curr.weight + neighbor.weight;
                if (newWeight < distance[neighbor.to]) {
                    distance[neighbor.to] = newWeight;
                    pq.offer(new Edge(neighbor.to, newWeight));
                }
            }
        }

        return distance;
    }
}
