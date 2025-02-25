import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon22352 {

    public static int[][] before;
    public static int[][] after;
    public static boolean[][] visited;
    public static int[] dx = { 1, -1, 0, 0 };
    public static int[] dy = { 0, 0, 1, -1 };

    public static int N;
    public static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        before = new int[N][M];
        after = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                before[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Position2D diffPosition = null;
        int originalValue = -1;
        int newValue = -1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                after[i][j] = Integer.parseInt(st.nextToken());

                if (diffPosition == null && before[i][j] != after[i][j]) {
                    diffPosition = new Position2D(j, i);
                    originalValue = before[i][j]; 
                    newValue = after[i][j];
                }
            }
        }

        // 변경된 부분이 있다면 BFS 실행
        if (diffPosition != null) {
            solution(diffPosition, originalValue, newValue);
        }

        // 최종 비교 후 출력
        System.out.println(Arrays.deepEquals(before, after) ? "YES" : "NO");
    }

    public static void solution(Position2D diffPosition, int originalValue, int newValue) {
        Queue<Position2D> queue = new ArrayDeque<>();
        visited[diffPosition.y][diffPosition.x] = true;
        queue.offer(diffPosition);

        before[diffPosition.y][diffPosition.x] = newValue;

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = curr.x + dx[i];
                int newY = curr.y + dy[i];

                if (isInBound(newX, newY) && !visited[newY][newX] && before[newY][newX] == originalValue) {
                    visited[newY][newX] = true;
                    before[newY][newX] = newValue; 
                    queue.offer(new Position2D(newX, newY));
                }
            }
        }
    }

    public static boolean isInBound(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }

    public static class Position2D {
        int x, y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
