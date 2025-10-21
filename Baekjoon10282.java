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
 * 1. 각 컴퓨터를 노드로, 의존성을 간선으로 하는
 * 그래프를 생성
 * 2. 다익스트라 알고리즘을 사용하여 해킹당한 컴퓨터
 * 로부터 다른 컴퓨터들까지의 최단 시간을 계산
 * 3. 최단 시간 배열을 순회하며 감염된 컴퓨터의 수
 * 와 가장 늦게 감염된 컴퓨터의 시간을 구함
 * 4. 결과를 출력
 * 
 * 시간 복잡도: O((N + D) log N) - N은 컴퓨터 개수, D는 의존성 개수
 * 공간 복잡도: O(N + D) - 그래프 저장을 위한 공간
 */

public class Baekjoon10282 {

    public static class Edge {
        public int to;
        public int distance;

        public Edge(int to, int distance) {
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
                int n = tokens[0]; // 컴퓨터 개수
                int d = tokens[1]; // 의존성의 개수
                int c = tokens[2]; // 해킹당한 컴퓨터의 번호

                List<List<Edge>> graph = new ArrayList<>();

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

                int[] distance = dijkstra(n, c, graph);
                int count = 0;
                int sum = 0;
                for (int i = 1; i <= n; i++) {
                    if (distance[i] != Integer.MAX_VALUE) {
                        count++;
                        sum = Math.max(sum, distance[i]);
                    }
                }

                answer.append(count).append(" ").append(sum).append("\n");
            }

            bw.write(String.valueOf(answer));
            bw.flush();
        } catch (IOException e) {
            e.getMessage();
        }
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
