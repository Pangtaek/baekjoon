import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Baekjoon3075 {

    public static final int INF = 100_000_000;
    public static int[][] distance; // 사람별 행성의 최단 거리 정보 배열
    public static List<List<Edge>> graph; // 간선 정보 리스트

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
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int[] tokens = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int n = tokens[0]; // 사람 수
            int p = tokens[1]; // 은하 수
            int q = tokens[2]; // 경로 수

            // 시작 위치 정보
            int[] spots = new int[n];
            for (int i = 0; i < n; i++) {
                spots[i] = Integer.parseInt(br.readLine());
            }

            // 거리 배열 초기화
            distance = new int[n][p + 1];
            for (int person = 0; person < n; person++) {
                Arrays.fill(distance[person], INF);
                distance[person][spots[person]] = 0;
            }

            // 그래프 초기화
            graph = new ArrayList<>();
            for (int i = 0; i <= p; i++) {
                graph.add(new ArrayList<>());
            }

            // 그래프 입력
            for (int i = 0; i < q; i++) {
                tokens = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
                int from = tokens[0];
                int to = tokens[1];
                int dist = tokens[2];

                // 무방향 그래프
                graph.get(from).add(new Edge(to, dist));
                graph.get(to).add(new Edge(from, dist));
            }

            // Dijkstra 실행
            dijkstra(spots);

            // 정답 계산 및 출력
            int[] result = findBestMeetingPoint(spots, p);
            answer.append(result[0]).append(" ").append(result[1]).append("\n");
        }

        System.out.print(answer);
    }

    // 각 사람마다 다익스트라 실행
    public static void dijkstra(int[] spots) {
        for (int person = 0; person < spots.length; person++) {
            PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.distance - o2.distance);
            pq.offer(new Edge(spots[person], 0));

            while (!pq.isEmpty()) {
                Edge curr = pq.poll();

                if (distance[person][curr.to] < curr.distance)
                    continue;

                for (Edge next : graph.get(curr.to)) {
                    int newDistance = distance[person][curr.to] + next.distance;
                    if (newDistance < distance[person][next.to]) {
                        distance[person][next.to] = newDistance;
                        pq.offer(new Edge(next.to, newDistance));
                    }
                }
            }
        }
    }

    // 모든 사람이 도달 가능한 행성 중, 가장 가까운 행성의 번호와 총비용을 반환 메서드
    public static int[] findBestMeetingPoint(int[] spots, int p) {
        int bestPlanet = 0;
        int minCharge = INF;

        for (int planet = 1; planet <= p; planet++) {
            boolean reachable = true;
            int totalCost = 0;

            for (int person = 0; person < spots.length; person++) {
                int d = getDistance(person, planet);
                if (d == INF) {
                    reachable = false;
                    break;
                }
                totalCost += d * d;
            }

            if (reachable && (totalCost < minCharge || (totalCost == minCharge && planet < bestPlanet))) {
                bestPlanet = planet;
                minCharge = totalCost;
            }
        }

        return new int[] { bestPlanet, minCharge };
    }

    // 행성까지의 거리 반환 메서드
    public static int getDistance(int n, int planet) {
        return distance[n][planet];
    }
}
