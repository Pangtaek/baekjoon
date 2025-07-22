import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Baekjoon14442 {

    private static final int[][] DIRECTIONS = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

    private static class Node {
        int x, y, wallsBroken, dist;

        Node(int x, int y, int wallsBroken, int dist) {
            this.x = x;
            this.y = y;
            this.wallsBroken = wallsBroken;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = tokens[0];
        int M = tokens[1];
        int K = tokens[2];

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        int result = bfs(N, M, K, map);
        bw.write(result + "\n");
        bw.flush();
    }

    private static int bfs(int N, int M, int K, int[][] map) {
        boolean[][][] visited = new boolean[N][M][K + 1];
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0, 0, 1));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.x == N - 1 && current.y == M - 1) {
                return current.dist;
            }

            for (int[] dir : DIRECTIONS) {
                int nx = current.x + dir[0];
                int ny = current.y + dir[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                // 벽이 없는 경우
                if (map[nx][ny] == 0 && !visited[nx][ny][current.wallsBroken]) {
                    visited[nx][ny][current.wallsBroken] = true;
                    queue.offer(new Node(nx, ny, current.wallsBroken, current.dist + 1));
                }
                // 벽이 있고 부술 수 있는 경우
                else if (map[nx][ny] == 1 && current.wallsBroken < K && !visited[nx][ny][current.wallsBroken + 1]) {
                    visited[nx][ny][current.wallsBroken + 1] = true;
                    queue.offer(new Node(nx, ny, current.wallsBroken + 1, current.dist + 1));
                }
            }
        }

        return -1;
    }
}
