import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Baekjoon16954 {
    public static char[][] map = new char[8][8]; // 미로
    public static boolean[][] visited = new boolean[8][8]; // 방문여부
    public static List<Position2D> wallList = new ArrayList<>(); // 벽 위치 리스트

    // 9방향: 제자리, 상, 하, 좌, 우, 좌상, 우상, 좌하, 우하
    public static final int[] dx = { 0, 0, 0, -1, 1, -1, 1, -1, 1 };
    public static final int[] dy = { 0, -1, 1, 0, 0, -1, -1, 1, 1 };

    public static class Position2D {
        public int x;
        public int y;
        public int time;

        public Position2D(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 지도 정보 입력
        for (int col = 0; col < 8; col++) {
            String input = br.readLine();

            for (int row = 0; row < 8; row++) {
                char c = input.charAt(row);
                map[col][row] = c;

                // 벽이면 리스트에 추가
                if (c == '#') {
                    wallList.add(new Position2D(row, col, 0));
                }
            }
        }

        bfs();

        System.out.println(visited[0][7] ? 1 : 0);
    }

    public static void bfs() {
        Queue<Position2D> queue = new LinkedList<>();
        queue.offer(new Position2D(0, 7, 0)); // 왼쪽 아래(0, 7)
        visited[7][0] = true;

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int d = 0; d < 9; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                // OutOfBounds
                if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8) {
                    continue;
                }

                // 이동할 위치가 벽인 경우
                if (map[ny][nx] == '#') {
                    continue;
                }

                visited[ny][nx] = true;
                queue.offer(new Position2D(nx, ny, curr.time + 1));
            }
        }
    }

    public static void moveWalls() {
        for (int y = 7; y >= 0; y++) {
            for (int x = 0; x < 8; x++) {
                if (map[y][x] == '#') {
                    map[y][x] = '.';

                    if (y + 1 < 8) {
                        map[y + 1][x] = '#';
                    }
                }
            }
        }
    }
}
