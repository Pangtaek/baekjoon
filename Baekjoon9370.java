import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Baekjoon9370 {

    private static class Edge implements Comparable<Edge> {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.weight, e.weight);
        }
    }

    private static int n, m, t; // 교차로, 도로, 목적지 후보 수
    private static int s, g, h; // 출발지, 특별한 도로 g-h
    private static List<List<Edge>> graph; // 인접 리스트 그래프
    private static int[] distFromS, distFromG, distFromH;
    private static List<Integer> candidates;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        while (T-- > 0) {
            // 교차로, 도로, 목적지 후보 수
            int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            n = tokens[0];
            m = tokens[1];
            t = tokens[2];

            // 출발지, g, h
            tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            s = tokens[0];
            g = tokens[1];
            h = tokens[2];

            // 그래프 초기화
            graph = new ArrayList<>();
            for (int i = 0; i <= n; i++)
                graph.add(new ArrayList<>());

            // 도로 입력
            for (int i = 0; i < m; i++) {
                tokens = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int a = tokens[0], b = tokens[1], d = tokens[2];
                graph.get(a).add(new Edge(b, d));
                graph.get(b).add(new Edge(a, d));
            }

            // 목적지 후보 입력
            candidates = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                candidates.add(Integer.parseInt(br.readLine()));
            }

            // 다익스트라 3회 실행
            distFromS = dijkstra(s);
            distFromG = dijkstra(g);
            distFromH = dijkstra(h);

            // 목적지 필터링
            List<Integer> answer = new ArrayList<>();
            for (int x : candidates) {
                // s -> g -> h -> x
                int path1 = distFromS[g] + distFromG[h] + distFromH[x];
                // s -> h -> g -> x
                int path2 = distFromS[h] + distFromH[g] + distFromG[x];
                // 최단 경로가 g-h를 포함하면 추가
                if (distFromS[x] == path1 || distFromS[x] == path2) {
                    answer.add(x);
                }
            }

            // 오름차순 정렬 후 출력 버퍼에 추가
            Collections.sort(answer);
            for (int i = 0; i < answer.size(); i++) {
                sb.append(answer.get(i));
                if (i < answer.size() - 1)
                    sb.append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    /**
     * 다익스트라 알고리즘
     * 
     * @param start 시작 노드
     * @return start에서 각 노드까지 최단 거리 배열
     */
    private static int[] dijkstra(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int now = current.to;

            if (current.weight > dist[now])
                continue;

            for (Edge neighbor : graph.get(now)) {
                int newDist = dist[now] + neighbor.weight;
                if (dist[neighbor.to] > newDist) {
                    dist[neighbor.to] = newDist;
                    pq.offer(new Edge(neighbor.to, newDist));
                }
            }
        }
        return dist;
    }
}
