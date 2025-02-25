import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon3184 {

    public static char[][] field;
    public static boolean[][] visited;
    public static int[] dx = { 1, -1, 0, 0 };
    public static int[] dy = { 0, 0, 1, -1 };

    public static int R;
    public static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        field = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                field[i][j] = line.charAt(j);
            }
        }

        int[] answer = solution();
        System.out.println(answer[0] + " " + answer[1]);
    }

    public static int[] solution() {
        int sheep = 0, wolf = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && (field[i][j] == 'o' || field[i][j] == 'v')) {
                    int[] result = bfs(j, i);
                    sheep += result[0];
                    wolf += result[1];
                }
            }
        }
        return new int[] { sheep, wolf };
    }

    public static int[] bfs(int x, int y) {
        Queue<Position2D> queue = new ArrayDeque<>();
        visited[y][x] = true;
        queue.offer(new Position2D(x, y));

        int sheepCount = 0, wolfCount = 0;

        if (field[y][x] == 'o')
            sheepCount++;
        if (field[y][x] == 'v')
            wolfCount++;

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = curr.x + dx[i];
                int newY = curr.y + dy[i];

                if (isInBound(newX, newY) && !visited[newY][newX] && field[newY][newX] != '#') {
                    visited[newY][newX] = true;
                    queue.offer(new Position2D(newX, newY));

                    if (field[newY][newX] == 'o')
                        sheepCount++;
                    if (field[newY][newX] == 'v')
                        wolfCount++;
                }
            }
        }

        if (sheepCount > wolfCount)
            wolfCount = 0;
        else
            sheepCount = 0;

        return new int[] { sheepCount, wolfCount };
    }

    public static boolean isInBound(int x, int y) {
        return 0 <= x && x < C && 0 <= y && y < R;
    }

    public static class Position2D {
        int x, y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
