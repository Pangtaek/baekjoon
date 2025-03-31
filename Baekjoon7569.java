import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Baekjoon7569 {

    public static int M, N, H; // 가로, 세로, 높이
    public static int[][][] box;
    public static boolean[][][] visited;
    public static List<Position3D> tomatoSpots = new ArrayList<>();

    // 6방향 이동 (상, 하, 좌, 우, 위, 아래)
    public static final int[] dx = { 1, -1, 0, 0, 0, 0 };
    public static final int[] dy = { 0, 0, 1, -1, 0, 0 };
    public static final int[] dz = { 0, 0, 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        M = input[0];
        N = input[1];
        H = input[2];

        box = new int[H][N][M];
        visited = new boolean[H][N][M];

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                int[] row = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                for (int m = 0; m < M; m++) {
                    box[h][n][m] = row[m];

                    if (box[h][n][m] == 1) {
                        tomatoSpots.add(new Position3D(m, n, h));
                    }
                }
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Position3D> queue = new ArrayDeque<>();
        int[][][] day = new int[H][N][M];
        int maxDays = 0;

        for (Position3D tomato : tomatoSpots) {
            visited[tomato.z][tomato.y][tomato.x] = true;
            queue.offer(tomato);
        }

        while (!queue.isEmpty()) {
            Position3D curr = queue.poll();

            for (int d = 0; d < 6; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];
                int nz = curr.z + dz[d];

                boolean flag = isInBounds(nx, ny, nz)
                        && !visited[nz][ny][nx]
                        && box[nz][ny][nx] == 0;
                if (flag) {
                    visited[nz][ny][nx] = true;
                    day[nz][ny][nx] = day[curr.z][curr.y][curr.x] + 1;
                    maxDays = Math.max(maxDays, day[nz][ny][nx]);
                    queue.offer(new Position3D(nx, ny, nz));
                }
            }
        }

        // 익지 않은 토마토가 남아있는지 확인
        for (int z = 0; z < H; z++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (box[z][y][x] == 0 && !visited[z][y][x]) {
                        return -1;
                    }
                }
            }
        }

        return maxDays;
    }

    public static boolean isInBounds(int x, int y, int z) {
        return 0 <= x && x < M && 0 <= y && y < N && 0 <= z && z < H;
    }

    public static class Position3D {
        public int x, y, z;

        public Position3D(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
