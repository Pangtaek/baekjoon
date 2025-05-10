import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;

public class Baekjoon17836 {

    public static int N;
    public static int M;
    public static int T;
    public static Position2D gramrSpot;
    public static int[][] map;

    public static final int[][] dxdy = {
            { 0, -1 }, // 상
            { 0, 1 }, // 하
            { -1, 0 }, // 좌
            { 1, 0 } // 우
    };

    public static class Position2D {
        public int x;
        public int y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Hero extends Position2D {
        public boolean hasGramr = false;
        public int time = 0;
        public int dir = 0;

        public Hero(int x, int y) {
            super(x, y);
        }

        public Hero(int x, int y, int time) {
            this(x, y);
            this.time = time;
        }

        public Hero(int x, int y, int time, boolean hasGramr) {
            this(x, y, time);
            this.hasGramr = true;
        }

        public Hero(int x, int y, int time, int dir) {
            this(x, y, time);
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = tokens[0];
        M = tokens[1];
        T = tokens[2];
        map = new int[N][M];

        for (int y = 0; y < N; y++) {
            String input = br.readLine();

            for (int x = 0; x < M; x++) {
                char c = input.charAt(x);

                if (c == '2')
                    gramrSpot = new Position2D(x, y);

                map[y][x] = c - '0';
            }
        }

        br.close();

        int answer = solution();
        System.out.println(answer != -1 ? answer : "Fail");
    }

    public static int solution() {
        return Math.min(normalBfs(), gramrBfs());
    }

    public static int normalBfs() {
        int[][] visited = new int[N][M];
        Queue<Hero> queue = new ArrayDeque<>();

        for (int[] row : visited)
            Arrays.fill(row, -1);

        visited[0][0] = 0;
        queue.offer(new Hero(0, 0));

        while (!queue.isEmpty()) {
            Hero curr = queue.poll();

            if (curr.time >= T)
                continue;

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dxdy[d][0];
                int ny = curr.y + dxdy[d][1];
                int nt = curr.time + 1;

                if (nx < 0 || nx >= M || ny < 0 || ny >= N)
                    continue;

                if (map[ny][nx] == 1)
                    continue;

                if (visited[ny][nx] != -1)
                    continue;

                visited[ny][nx] = nt;
                queue.offer(new Hero(nx, ny, nt));
            }
        }

        return visited[N - 1][M - 1];
    }

    public static int gramrBfs() {
        int[][] visited = new int[N][M];
        Deque<Hero> dq = new ArrayDeque<>();
        boolean isGameOver = false;
        Hero step2Position = null;

        for (int[] row : visited)
            Arrays.fill(row, -1);

        dq.offer(new Hero(0, 0));

        // Step 1. 그람 찾기
        while (!dq.isEmpty()) {
            Hero curr = dq.poll();

            // 시간초과시 게임오버
            if (curr.time >= T) {
                isGameOver = true;
                break;
            }

            // 그람을 찾으면 종료
            if (curr.hasGramr)
                break;

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dxdy[d][0];
                int ny = curr.y + dxdy[d][1];
                int nt = curr.time + 1;

                // 범위 확인
                if (nx < 0 || nx >= M || ny < 0 || ny >= N)
                    continue;

                // 벽 확인
                if (map[ny][nx] == 1)
                    continue;

                // 방문 여부 확인
                if (visited[ny][nx] != -1)
                    continue;

                // 그람 여부 확인
                if (map[ny][nx] == 2) {
                    dq.offer(new Hero(nx, ny, nt, true));
                    step2Position = new Hero(nx, ny, nt);
                    break;
                }

                visited[ny][nx] = nt;
                dq.offer(new Hero(nx, ny, nt));
            }
        }

        if (isGameOver)
            return -1;

        if (step2Position != null) {
            visited[N - 1][M - 1] = calculateTimeDuration(step2Position) <= T ? calculateTimeDuration(step2Position)
                    : -1;
        }

        return visited[N - 1][M - 1];
    }

    public static int calculateTimeDuration(Hero hero) {
        int addX = M - 1 - hero.x;
        int addY = N - 1 - hero.y;

        return hero.time + addX + addY;
    }
}
