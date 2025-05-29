import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Baekjoon4485 {

    static int[][] DIRECTIONS = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

    static class Position2D implements Comparable<Position2D> {
        int x;
        int y;
        int cost;

        Position2D(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        public int compareTo(Position2D other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int index = 1;

        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0)
                break;

            int[][] cave = new int[N][N];
            for (int y = 0; y < N; y++) {
                cave[y] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            bw.write("Problem " + index++ + ": " + dijkstra(N, cave));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int dijkstra(int N, int[][] cave) {
        int[][] dist = new int[N][N];
        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);

        PriorityQueue<Position2D> pq = new PriorityQueue<>();
        pq.offer(new Position2D(0, 0, cave[0][0]));
        dist[0][0] = cave[0][0];

        while (!pq.isEmpty()) {
            Position2D curr = pq.poll();

            if (curr.cost > dist[curr.y][curr.x])
                continue;

            for (int[] d : DIRECTIONS) {
                int nx = curr.x + d[0];
                int ny = curr.y + d[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;

                int newCost = curr.cost + cave[ny][nx];
                if (newCost < dist[ny][nx]) {
                    dist[ny][nx] = newCost;
                    pq.offer(new Position2D(nx, ny, newCost));
                }
            }
        }

        return dist[N - 1][N - 1];
    }
}
