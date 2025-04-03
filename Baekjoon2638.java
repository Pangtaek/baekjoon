import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Baekjoon2638 {
    public static int N, M;
    public static int[][] cheeseBoard;
    public static boolean[][] visited;

    public static final int[] dx = { 0, 0, 1, -1 };
    public static final int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        getInput();
        System.out.println(solution());
    }

    public static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];
        cheeseBoard = new int[N][M];

        for (int i = 0; i < N; i++) {
            input = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            cheeseBoard[i] = input;
        }
    }

    public static int solution() {
        int time = 0;

        while (true) {
            if (getCheeseCount() == 0)
                break;

            markOutsideAir();
            meltCheese();
            time++;
        }

        return time;
    }

    public static int getCheeseCount() {
        int count = 0;
        for (int[] row : cheeseBoard) {
            for (int val : row) {
                if (val == 1)
                    count++;
            }
        }
        return count;
    }

    public static void markOutsideAir() {
        visited = new boolean[N][M];
        Queue<Position2D> q = new ArrayDeque<>();
        q.offer(new Position2D(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Position2D curr = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if (isInBounds(nx, ny) && !visited[ny][nx] && cheeseBoard[ny][nx] == 0) {
                    visited[ny][nx] = true;
                    q.offer(new Position2D(nx, ny));
                }
            }
        }
    }

    public static void meltCheese() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (cheeseBoard[y][x] == 1) {
                    int contact = 0;

                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];

                        if (isInBounds(nx, ny) && visited[ny][nx] && cheeseBoard[ny][nx] == 0) {
                            contact++;
                        }
                    }

                    if (contact >= 2) {
                        cheeseBoard[y][x] = 2; // 녹을 치즈 표시
                    }
                }
            }
        }

        // 실제로 치즈 녹이기
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (cheeseBoard[y][x] == 2) {
                    cheeseBoard[y][x] = 0;
                }
            }
        }
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
