import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon1600 {

    public static int K, W, H;
    public static int[][] map;
    public static boolean[][][] visited; // [y][x][말 이동 횟수]

    public static int[] horseDx = { 2, 1, -1, -2, -2, -1, 1, 2 };
    public static int[] horseDy = { 1, 2, 2, 1, -1, -2, -2, -1 };
    public static int[] monkeyDx = { 1, -1, 0, 0 };
    public static int[] monkeyDy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine()); // 말처럼 움직일 수 있는 횟수
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken()); // 가로 길이
        H = Integer.parseInt(st.nextToken()); // 세로 길이

        map = new int[H][W];
        visited = new boolean[H][W][K + 1];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // BFS 실행하여 최소 이동 횟수 출력
        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Position2D> queue = new ArrayDeque<>();
        queue.offer(new Position2D(0, 0, 0, 0)); // 시작점 (0,0)에서 이동 시작 (x, y, 말이동횟수, 거리)
        visited[0][0][0] = true; // (0,0)에서 말 이동 없이 시작

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            // 목표 지점 도착 시, 이동 횟수 반환
            if (curr.x == W - 1 && curr.y == H - 1) {
                return curr.dist;
            }

            // 1️⃣ 원숭이 이동 (상, 하, 좌, 우)
            for (int i = 0; i < 4; i++) {
                int newX = curr.x + monkeyDx[i];
                int newY = curr.y + monkeyDy[i];

                if (isInBound(newX, newY) && map[newY][newX] == 0 && !visited[newY][newX][curr.horseMoves]) {
                    visited[newY][newX][curr.horseMoves] = true;
                    queue.offer(new Position2D(newX, newY, curr.horseMoves, curr.dist + 1));
                }
            }

            // 2️⃣ 말처럼 이동 (8방향) - K번까지 가능
            if (curr.horseMoves < K) {
                for (int i = 0; i < 8; i++) {
                    int newX = curr.x + horseDx[i];
                    int newY = curr.y + horseDy[i];

                    if (isInBound(newX, newY) && map[newY][newX] == 0 && !visited[newY][newX][curr.horseMoves + 1]) {
                        visited[newY][newX][curr.horseMoves + 1] = true;
                        queue.offer(new Position2D(newX, newY, curr.horseMoves + 1, curr.dist + 1));
                    }
                }
            }
        }

        // 도착 불가능한 경우 -1 반환
        return -1;
    }

    public static boolean isInBound(int x, int y) {
        return 0 <= x && x < W && 0 <= y && y < H;
    }

    // 좌표 및 상태 저장을 위한 클래스
    public static class Position2D {
        int x, y, horseMoves, dist;

        public Position2D(int x, int y, int horseMoves, int dist) {
            this.x = x;
            this.y = y;
            this.horseMoves = horseMoves;
            this.dist = dist;
        }
    }
}
