import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Baekjoon18352 {
    public static int N, M, K, X;
    public static List<List<Edge>> graph = new ArrayList<>();
    public static int[] distance;

    public static final int INF = Integer.MAX_VALUE;

    public static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = tokens[0];
        M = tokens[1];
        K = tokens[2];
        X = tokens[3];

        distance = new int[N + 1];
        Arrays.fill(distance, INF);
        distance[X] = 0;

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = tokens[0];
            int to = tokens[1];
            graph.get(from).add(new Edge(to, 1)); // 모든 간선 가중치는 1
        }

        List<Integer> result = dijkstra();

        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            Collections.sort(result);
            result.forEach(System.out::println);
        }
    }

    public static List<Integer> dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        pq.offer(new Edge(X, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (distance[curr.to] < curr.weight)
                continue;

            for (Edge next : graph.get(curr.to)) {
                int cost = distance[curr.to] + next.weight;
                if (cost < distance[next.to]) {
                    distance[next.to] = cost;
                    pq.offer(new Edge(next.to, cost));
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (distance[i] == K)
                list.add(i);
        }
        return list;
    }
}
