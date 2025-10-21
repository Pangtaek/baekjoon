import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
 * 백준 10282번: 해킹
 * 다익스트라 알고리즘을 활용한 최단 경로 문제
 * 
 * 풀이:
 * 1. 역방향 그래프를 구성하여 각 컴퓨터가 감염시
 * 킬 수 있는 컴퓨터를 저장
 * 2. 다익스트라 알고리즘을 사용하여 시작 컴퓨터에서
 * 부터 각 컴퓨터까지의 최단 감염 시간을 계산
 * 3. 감염된 컴퓨터의 수와 가장 오래 걸린 감염 시간을
 * 출력
 * 
 * 시간 복잡도: O((N + D) log N) - N은 컴퓨터 수, D는 의존성 수
 * 공간 복잡도: O(N + D) - 그래프 저장을 위한 공간
 */

public class Baekjoon10282 {

    static class Edge {
        int to, distance;

        Edge(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringBuilder answer = new StringBuilder();
            int tc = Integer.parseInt(br.readLine());

            while (tc-- > 0) {
                int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int n = tokens[0];
                int d = tokens[1];
                int c = tokens[2];

                List<List<Edge>> graph = new ArrayList<>(n + 1);
                for (int i = 0; i <= n; i++) {
                    graph.add(new ArrayList<>());
                }

                for (int i = 0; i < d; i++) {
                    tokens = Arrays.stream(br.readLine().split("\\s+"))
                            .mapToInt(Integer::parseInt)
                            .toArray();
                    int a = tokens[0];
                    int b = tokens[1];
                    int time = tokens[2];

                    graph.get(b).add(new Edge(a, time));
                }

                int[] distances = dijkstra(n, c, graph);
                int count = 0;
                int maxTime = 0;

                for (int i = 1; i <= n; i++) {
                    if (distances[i] != Integer.MAX_VALUE) {
                        count++;
                        maxTime = Math.max(maxTime, distances[i]);
                    }
                }

                answer.append(count).append(" ").append(maxTime).append("\n");
            }

            bw.write(answer.toString());
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int[] dijkstra(int n, int c, List<List<Edge>> graph) {
        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[c] = 0;

        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.distance - o2.distance);
        pq.offer(new Edge(c, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (visited[current.to])
                continue;
            visited[current.to] = true;

            for (Edge neighbor : graph.get(current.to)) {
                int newDistance = distances[current.to] + neighbor.distance;
                if (newDistance < distances[neighbor.to]) {
                    distances[neighbor.to] = newDistance;
                    pq.offer(new Edge(neighbor.to, newDistance));
                }
            }
        }

        return distances;
    }
}
