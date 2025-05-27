import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon16929 {
    static final int[][] DIRECTIONS = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static boolean hasCycle = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = tokens[0];
        M = tokens[1];

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        visited = new boolean[N][M];

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (!visited[y][x]) {
                    dfs(y, x, -1, -1, map[y][x]);
                }
            }
        }

        bw.write(hasCycle ? "Yes" : "No");
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int y, int x, int py, int px, char color) {
        visited[y][x] = true;

        for (int[] d : DIRECTIONS) {
            int ny = y + d[0];
            int nx = x + d[1];

            if (ny < 0 || nx < 0 || ny >= N || nx >= M)
                continue;

            if (map[ny][nx] != color)
                continue;

            // 직전 위치로 돌아가는 것은 무시
            if (ny == py && nx == px)
                continue;

            if (visited[ny][nx]) {
                hasCycle = true;
                return;
            }

            dfs(ny, nx, y, x, color);
            
            if (hasCycle)
                return; // 최적화: 사이클 발견 시 종료
        }
    }
}
