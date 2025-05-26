import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Baekjoon2206 {
    static final int[][] directions = {
            { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } // 상, 하, 좌, 우
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int N = tokens[0];
        int M = tokens[1];
        int[][] map = new int[N][M];

        for (int row = 0; row < N; row++) {
            String input = br.readLine();

            for (int col = 0; col < M; col++) {
                map[row][col] = Character.getNumericValue(input.charAt(col));
            }
        }

        bw.write(solution(N, M, map) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static int solution(int N, int M, int[][] map) {
        Deque<Player> dq = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][2]; // visited[y][x][0:안부숨, 1:부숨]

        dq.offer(new Player(0, 0, 1, false));
        visited[0][0][0] = true; // 시작지점은 벽을 부수지 않고 방문

        while (!dq.isEmpty()) {
            Player curr = dq.poll();

            // 도착지에 도달했을 경우
            if (curr.x == M - 1 && curr.y == N - 1) {
                return curr.distance;
            }

            for (int[] d : directions) {
                int nx = curr.x + d[0];
                int ny = curr.y + d[1];

                if (nx < 0 || ny < 0 || nx >= M || ny >= N)
                    continue;

                // 벽이 아닌 경우
                if (map[ny][nx] == 0) {
                    int broken = curr.isBreak ? 1 : 0;
                    if (!visited[ny][nx][broken]) {
                        visited[ny][nx][broken] = true;
                        dq.offer(new Player(nx, ny, curr.distance + 1, curr.isBreak));
                    }
                }
                // 벽인 경우
                else {
                    if (!curr.isBreak && !visited[ny][nx][1]) {
                        visited[ny][nx][1] = true;
                        dq.offer(new Player(nx, ny, curr.distance + 1, true)); // 벽을 부순 상태로 이동
                    }
                }
            }
        }

        return -1; // 도달할 수 없는 경우
    }

    static class Player extends Position2D {
        public boolean isBreak;
        public int distance;

        public Player(int x, int y, int distance, boolean isBreak) {
            super(x, y);
            this.isBreak = isBreak;
            this.distance = distance;
        }
    }

    static class Position2D {
        public int x;
        public int y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
