import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Baekjoon2636 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    static int time = 0;
    static int lastCheeseCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");
        N = Integer.parseInt(size[0]);
        M = Integer.parseInt(size[1]);

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        while (true) {
            int cheese = countCheese();
            if (cheese == 0)
                break;

            lastCheeseCount = cheese;
            bfsMarkOutsideAir();
            meltCheese();
            time++;
        }

        System.out.println(time);
        System.out.println(lastCheeseCount);
    }

    static int countCheese() {
        int count = 0;
        for (int[] row : map) {
            for (int val : row) {
                if (val == 1)
                    count++;
            }
        }
        return count;
    }

    static void bfsMarkOutsideAir() {
        visited = new boolean[N][M];
        Queue<Position2D> queue = new ArrayDeque<>();
        queue.offer(new Position2D(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();
            int x = curr.x;
            int y = curr.y;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (isInBounds(nx, ny) && !visited[ny][nx]) {
                    if (map[ny][nx] == 0) {
                        visited[ny][nx] = true;
                        queue.offer(new Position2D(nx, ny));
                    } else if (map[ny][nx] == 1) {
                        map[ny][nx] = 2; // 치즈지만 외부공기와 접촉한 경우
                        visited[ny][nx] = true;
                    }
                }
            }
        }
    }

    static void meltCheese() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) {
                    map[i][j] = 0;
                }
            }
        }
    }

    static boolean isInBounds(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }

    static class Position2D {
        int x, y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
    