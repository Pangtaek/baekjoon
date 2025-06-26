import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Baekjoon16933 {

    private static final int[][] DIRECTIONS = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } }; // 상, 하, 좌, 우

    // input
    private static int N, M, K;
    private static int[][] map;

    private static class Position2D {
        protected int x;
        protected int y;

        private Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class State extends Position2D {
        private int count; // 이동 횟수
        private int wallsBroken; // 부순 벽 수
        private boolean isDay; // 낮 여부

        private State(int x, int y, int count, int wallsBroken, boolean isDay) {
            super(x, y);
            this.count = count;
            this.wallsBroken = wallsBroken;
            this.isDay = isDay;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);
        K = Integer.parseInt(tokens[2]);

        map = new int[N][M];
        for (int y = 0; y < N; y++) {
            String line = br.readLine();
            for (int x = 0; x < M; x++) {
                map[y][x] = line.charAt(x) - '0';
            }
        }

        int answer = bfs();
        bw.write(Integer.toString(answer));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs() {
        int[][] visited = new int[N][M]; // 해당 좌표까지 부순 벽의 최소 수
        for (int y = 0; y < N; y++) {
            Arrays.fill(visited[y], Integer.MAX_VALUE);
        }

        Queue<State> queue = new ArrayDeque<>();
        queue.offer(new State(0, 0, 1, 0, true)); // 시작은 낮, 이동 횟수 1

        visited[0][0] = 0;

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (cur.x == M - 1 && cur.y == N - 1) {
                return cur.count;
            }

            for (int[] d : DIRECTIONS) {
                int nx = cur.x + d[0];
                int ny = cur.y + d[1];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N)
                    continue;
                    
                if (visited[ny][nx] <= cur.wallsBroken)
                    continue;

                if (map[ny][nx] == 1) { // 벽
                    if (cur.wallsBroken >= K)
                        continue;

                    if (cur.isDay) {
                        visited[ny][nx] = cur.wallsBroken + 1;
                        queue.offer(new State(nx, ny, cur.count + 1, cur.wallsBroken + 1, false));
                    } else {
                        queue.offer(new State(cur.x, cur.y, cur.count + 1, cur.wallsBroken, true)); // 제자리 대기
                    }
                } else { // 빈 칸
                    visited[ny][nx] = cur.wallsBroken;
                    queue.offer(new State(nx, ny, cur.count + 1, cur.wallsBroken, !cur.isDay));
                }
            }
        }

        return -1;
    }
}
