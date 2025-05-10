import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Baekjoon17836 {

    public static int N, M, T;
    public static int[][] map;
    public static final int[][] dxdy = {
            { 0, -1 }, // 상
            { 0, 1 }, // 하
            { -1, 0 }, // 좌
            { 1, 0 } // 우
    };

    public static class Position2D {
        public int x, y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Hero extends Position2D {
        public int time;

        public Hero(int x, int y, int time) {
            super(x, y);
            this.time = time;
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
            String[] line = br.readLine().split(" ");
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(line[x]);
            }
        }

        int answer = solution();
        System.out.println(answer != -1 ? answer : "Fail");
    }

    public static int solution() {
        int res1 = normalBfs();
        int res2 = gramrBfs();

        int answer = Integer.MAX_VALUE;
        if (res1 != -1)
            answer = Math.min(answer, res1);
        if (res2 != -1)
            answer = Math.min(answer, res2);

        return answer <= T ? answer : -1;
    }

    // 벽을 통과하지 않고 공주에게 도달하는 최단시간
    public static int normalBfs() {
        int[][] visited = new int[N][M];
        for (int[] row : visited)
            Arrays.fill(row, -1);

        Queue<Hero> queue = new ArrayDeque<>();
        queue.offer(new Hero(0, 0, 0));
        visited[0][0] = 0;

        while (!queue.isEmpty()) {
            Hero curr = queue.poll();

            // 제한시간 확인
            if (curr.time > T)
                continue;

            // 목표 지점 확인
            if (curr.x == M - 1 && curr.y == N - 1)
                return curr.time;

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dxdy[d][0];
                int ny = curr.y + dxdy[d][1];

                // 배열 범위 확인
                if (nx < 0 || nx >= M || ny < 0 || ny >= N)
                    continue;

                // 벽 확인: 벽으로는 이동 불가능
                if (map[ny][nx] == 1)
                    continue;

                // 방문 여부 확인
                if (visited[ny][nx] != -1)
                    continue;

                // 신규 방문
                visited[ny][nx] = curr.time + 1;
                queue.offer(new Hero(nx, ny, curr.time + 1));
            }
        }

        return -1;
    }

    // 그람을 얻고 벽을 통과해서 공주에게 도달하는 최단시간
    public static int gramrBfs() {
        int[][] visited = new int[N][M];
        for (int[] row : visited)
            Arrays.fill(row, -1);

        Queue<Hero> queue = new ArrayDeque<>();
        queue.offer(new Hero(0, 0, 0));
        visited[0][0] = 0;

        Hero gramHero = null;

        // Step 1: 그람 획득
        while (!queue.isEmpty()) {
            Hero curr = queue.poll();

            // 제한시간 확인
            if (curr.time > T)
                continue;

            // 목표 지점 확인
            if (map[curr.y][curr.x] == 2) {
                gramHero = curr;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dxdy[d][0];
                int ny = curr.y + dxdy[d][1];

                // 배열 범위 확인
                if (nx < 0 || nx >= M || ny < 0 || ny >= N)
                    continue;

                // 벽 확인: 벽으로는 이동 불가능
                if (map[ny][nx] == 1)
                    continue;

                // 방문 여부 확인
                if (visited[ny][nx] != -1)
                    continue;

                // 신규 방문
                visited[ny][nx] = curr.time + 1;
                queue.offer(new Hero(nx, ny, curr.time + 1));
            }
        }

        // 그람 획득 여부 확인
        if (gramHero == null)
            return -1;

        // Step 2: 벽 통과 가능 상태로 다시 BFS
        int[][] visitedAfter = new int[N][M];
        for (int[] row : visitedAfter)
            Arrays.fill(row, -1);

        Queue<Hero> afterQ = new ArrayDeque<>();
        afterQ.offer(new Hero(gramHero.x, gramHero.y, gramHero.time));
        visitedAfter[gramHero.y][gramHero.x] = gramHero.time;

        while (!afterQ.isEmpty()) {
            Hero curr = afterQ.poll();

            // 제한시간 확인
            if (curr.time > T)
                continue;

            // 목표 지점 확인
            if (curr.x == M - 1 && curr.y == N - 1)
                return curr.time;

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dxdy[d][0];
                int ny = curr.y + dxdy[d][1];

                // 배열 범위 확인
                if (nx < 0 || nx >= M || ny < 0 || ny >= N)
                    continue;

                // 방문 여부 확인
                if (visitedAfter[ny][nx] != -1)
                    continue;

                visitedAfter[ny][nx] = curr.time + 1;
                afterQ.offer(new Hero(nx, ny, curr.time + 1));
            }
        }

        return -1;
    }
}
