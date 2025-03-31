import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Baekjoon7576 {

    public static int M, N; // 가로, 세로
    public static List<Position2D> tomatoSpots = new ArrayList<>();
    public static int[][] box;

    public static final int[] dx = { 1, -1, 0, 0 };
    public static final int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        M = tokens[0];
        N = tokens[1];

        box = new int[N][M];

        for (int col = 0; col < N; col++) {
            tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int row = 0; row < M; row++) {
                box[col][row] = tokens[row];

                if (box[col][row] == 1) {
                    tomatoSpots.add(new Position2D(row, col));
                }
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        int maxDays = 0;
        Queue<Position2D> queue = new ArrayDeque<>();
        int[][] day = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        for (Position2D tomatoSpot : tomatoSpots) {
            visited[tomatoSpot.y][tomatoSpot.x] = true;
            queue.offer(tomatoSpot);
        }

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                boolean flag = isInBounds(nx, ny) && !visited[ny][nx] && box[ny][nx] == 0;
                if (flag) {
                    visited[ny][nx] = true;
                    day[ny][nx] = day[curr.y][curr.x] + 1;
                    maxDays = Math.max(day[ny][nx], maxDays);
                    queue.offer(new Position2D(nx, ny));
                }
            }
        }

        for (int col = 0; col < N; col++) {
            for (int row = 0; row < M; row++) {
                if (day[col][row] == 0 && !visited[col][row]) {
                    return -1;
                }
            }
        }

        return maxDays;
    }

    public static boolean isInBounds(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }

    public static class Position2D {
        public int x, y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
