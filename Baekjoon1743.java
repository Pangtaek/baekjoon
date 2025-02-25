import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon1743 {

    public static int N;
    public static int M;
    public static int K;

    public static boolean[][] corridor;
    public static boolean[][] visited;
    public static int[] dx = { 1, -1, 0, 0 };
    public static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        K = Integer.parseInt(st.nextToken()); // 쓰레기의 개수

        corridor = new boolean[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int col = Integer.parseInt(st.nextToken()) - 1;
            int row = Integer.parseInt(st.nextToken()) - 1;

            corridor[col][row] = true;
        }

        int maximumSize = solution();

        System.out.println(maximumSize);
    }

    public static int solution() {
        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && corridor[i][j]) {
                    answer = Math.max(answer, bfs(j, i));
                }
            }
        }

        return answer;
    }

    public static int bfs(int row, int col) {
        int answer = 1;
        
        Queue<Position2D> queue = new ArrayDeque<>();

        visited[col][row] = true;
        queue.offer(new Position2D(row, col));

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = curr.x + dx[i];
                int newY = curr.y + dy[i];

                boolean flag = isInBound(newX, newY) && !visited[newY][newX] && corridor[newY][newX];

                if (flag) {
                    answer++;
                    visited[newY][newX] = true;
                    queue.offer(new Position2D(newX, newY));
                }
            }
        }

        return answer;
    }

    public static boolean isInBound(int newX, int newY) {
        return 0 <= newX && newX < M && 0 <= newY && newY < N;
    }

    public static class Position2D {
        int x, y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
