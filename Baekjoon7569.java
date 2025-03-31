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

    public static final int[] dx = { 1, -1, 0, 0, 0, 0 };
    public static final int[] dy = { 0, 0, 1, -1, 0, 0 };
    public static final int[] dz = { 0, 0, 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        M = input[0];
        N = input[1];
        H = input[2];

        box = new int[M][N][H];
        visited = new boolean[M][N][H];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < M; j++) {
                input = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

                for (int k = 0; k < N; k++) {
                    box[j][k][i] = input[k];

                    if (box[j][k][i] == 1) {
                        tomatoSpots.add(new Position3D(j, k, i));
                    }
                }
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        int count = 0;
        Queue<Position3D> queue = new ArrayDeque<>();
        int[][][] distance = new int[M][N][H];

        for (Position3D tomatoSpot : tomatoSpots) {
            visited[tomatoSpot.y][tomatoSpot.x][tomatoSpot.z] = true;
            queue.offer(tomatoSpot);
        }

        while (!queue.isEmpty()) {
            Position3D curr = queue.poll();

            for (int d = 0; d < 6; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];
                int nz = curr.z + dz[d];

                if (isInBounds(nx, ny, nz) && !visited[ny][nx][nz] && box[ny][nx][nz] == 0) {
                    visited[ny][nx][nz] = true;
                    distance[ny][nx][nz] = distance[curr.y][curr.x][curr.z] + 1;
                    count = Math.max(count, distance[ny][nx][nz]);
                    queue.offer(new Position3D(nx, ny, nz));
                }
            }
        }

        return count;
    }

    public static boolean isInBounds(int x, int y, int z) {
        return 0 <= x && x < N && 0 <= y && y < M && 0 <= z && z < H;
    }

    public static class Position3D {
        public int x;
        public int y;
        public int z;

        public Position3D(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
