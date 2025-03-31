import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Baekjoon2589 {

    public static int col, row;
    public static char[][] map;
    public static int[] dx = { 1, -1, 0, 0 };
    public static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = br.readLine().split("\\s+");
        col = Integer.parseInt(tokens[0]);
        row = Integer.parseInt(tokens[1]);

        map = new char[col][row];

        for (int i = 0; i < col; i++) {
            String line = br.readLine();
            for (int j = 0; j < row; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int answer = 0;

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (map[i][j] == 'L') {
                    answer = Math.max(answer, bfs(new Position2D(j, i)));
                }
            }
        }

        System.out.println(answer);
    }

    public static int bfs(Position2D start) {
        boolean[][] visited = new boolean[col][row];
        int[][] dist = new int[col][row];
        Queue<Position2D> queue = new ArrayDeque<>();

        visited[start.y][start.x] = true;
        queue.offer(start);

        int maxDistance = 0;

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if (isInBounds(nx, ny) && !visited[ny][nx] && map[ny][nx] == 'L') {
                    visited[ny][nx] = true;
                    dist[ny][nx] = dist[curr.y][curr.x] + 1;
                    maxDistance = Math.max(maxDistance, dist[ny][nx]);
                    queue.offer(new Position2D(nx, ny));
                }
            }
        }

        return maxDistance;
    }

    public static boolean isInBounds(int x, int y) {
        return 0 <= x && x < row && 0 <= y && y < col;
    }

    public static class Position2D {
        int x, y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
