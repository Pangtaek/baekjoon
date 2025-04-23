import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Baekjoon6087 {
    public static int[] dx = { -1, 1, 0, 0 };
    public static int[] dy = { 0, 0, -1, 1 };
    public static int W;
    public static int H;
    public static char[][] map;
    public static int[][][] dist;
    public static Point start;
    public static Point end;

    public static class Point {
        public int x;
        public int y;
        public int dir;
        public int mirrorCount;

        public Point(int x, int y, int dir, int mirrorCount) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.mirrorCount = mirrorCount;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tokens = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        W = tokens[0]; // 가로
        H = tokens[1]; // 세로

        map = new char[H][W]; // 지도
        dist = new int[H][W][4];

        int countC = 0;
        for (int y = 0; y < H; y++) {
            String input = br.readLine();
            for (int x = 0; x < W; x++) {
                map[y][x] = input.charAt(x);

                if (map[y][x] == 'C') {
                    if (countC == 0) {
                        start = new Point(x, y, -1, 0);
                        countC++;
                    } else {
                        end = new Point(x, y, -1, 0);
                    }
                }
            }
        }

        // 거리 배열 초기화
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                Arrays.fill(dist[y][x], Integer.MAX_VALUE);
            }
        }

        bfs();

        int answer = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            answer = Math.min(answer, dist[end.y][end.x][d]);
        }

        System.out.println(answer);
    }

    public static void bfs() {
        Deque<Point> deque = new ArrayDeque<>();

        for (int d = 0; d < 4; d++) {
            dist[start.y][start.x][d] = 0;
            deque.offer(new Point(start.x, start.y, d, 0));
        }

        while (!deque.isEmpty()) {
            Point curr = deque.poll();

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                // 배열 범위 확인
                if (ny < 0 || ny >= H || nx < 0 || nx >= W)
                    continue;

                // 벽 여부 확인
                if (map[ny][nx] == '*')
                    continue;

                // 반사판 설치 여부 확인
                int newMirrorCount = curr.mirrorCount + (curr.dir == d ? 0 : 1);

                if (dist[ny][nx][d] > newMirrorCount) {
                    dist[ny][nx][d] = newMirrorCount;
                    Point next = new Point(nx, ny, d, newMirrorCount);

                    // 기존 방향 유지
                    if (curr.dir == d) {
                        deque.offerFirst(next);
                    } else { // 반사판을 이용해서 방향 변경
                        deque.offerLast(next);
                    }
                }
            }
        }
    }
}
