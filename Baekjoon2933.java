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

public class Baekjoon2933 {

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

        List<Position2D> mineralList = new ArrayList<>();

        // 미네랄이 시작하는 좌표를 찾는다.
        for (int i = 0; i < C; i++) {
            if (cave[R - 1][i] == 'x') {
                mineralList.add(new Position2D(i, R - 1));
            }
        }

        boolean[][] visited = new boolean[R][C];
        Queue<Position2D> queue = new ArrayDeque<>();
        for (int i = 0; i < mineralList.size(); i++) {
            Position2D pos = mineralList.get(i);
            visited[pos.y][pos.x] = true;
            queue.offer(pos);
        }

        bfs(queue, cave, visited);

        List<Position2D> clusterList = new ArrayList<>();
        for (int row = 0; row < R; row++) {
            for (int column = 0; column < C; column++) {
                if (cave[row][column] == 'x' && !visited[row][column]) { // 공중에 미네랄이 떠있는 경우
                    clusterList.add(new Position2D(column, row));
                }
            }
        }

        if (!clusterList.isEmpty()) {
            dropMineral(cave, clusterList);
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

                if (nx < 0 || nx >= C || ny < 0 || ny >= R) { // 배열 범위를 초과한 경우
                    continue;
                }

                if (cave[ny][nx] != 'x') { // 미네랄이 아닌 경우
                    continue;
                }

                if (visited[ny][nx]) { // 이미 방문한 경우
                    continue;
                }

                visited[ny][nx] = true;
                queue.offer(new Position2D(nx, ny));
            }
        }
    }

    /** 공중에 떠 있는 미네랄 클러스터(clusterList)를 중력 방향(아래)으로 떨어뜨린다. */
    private static void dropMineral(char[][] cave, List<Position2D> clusterList) {
        int R = cave.length;
        // int C = cave[0].length;

        /* 1) 클러스터 블록을 잠시 제거해 충돌 판단을 쉽게 만든다. */
        for (Position2D p : clusterList) {
            cave[p.y][p.x] = '.';
        }

        /* 2) 클러스터 전체가 떨어질 수 있는 최소 거리(minFall) 계산 */
        int minFall = R; // 충분히 큰 값으로 시작
        for (Position2D cluster : clusterList) {
            int ny = cluster.y + 1; // 바로 아래칸부터 검사
            int fall = 0;
            while (ny < R && cave[ny][cluster.x] == '.') {
                fall++;
                ny++;
            }
            minFall = Math.min(minFall, fall); // 모든 블록 중 가장 작은 거리
        }

        for (Position2D cluster : clusterList) {
            cave[cluster.y + minFall][cluster.x] = 'x';
        }
    }
}