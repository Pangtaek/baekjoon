import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon1865 {

    public static int TC;
    public static int N, M, W;

    public static final int INF = Integer.MAX_VALUE;

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
            if (runTestCase(br)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static boolean runTestCase(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        int totalEdges = 2 * M + W;
        edges = new Edge[totalEdges];
        int idx = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            edges[idx++] = new Edge(from, to, time);
            edges[idx++] = new Edge(to, from, time);
        }

        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            edges[idx++] = new Edge(from, to, -time); 
        }

        // 가상 출발 노드 0번에서 모든 노드로 가는 간선 추가
        distance = new int[N + 1];
        Arrays.fill(distance, INF);
        distance[0] = 0;

        Edge[] newEdges = new Edge[edges.length + N];
        System.arraycopy(edges, 0, newEdges, 0, edges.length);
        for (int i = 1; i <= N; i++) {
            newEdges[idx++] = new Edge(0, i, 0);
        }
        edges = newEdges;

        return hasNegativeCycle();
    }

    public static boolean hasNegativeCycle() {
        for (int i = 0; i < N; i++) {   // 가상의 노드 0을 추가하였기 때문에 N번 반복을 수행한다.
            for (Edge edge : edges) {
                if (distance[edge.from] != INF && distance[edge.to] > distance[edge.from] + edge.time) {
                    distance[edge.to] = distance[edge.from] + edge.time;
                }
            }
        }

        for (Edge edge : edges) {
            if (distance[edge.from] != INF && distance[edge.to] > distance[edge.from] + edge.time) {
                return true;
            }
        }

        return false;
    }
}
