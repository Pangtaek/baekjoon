import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Baekjoon20160 {
    static class Edge {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static int V, E;
    static List<List<Edge>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 정점 수 V, 간선 수 E 입력
        int[] ve = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        V = ve[0];
        E = ve[1];

        for (int i = 0; i <= V; i++)
            graph.add(new ArrayList<>());

        // 간선 정보 입력
        for (int i = 0; i < E; i++) {
            int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }

        // 야쿠르트 아줌마 이동 순서 입력
        int[] moveOrder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 내 출발 위치 입력
        int myStart = Integer.parseInt(br.readLine());

        // 나의 출발점에서 모든 정점까지의 최단 거리
        int[] myCost = dijkstra(myStart);

        long beforeTime = 0;
        int answer = 10001; // 문제 조건상 정점 번호는 10000 이하

        // 내가 아줌마의 첫 정점에 있으면 그 정점이 정답 가능성 있음
        if (myStart == moveOrder[0])
            answer = myStart;

        // 아줌마의 이동 경로 시뮬레이션
        for (int i = 0; i < 9; i++) {
            int[] cost = dijkstra(moveOrder[i]); // 현재 지점에서 다음 지점까지 비용 계산
            int nextIdx = i + 1;

            // 다음으로 갈 수 있는 유효한 정점 찾기
            while (nextIdx < 10 && cost[moveOrder[nextIdx]] == Integer.MAX_VALUE) {
                nextIdx++;
            }
            if (nextIdx >= 10)
                break;

            // 내가 더 빠르거나 동시에 도착 가능하고 정점 번호가 작으면 갱신
            if (myCost[moveOrder[nextIdx]] <= beforeTime + cost[moveOrder[nextIdx]] && moveOrder[nextIdx] < answer) {
                answer = moveOrder[nextIdx];
            }

            // 다음으로 누적 시간 갱신
            beforeTime += cost[moveOrder[nextIdx]];
            i = nextIdx - 1; // 다음 루프를 위한 인덱스 조정
        }

        if (answer == 10001)
            answer = -1;
        bw.write(Integer.toString(answer));
        bw.newLine();
        bw.flush();
        br.close();
        bw.close();
    }

    static int[] dijkstra(int start) {
        int[] distance = new int[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            if (curr.weight > distance[curr.to])
                continue;

            for (Edge next : graph.get(curr.to)) {
                int newDist = curr.weight + next.weight;
                if (newDist < distance[next.to]) {
                    distance[next.to] = newDist;
                    pq.offer(new Edge(next.to, newDist));
                }
            }
        }

        return distance;
    }
}
