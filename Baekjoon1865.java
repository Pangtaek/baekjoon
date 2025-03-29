import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon1865 {

    public static int TC;
    public static int N, M, W;

    public static int INF = Integer.MAX_VALUE;

    public static int[] distance;
    public static Edge[] edges;

    public static class Edge {
        int from, to, time;

        public Edge(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            init(br);

            if (bellmanFord()) {
                System.out.println("YES\n");
            } else {
                System.out.println("NO\n");
            }
        }
    }

    public static boolean bellmanFord() {
        for (int k = 0; k < N - 1; k++) {
            for (Edge edge : edges) {
                if (distance[edge.from] == INF)
                    continue;

                if (distance[edge.to] > distance[edge.from] + edge.time)
                    distance[edge.to] = distance[edge.from] + edge.time;
            }
        }

        for (Edge edge : edges) {
            if (distance[edge.from] == INF)
                continue;

            if (distance[edge.to] > distance[edge.from] + edge.time)
                return false;
        }

        return true;
    }

    public static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        distance = new int[N + 1];
        Arrays.fill(distance, INF);
        distance[1] = 0;

        // 도로 + 웜홀 만큼의 간선 배열 생성
        edges = new Edge[2 * M + W];

        // 노선 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(from, to, time);
        }

        // 웜홀 정보 입력
        for (int i = M; i < M + W; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = -1 * Integer.parseInt(st.nextToken());

            edges[i] = new Edge(from, to, time);
        }
    }
}
