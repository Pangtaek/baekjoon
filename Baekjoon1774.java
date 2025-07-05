import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Baekjoon1774 {

    private static class Position2D {
        int x, y;

        Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Edge implements Comparable<Edge> {
        int u, v;
        double cost;

        Edge(int u, int v, double cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return Double.compare(this.cost, other.cost);
        }
    }

    private static class UnionFind {
        int[] parent;

        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB)
                return false;
            parent[rootB] = rootA;
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int N = tokens[0]; // 우주신들의 수
        int M = tokens[1]; // 이미 연결된 통로 수

        Position2D[] positions = new Position2D[N];
        for (int i = 0; i < N; i++) {
            tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            positions[i] = new Position2D(tokens[0], tokens[1]);
        }

        UnionFind uf = new UnionFind(N);

        for (int i = 0; i < M; i++) {
            tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int a = tokens[0] - 1; // 입력은 1-indexed
            int b = tokens[1] - 1;
            uf.union(a, b); // 미리 연결된 통로 처리
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double dist = Math.sqrt(Math.pow(positions[i].x - positions[j].x, 2)
                        + Math.pow(positions[i].y - positions[j].y, 2));
                pq.offer(new Edge(i, j, dist));
            }
        }

        double total = 0.0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (uf.union(edge.u, edge.v)) {
                total += edge.cost;
            }
        }

        bw.write(String.format("%.2f\n", total));
        bw.flush();
        bw.close();
        br.close();
    }
}
