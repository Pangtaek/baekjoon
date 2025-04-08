import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Baekjoon2178 {
    public static int N, M;
    public static int[][] maze;
    public static boolean[][] visited;
    public static int[] dx = { 0, 0, 1, -1 };
    public static int[] dy = { 1, -1, 0, 0 };

    public static class Position2D {
        public int x;
        public int y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tokens = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        N = tokens[0];
        M = tokens[1];

        maze = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = row.charAt(j) - '0';
            }
        }

        System.out.println(solution());
    }

    public static int solution() {
        int[][] time = new int[N][M];
        Queue<Position2D> queue = new ArrayDeque<>();
        queue.offer(new Position2D(0, 0));
        visited[0][0] = true;
        time[0][0] = 1;

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                boolean flag = isInBounds(nx, ny) & !visited[ny][nx] && maze[ny][nx] == 1;
                if (flag) {
                    visited[ny][nx] = true;
                    time[ny][nx] = time[curr.y][curr.x] + 1;
                    queue.offer(new Position2D(nx, ny));
                }
            }
        }

        return time[N - 1][M - 1];
    }

    public static boolean isInBounds(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }
}
