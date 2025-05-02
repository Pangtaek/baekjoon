import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Baekjoon2931 {
    public static final int[] dx = { 0, 0, -1, 1 }; // 상, 하, 좌, 우
    public static final int[] dy = { -1, 1, 0, 0 };

    public static class Position2D {
        public int x, y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int R = tokens[0];
        int C = tokens[1];

        char[][] map = new char[R][C];
        Position2D[] spots = new Position2D[2]; // [0] = M, [1] = Z

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = input.charAt(j);
                if (c == 'M')
                    spots[0] = new Position2D(j, i);
                else if (c == 'Z')
                    spots[1] = new Position2D(j, i);
                map[i][j] = c;
            }
        }

        System.out.println(solution(R, C, map, spots));
    }

    public static StringBuilder solution(int R, int C, char[][] map, Position2D[] spots) {
        StringBuilder answer = new StringBuilder();

        // 해커가 지운 위치 찾기
        Position2D missedPosition = findMissedPosition(map, spots[0]);
        
        answer.append(missedPosition.y + 1).append(" ").append(missedPosition.x + 1).append(" ");

        return answer;
    }

    public static Position2D findMissedPosition(char[][] map, Position2D start) {
        Queue<Position2D> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        queue.offer(new Position2D(start.x, start.y));
        visited[start.y][start.x] = true;

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if (nx < 0 || nx >= map[0].length || ny < 0 || ny >= map.length)
                    continue;
                    
                if (visited[ny][nx])
                    continue;

                if (!isConnected(map[curr.y][curr.x], d))
                    continue;

                char next = map[ny][nx];

                if (next == '.') {
                    // 연결된 방향 수 세기
                    int count = 0;
                    for (int dd = 0; dd < 4; dd++) {
                        int nnx = nx + dx[dd];
                        int nny = ny + dy[dd];
                        if (nnx < 0 || nnx >= map[0].length || nny < 0 || nny >= map.length)
                            continue;
                        if (isConnected(map[nny][nnx], reverseDirection(dd))) {
                            count++;
                        }
                    }

                    if (count >= 2) {
                        return new Position2D(nx, ny);
                    }

                    // continue로 다음 방향 계속 보기
                    continue;
                }

                if (isConnected(next, reverseDirection(d))) {
                    visited[ny][nx] = true;
                    queue.offer(new Position2D(nx, ny));
                }
            }
        }

        return null;
    }

    public static boolean isConnected(char pipe, int dir) {
        switch (pipe) {
            case '|':
                return dir == 0 || dir == 1; // 상, 하
            case '-':
                return dir == 2 || dir == 3; // 좌, 우
            case '+':
                return true; // 4방향
            case '1':
                return dir == 1 || dir == 3; // 하, 우 (┌)
            case '2':
                return dir == 0 || dir == 3; // 상, 우 (└)
            case '3':
                return dir == 0 || dir == 2; // 상, 좌 (┘)
            case '4':
                return dir == 1 || dir == 2; // 하, 좌 (┐)
            case 'M':
            case 'Z':
                return true;
            default:
                return false;
        }
    }

    // 현재 방향의 반대 방향 반환
    public static int reverseDirection(int dir) {
        switch (dir) {
            case 0:
                return 1; // 상 → 하
            case 1:
                return 0; // 하 → 상
            case 2:
                return 3; // 좌 → 우
            case 3:
                return 2; // 우 → 좌
            default:
                return -1; // 잘못된 값
        }
    }
}
