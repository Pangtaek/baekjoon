import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Main {

    private static int[][] DIRECTIONS = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } }; // 상, 하, 좌, 우

    private static class Position2D {
        private int x;
        private int y;

        private Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int R = tokens[0]; // 행
        int C = tokens[1]; // 열
        char[][] cave = new char[R][C]; // '.'는 빈 칸, 'x'는 미네랄을 나타낸다.

        for (int row = 0; row < R; row++) {
            cave[row] = br.readLine().toCharArray();
        }

        int N = Integer.parseInt(br.readLine()); // 막대를 던진 횟수
        int[] H = new int[N];
        H = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray(); // 막대를 던진 높이

        for (int i = 0; i < H.length; i++) {
            int y = R - H[i];
            int x = -1;

            if (i % 2 == 0) { // 왼쪽에서 막대기 던지는 차례
                for (int j = 0; j < C; j++) {
                    if (cave[y][j] == 'x') {
                        x = j;
                        break;
                    }
                }
            } else { // 오른쪽에서 막대기 던지는 차레
                for (int j = C - 1; j >= 0; j--) {
                    if (cave[y][j] == 'x') {
                        x = j;
                        break;
                    }
                }
            }

            if (x == -1) { // 해당 높이에 미네랑이 존재하지 않는 경우
                continue; // 다음 턴
            }

            cave[y][x] = '.';

            bfsHandler(cave);
        }

        // cave 상태 출력
        for (int row = 0; row < R; row++) {
            for (int column = 0; column < C; column++) {
                bw.write(cave[row][column]);
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfsHandler(char[][] cave) {
        int R = cave.length;
        int C = cave[0].length;

        List<Position2D> groundMinerals = new ArrayList<>();

        // 미네랄이 시작하는 좌표를 찾는다.
        for (int i = 0; i < C; i++) {
            if (cave[R - 1][i] == 'x') {
                groundMinerals.add(new Position2D(i, R - 1));
            }
        }

        boolean[][] visited = new boolean[R][C];
        Queue<Position2D> queue = new ArrayDeque<>();
        for (int i = 0; i < groundMinerals.size(); i++) {
            Position2D pos = groundMinerals.get(i);
            visited[pos.y][pos.x] = true;
            queue.offer(pos);
        }

        bfs(queue, cave, visited);

        List<Position2D> floatingCluster = new ArrayList<>();
        for (int row = 0; row < R; row++) {
            for (int column = 0; column < C; column++) {
                if (cave[row][column] == 'x' && !visited[row][column]) { // 공중에 미네랄이 떠있는 경우
                    floatingCluster.add(new Position2D(column, row));
                }
            }
        }

        if (!floatingCluster.isEmpty()) {
            dropFloatingCluster(cave, floatingCluster);
        }
    }

    private static void bfs(Queue<Position2D> queue, char[][] cave, boolean[][] visited) {
        int R = cave.length;
        int C = cave[0].length;

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int[] d : DIRECTIONS) {
                int nx = curr.x + d[0];
                int ny = curr.y + d[1];

                boolean flag = (nx < 0 || nx >= C || ny < 0 || ny >= R) // 동굴 범위를 초과한 경우
                        || cave[ny][nx] != 'x' // 미네랄이 아닌 경우
                        || visited[ny][nx]; // 이미 방문한 경우

                if (flag) {
                    continue;
                }

                visited[ny][nx] = true;
                queue.offer(new Position2D(nx, ny));
            }
        }
    }

    private static void dropFloatingCluster(char[][] cave, List<Position2D> floatingCluster) {
        int R = cave.length;

        // 1. 클러스터 블록을 cave에서 제거
        for (Position2D p : floatingCluster) {
            cave[p.y][p.x] = '.';
        }

        // 2. 최소 낙하 거리 계산
        int minFall = R;
        for (Position2D p : floatingCluster) {
            int nx = p.x;
            int ny = p.y + 1;
            int fall = 0;

            while (ny < R && cave[ny][nx] == '.') {
                fall++;
                ny++;
            }

            // cave[ny][nx]가 떠있는 클러스터에 포함되어 있다면 그건 충돌 아님
            boolean isConflict = true;
            for (Position2D other : floatingCluster) {
                if (other.x == nx && other.y == ny) {
                    isConflict = false;
                    break;
                }
            }

            if (isConflict) {
                minFall = Math.min(minFall, fall);
            }
        }

        // 3. y 큰 순서대로 다시 cave에 삽입 (덮어쓰기 방지)
        floatingCluster.sort((a, b) -> b.y - a.y);
        for (Position2D p : floatingCluster) {
            cave[p.y + minFall][p.x] = 'x';
        }
    }
}