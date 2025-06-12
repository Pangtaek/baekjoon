import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon1987 {

    static final int[][] DIRECTIONS = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } }; // 상, 하, 좌, 우

    static int R;
    static int C;
    static char[][] board;
    static boolean[] visited = new boolean[26];
    static int count = 1;

    static class Position2D {
        int x;
        int y;

        Position2D(int x, int y) {
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
        R = tokens[0];
        C = tokens[1];
        board = new char[R][C];

        for (int row = 0; row < R; row++) {
            char[] chars = br.readLine().toCharArray();
            for (int column = 0; column < C; column++) {
                board[row][column] = chars[column];
            }
        }

        visited[board[0][0] - 'A'] = true;
        dfs(new Position2D(0, 0), 1);

        bw.write(Integer.toString(count));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(Position2D pos, int depth) {
        count = Math.max(count, depth);

        for (int[] d : DIRECTIONS) {
            int nx = pos.x + d[0];
            int ny = pos.y + d[1];

            if (nx < 0 || nx >= C || ny < 0 || ny >= R)
                continue;

            int alphaIndex = board[ny][nx] - 'A';
            if (!visited[alphaIndex]) {
                visited[alphaIndex] = true;
                dfs(new Position2D(nx, ny), depth + 1);
                visited[alphaIndex] = false;
            }
        }
    }
}