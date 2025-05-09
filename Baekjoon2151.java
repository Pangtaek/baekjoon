import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Baekjoon2151 {

    public static int N;
    public static char[][] map;
    public static Position2D[] doorSpotList = new Position2D[2];

    public static final int[][] dydx = {
            { -1, 0 }, // 상
            { 1, 0 }, // 하
            { 0, -1 }, // 좌
            { 0, 1 } // 우
    };

    public static class Position2D {
        public int x;
        public int y;
        public int count;
        public int dir;

        public Position2D(int x, int y, int count, int dir) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        int index = 0;
        for (int y = 0; y < N; y++) {
            String input = br.readLine();
            for (int x = 0; x < N; x++) {
                char c = input.charAt(x);
                if (c == '#') {
                    doorSpotList[index++] = new Position2D(x, y, 0, -1);
                }
                map[y][x] = c;
            }
        }

        System.out.println(solution());
    }

    public static int solution() {
        int[][][] visited = new int[N][N][4];
        for (int[][] row : visited)
            for (int[] col : row)
                Arrays.fill(col, Integer.MAX_VALUE);

        Deque<Position2D> dq = new ArrayDeque<>();
        Position2D start = doorSpotList[0];
        Position2D end = doorSpotList[1];

        // 4방향 모두 시작 가능
        for (int d = 0; d < 4; d++) {
            visited[start.y][start.x][d] = 0;
            dq.offer(new Position2D(start.x, start.y, 0, d));
        }

        while (!dq.isEmpty()) {
            Position2D curr = dq.poll();

            int nx = curr.x + dydx[curr.dir][1];
            int ny = curr.y + dydx[curr.dir][0];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                continue;
            if (map[ny][nx] == '*')
                continue;
            if (visited[ny][nx][curr.dir] <= curr.count)
                continue;

            visited[ny][nx][curr.dir] = curr.count;

            if (map[ny][nx] == '.' || map[ny][nx] == '#') 
                dq.offerFirst(new Position2D(nx, ny, curr.count, curr.dir));
            

            if (map[ny][nx] == '!') {
                // 직진
                dq.offerFirst(new Position2D(nx, ny, curr.count, curr.dir));
                // 90도 회전
                for (int turn : getTurnDirs(curr.dir)) {
                    if (visited[ny][nx][turn] > curr.count + 1) {
                        visited[ny][nx][turn] = curr.count + 1;
                        dq.offerLast(new Position2D(nx, ny, curr.count + 1, turn));
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            answer = Math.min(answer, visited[end.y][end.x][d]);
        }
        return answer;
    }

    // 주어진 방향에서 회전 가능한 2개의 방향 반환
    public static int[] getTurnDirs(int dir) {
        if (dir == 0 || dir == 1) { // 상 or 하 → 좌, 우
            return new int[] { 2, 3 };
        } else { // 좌 or 우 → 상, 하
            return new int[] { 0, 1 };
        }
    }
}
