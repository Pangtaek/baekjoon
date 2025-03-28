import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon11657 {

    static class Edge {
        int from, to, time;

        public Edge(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }

    static int N, M;
    static Edge[] edges;
    static final long INF = Long.MAX_VALUE;
    static long[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점 수
        M = Integer.parseInt(st.nextToken()); // 간선 수

        edges = new Edge[M];
        dist = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            dist[i] = INF;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(from, to, time);
        }

        dist[1] = 0; // 1번 정점 시작

        // 벨만-포드 알고리즘
        if (bellmanFord()) {
            System.out.println(-1); // 음수 사이클 존재
        } else {
            for (int i = 2; i <= N; i++) {
                System.out.println(dist[i] == INF ? -1 : dist[i]);
            }
        }
    }

    public static boolean bellmanFord() {
        for (int i = 1; i <= N - 1; i++) {
            for (Edge e : edges) {
                if (dist[e.from] != INF && dist[e.to] > dist[e.from] + e.time) {
                    dist[e.to] = dist[e.from] + e.time;
                }
            }
        }

        // N번째 반복에서 갱신이 일어나면 음수 사이클 존재
        for (Edge e : edges) {
            if (dist[e.from] != INF && dist[e.to] > dist[e.from] + e.time) {
                return true;
            }
        }

        return false;
    }
}
