import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon22352 {

    public static int[][] origin;
    public static int[][] mutation;
    public static boolean[][] visited;
    public static int[] dx = { 1, -1, 0, 0 };
    public static int[] dy = { 0, 0, -1, 1 };
    public static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        origin = new int[N][M];
        mutation = new int[N][M];
        visited = new boolean[N][M];

        Position2D diffPosition = null;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean isFound = false;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                mutation[i][j] = Integer.parseInt(st.nextToken());

                if (!isFound && mutation[i][j] != origin[i][j]) {
                    diffPosition = new Position2D(j, i, origin[i][j]);
                    isFound = true;
                }
            }
        }

        if (diffPosition != null) {
            solution(diffPosition);
        }

        System.out.println("--- origin ---");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(origin[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        System.out.println("--- mutation ---");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(mutation[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println();

        System.out.println(answer());
    }

    public static void solution(Position2D diffPosition) {

        if (!isDifferent(diffPosition.x, diffPosition.y))
            return;

        Queue<Position2D> queue = new ArrayDeque<>();
        visited[diffPosition.y][diffPosition.x] = true;
        queue.offer(diffPosition);

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = curr.x + dx[i];
                int newY = curr.y + dy[i];

                if (isInBound(newX, newY) && !visited[newY][newX] && mutation[newY][newX] == diffPosition.dept) {
                    visited[newY][newX] = true;
                    queue.offer(new Position2D(newX, newY, curr.dept + 1));
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) {
                    mutation[i][j] = diffPosition.dept;
                }
            }
        }
    }

    public static String answer() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (origin[i][j] != mutation[i][j]) {
                    return "NO";
                }
            }
        }
        return "YES";
    }

    public static boolean isInBound(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }

    public static boolean isDifferent(int diffX, int diffY) {
        return diffX != -1 && diffY != -1;
    }

    public static class Position2D {
        int x, y, dept;

        public Position2D(int x, int y, int dept) {
            this.x = x;
            this.y = y;
            this.dept = dept;
        }
    }
}
