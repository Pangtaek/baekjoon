import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon1926 {

    public static int N; // 도화지의 세로의 크기
    public static int M; // 도화지의 가로의 크기

    public static int[][] matrix;
    public static boolean[][] visited;
    public static int[] dx = { 1, -1, 0, 0 };
    public static int[] dy = { 0, 0, 1, -1 };
    public static Queue<Position2D> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] answer = solution();

        for (int i = 0; i < 2; i++) {
            System.out.println(answer[i]);
        }
    }

    public static int[] solution() {
        int[] answer = { 0, 0 };

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                boolean flag = !visited[i][j] && matrix[i][j] == 1; // 방문하지 않고, 값이 1

                if (flag) {
                    answer[1] = Math.max(answer[1], bfs(new Position2D(j, i)));
                    answer[0]++;
                }
            }
        }

        return answer;
    }

    public static int bfs(Position2D curr) {
        int area = 1;

        visited[curr.y][curr.x] = true;
        queue.offer(curr);

        while (!queue.isEmpty()) {
            Position2D currentPositionInQueue = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = currentPositionInQueue.x + dx[i];
                int newY = currentPositionInQueue.y + dy[i];

                boolean flag = isInBound(newX, newY) && !visited[newY][newX] && matrix[newY][newX] == 1; // 배열 범위 안, 미방문, 값이 1
                if (flag) {
                    visited[newY][newX] = true;
                    queue.offer(new Position2D(newX, newY));
                    area++;
                }
            }
        }

        return area;
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