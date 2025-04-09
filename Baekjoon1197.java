import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Baekjoon1197 {
    public static class Edge {
        public int to;
        public int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tokens = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int V = tokens[0];
        int E = tokens[1];

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            tokens = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int from = tokens[0];
            int to = tokens[1];
            int cost = tokens[2];

            graph.get(from).add(new Edge(to, cost));
            graph.get(to).add(new Edge(from, cost));
        }

        System.out.println(solution(V, E, graph));
    }

    public static int solution(int V, int E, List<List<Edge>> graph) {
        int answer = 0;
        int index = 0;
        boolean[] visited = new boolean[V + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>((edge1, edge2) -> edge1.cost - edge2.cost);
        pq.offer(new Edge(1, 0));

        while (!pq.isEmpty() && index < V) {
            Edge current = pq.poll();

            if (visited[current.to])
                continue;

            visited[current.to] = true;
            answer += current.cost;
            index++;

            for (Edge next : graph.get(current.to)) {
                if (!visited[next.to]) {
                    pq.offer(next);
                }
            }
        }

        return answer;
    }
}
