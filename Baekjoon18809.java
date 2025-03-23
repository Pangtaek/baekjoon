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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        N = input[0];
        M = input[1];
        G = input[2];
        R = input[3];

        field = new int[N][M];

        for (int i = 0; i < N; i++) {
            int[] row = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < M; j++) {
                field[i][j] = row[j];
                if (field[i][j] == 2) {
                    availableSpots.add(new Position2D(j, i));
                }
            }
        }

        Character[] selected = new Character[availableSpots.size()];
        int result = dfs(0, 0, 0, selected, 0);
        System.out.println(result);
    }

    public static int dfs(int depth, int green, int red, Character[] selected, int maximum) {
        if (green == G && red == R) {
            return Math.max(maximum, bfs(selected));
        }

        if (depth == availableSpots.size())
            return maximum;

        int result = maximum;

        if (green < G) {
            selected[depth] = 'G';
            result = Math.max(result, dfs(depth + 1, green + 1, red, selected, result));
            selected[depth] = null;
        }

        if (red < R) {
            selected[depth] = 'R';
            result = Math.max(result, dfs(depth + 1, green, red + 1, selected, result));
            selected[depth] = null;
        }

        result = Math.max(result, dfs(depth + 1, green, red, selected, result));

        return result;
    }

    public static int bfs(Character[] selected) {
        int[][] time = new int[N][M];
        char[][] color = new char[N][M];
        Queue<Field> queue = new ArrayDeque<>();

        for (int i = 0; i < selected.length; i++) {
            if (selected[i] != null) {
                Position2D pos = availableSpots.get(i);
                color[pos.y][pos.x] = selected[i];
                time[pos.y][pos.x] = 1;
                queue.offer(new Field(pos.x, pos.y, 1, selected[i]));
            }
        }

        int flowers = 0;
        while (!queue.isEmpty()) {
            Field curr = queue.poll();

            if (color[curr.y][curr.x] == 'F')
                continue;

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if (!isInRange(nx, ny))
                    continue;
                if (field[ny][nx] == 0)
                    continue;

                if (color[ny][nx] == 0) {
                    color[ny][nx] = curr.color;
                    time[ny][nx] = curr.time + 1;
                    queue.offer(new Field(nx, ny, curr.time + 1, curr.color));
                } else if ((color[ny][nx] == 'G' || color[ny][nx] == 'R') && color[ny][nx] != curr.color
                        && time[ny][nx] == curr.time + 1) {
                    color[ny][nx] = 'F';
                    flowers++;
                }
            }
        }

        return flowers;
    }

    public static boolean isInRange(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }

    public static class Position2D {
        public int x, y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Field extends Position2D {
        public int time;
        public char color;

        public Field(int x, int y, int time, char color) {
            super(x, y);
            this.time = time;
            this.color = color;
        }
    }
}