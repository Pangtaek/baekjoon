import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Baekjoon2665 {
    public static int N;
    public static int[][] metrix;
    public static int[][] cost;

    public static final int INF = Integer.MAX_VALUE;
    public static final int[] dx = { 1, -1, 0, 0 };
    public static final int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        metrix = new int[N][N];
        cost = new int[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            for (int j = 0; j < N; j++) {
                metrix[i][j] = input.charAt(j) - '0';
                cost[i][j] = INF;
            }
        }

        bfs();

        System.out.println(cost[N - 1][N - 1]);
    }

    public static void bfs() {
        Deque<Position2D> deque = new ArrayDeque<>();
        cost[0][0] = 0;
        deque.offer(new Position2D(0, 0));

        while (!deque.isEmpty()) {
            Position2D curr = deque.poll();

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if (!isInBounds(nx, ny))
                    continue;

                int newCost = cost[curr.y][curr.x] + (metrix[ny][nx] == 0 ? 1 : 0);

                // 더 작은 비용으로 갈 수 있을 때만 갱신
                if (cost[ny][nx] > newCost) {
                    cost[ny][nx] = newCost;

                    if (metrix[ny][nx] == 1) {
                        deque.offerFirst(new Position2D(nx, ny));
                    } else {
                        deque.offerLast(new Position2D(nx, ny));
                    }
                }
            }
        }
    }

    public static boolean isInBounds(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public static class Position2D {
        public int x;
        public int y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}