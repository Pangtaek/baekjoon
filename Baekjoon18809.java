import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Baekjoon18809 {
    public static int N, M, G, R;
    public static int[][] field;
    public static List<Position2D> availableSpots = new ArrayList<>();

    public static final int[] dx = { 1, -1, 0, 0 };
    public static final int[] dy = { 0, 0, 1, -1 };

    public static final int EMPTY = 0;
    public static final int GREEN = 1;
    public static final int RED = 2;

    public static int maxFlowers = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];
        G = input[2];
        R = input[3];

        field = new int[N][M];
        for (int i = 0; i < N; i++) {
            int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < M; j++) {
                field[i][j] = row[j];
                if (row[j] == 2) {
                    availableSpots.add(new Position2D(j, i));
                }
            }
        }

        int[] selected = new int[availableSpots.size()];
        dfs(0, 0, 0, selected);
        System.out.println(maxFlowers);
    }

    public static void dfs(int depth, int green, int red, int[] selected) {
        if (green == G && red == R) {
            maxFlowers = Math.max(maxFlowers, bfs(selected));
            return;
        }

        if (depth == availableSpots.size())
            return;

        // 초록색 배양액 뿌리기
        if (green < G) {
            selected[depth] = GREEN;
            dfs(depth + 1, green + 1, red, selected);
            selected[depth] = EMPTY;
        }

        // 빨간색 배양액 뿌리기
        if (red < R) {
            selected[depth] = RED;
            dfs(depth + 1, green, red + 1, selected);
            selected[depth] = EMPTY;
        }

        // 아무것도 안 뿌리기
        selected[depth] = EMPTY;
        dfs(depth + 1, green, red, selected);
    }

    public static int bfs(int[] selected) {
        int[][] time = new int[N][M];
        char[][] color = new char[N][M];
        Queue<Position2D> queue = new ArrayDeque<>();

        for (int i = 0; i < selected.length; i++) {
            if (selected[i] != EMPTY) {
                Position2D pos = availableSpots.get(i);
                char c = selected[i] == GREEN ? 'G' : 'R';
                color[pos.y][pos.x] = c;
                time[pos.y][pos.x] = 1;
                queue.offer(new Position2D(pos.x, pos.y, c, 1));
            }
        }

        int flowers = 0;
        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            if (color[curr.y][curr.x] == 'F')
                continue;

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if (!isInBound(nx, ny))
                    continue;
                if (field[ny][nx] == 0)
                    continue;

                if (color[ny][nx] == 0) {
                    color[ny][nx] = curr.color;
                    time[ny][nx] = curr.time + 1;
                    queue.offer(new Position2D(nx, ny, curr.color, curr.time + 1));
                } else if ((color[ny][nx] == 'G' || color[ny][nx] == 'R')
                        && color[ny][nx] != curr.color
                        && time[ny][nx] == curr.time + 1) {
                    color[ny][nx] = 'F';
                    flowers++;
            }
        }

        return flowers;
    }

    public static boolean isInBound(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }

    public static class Position2D {
        int x, y, time;
        char color;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position2D(int x, int y, char color, int time) {
            this.x = x;
            this.y = y;
            this.color = color;
            this.time = time;
        }
    }
}
