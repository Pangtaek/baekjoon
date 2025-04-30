import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon16954 {
    static char[][] map = new char[8][8];
    static final int[] dx = { 0, 0, 0, -1, 1, -1, 1, -1, 1 };
    static final int[] dy = { 0, -1, 1, 0, 0, -1, -1, 1, 1 };

    static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int y = 0; y < 8; y++) {
            String line = br.readLine();
            for (int x = 0; x < 8; x++) {
                map[y][x] = line.charAt(x);
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(7, 0)); // (y=7, x=0)

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean[][] visited = new boolean[8][8]; // 턴마다 visited 초기화

            for (int i = 0; i < size; i++) {
                Position curr = queue.poll();

                if (map[curr.x][curr.y] == '#')
                    continue; // 현재 위치에 벽이 오면 사망

                if (curr.x == 0 && curr.y == 7)
                    return 1; // 목표 도달

                for (int d = 0; d < 9; d++) {
                    int nx = curr.x + dx[d];
                    int ny = curr.y + dy[d];

                    if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8)
                        continue; // ArrayIndexOfBounds

                    if (map[nx][ny] == '#')
                        continue; // 다음 위치에 벽이 오면 사망

                    if (visited[nx][ny])
                        continue; // 이미 방문했으면 패스

                    visited[nx][ny] = true;
                    queue.offer(new Position(nx, ny));
                }
            }

            moveWall(); // 턴마다 벽 내려옴
        }

        return 0; // 탈출 불가
    }

    public static void moveWall() {
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                if (map[y][x] == '#') {
                    map[y][x] = '.';

                    if (y + 1 < 8) {
                        map[y + 1][x] = '#';
                    }
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            map[0][i] = '.';
        }
    }
}
