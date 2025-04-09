import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Baekjoon1922 {

    static class Edge implements Comparable<Edge> {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return this.cost - other.cost;
        }
    }

    public static int N, M;
    public static List<List<Edge>> graph = new ArrayList<>();
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];

        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 입력
        for (int i = 0; i < M; i++) {
            String[] tokens = br.readLine().split(" ");
            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            int cost = Integer.parseInt(tokens[2]);

            // 양방향 그래프
            graph.get(from).add(new Edge(to, cost));
            graph.get(to).add(new Edge(from, cost));
        }

        // 프림 알고리즘 실행
        int result = prim();
        System.out.println(result);
    }

    public static int prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int totalCost = 0;
        int count = 0;

        pq.offer(new Edge(1, 0)); // 1번 노드부터 시작

        while (!pq.isEmpty() && count < N) {
            Edge current = pq.poll();

            if (visited[current.to])
                continue;

            visited[current.to] = true;
            totalCost += current.cost;
            count++;

            for (Edge next : graph.get(current.to)) {
                if (!visited[next.to]) {
                    pq.offer(next);
                }
            }
        }

        return totalCost;
    }
}
