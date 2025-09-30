import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;

/*
 * 백준 17836 공주님을 구해라!
 * https://www.acmicpc.net/problem/17836
 * 풀이법: BFS
 */
public class Baekjoon17836 {

    private static final int[][] DIRECTIONS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    private static int N, M, T;
    private static int[][] map;
    private static int[][][] visited; // 3차원 배열로 변경

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            N = input[0];
            M = input[1];
            T = input[2];

            map = new int[N][M];

            for (int row = 0; row < N; row++) {
                map[row] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            // 3차원 visited 배열 초기화 [x][y][검보유여부]
            visited = new int[N][M][2];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    Arrays.fill(visited[i][j], Integer.MAX_VALUE);
                }
            }

            int result = bfs();

            if (result == -1 || result > T) {
                bw.write("Fail");
            } else {
                bw.write(String.valueOf(result));
            }
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    private static int bfs() {
        ArrayDeque<Hero> queue = new ArrayDeque<>();
        queue.offer(new Hero(0, 0, 0, false));
        visited[0][0][0] = 0; // 검 없는 상태로 시작

        while (!queue.isEmpty()) {
            Hero current = queue.poll();
            int x = current.getX();
            int y = current.getY();
            int time = current.getTime();
            boolean hasSword = current.isHasSword();

            // 시간 제한 초과 시 스킵
            if (time > T) {
                continue;
            }

            // 목표 지점 도달
            if (x == N - 1 && y == M - 1) {
                return time;
            }

            for (int[] direction : DIRECTIONS) {
                int nextX = x + direction[0];
                int nextY = y + direction[1];
                int nextTime = time + 1;

                if (!isInRange(nextX, nextY) || nextTime > T) {
                    continue;
                }

                // 검을 가진 상태: 모든 칸 이동 가능
                if (hasSword) {
                    if (visited[nextX][nextY][1] > nextTime) {
                        visited[nextX][nextY][1] = nextTime;
                        queue.offer(new Hero(nextX, nextY, nextTime, true));
                    }
                }
                // 검을 가지지 않은 상태: 벽 통과 불가
                else {
                    if (map[nextX][nextY] != 1) { // 벽이 아닌 경우만
                        boolean newSword = (map[nextX][nextY] == 2); // 검 획득 여부
                        int swordState = newSword ? 1 : 0;

                        if (visited[nextX][nextY][swordState] > nextTime) {
                            visited[nextX][nextY][swordState] = nextTime;
                            queue.offer(new Hero(nextX, nextY, nextTime, newSword));
                        }
                    }
                }
            }
        }

        // 목표 지점에 도달한 최소 시간 반환
        int result = Math.min(visited[N - 1][M - 1][0], visited[N - 1][M - 1][1]);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private static class Position2D {
        private int x;
        private int y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private static class Hero extends Position2D {
        private int time;
        private boolean hasSword;

        public Hero(int x, int y, int time, boolean hasSword) {
            super(x, y);
            this.time = time;
            this.hasSword = hasSword;
        }

        public int getTime() {
            return time;
        }

        public boolean isHasSword() {
            return hasSword;
        }
    }
}
