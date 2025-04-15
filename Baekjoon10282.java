import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Baekjoon10282 {

    public static class Edge {
        public int to;
        public int distance;

        public Edge(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            int[] tokens = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int n = tokens[0]; // 컴퓨터 개수
            int d = tokens[1]; // 의존성의 개수
            int c = tokens[2]; // 해킹당한 컴퓨터의 번호

            List<List<Edge>> graph = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < d; i++) {
                tokens = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

                int a = tokens[0];
                int b = tokens[1];
                int time = tokens[2];

                graph.get(b).add(new Edge(a, time));
            }

            int[] distance = dijkstra(n, c, graph);
            int count = 0;
            int sum = 0;
            for (int dist : distance) {
                if (dist != Integer.MAX_VALUE) {
                    count++;
                    sum += dist;
                }
            }

            answer.append(count).append(" ").append(sum).append("\n");
        }

        System.out.println(answer);
    }

    public static int[] dijkstra(int n, int c, List<List<Edge>> graph) {
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[c] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.distance - o2.distance);
        pq.offer(new Edge(c, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (distance[current.to] < current.distance)
                continue;

            for (Edge next : graph.get(current.to)) {
                int newDistance = distance[current.to] + next.distance;
                if (newDistance < distance[next.to]) {
                    distance[next.to] = newDistance;
                    pq.offer(new Edge(next.to, newDistance));
                }
            }
        }

        return distance;
    }
}
