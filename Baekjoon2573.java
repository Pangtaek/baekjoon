import java.util.*;
import java.io.*;

public class Baekjoon2573 {

    public static int[][] matrix;
    public static final int[] dx = { 1, -1, 0, 0 };
    public static final int[] dy = { 0, 0, 1, -1 };
    public static Queue<Position2D> queue = new LinkedList<>();
    public static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 행(y), 열(x) 값 입력 받기
        int[] size = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rows = size[0];
        int cols = size[1];

        // 배열 초기화
        matrix = new int[rows][cols];
        visited = new boolean[rows][cols];

        // 배열에 값 넣기
        for (int i = 0; i < rows; i++) {
            matrix[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int answer = 0;

        // 시간이 지나며 빙하 개수 확인
        while (true) {
            int glacierCount = solution();
            if (glacierCount == 0) {
                answer = 0; // 모든 빙하가 녹아서 분리될 수 없음
                break;
            }
            if (glacierCount >= 2) {
                break;
            }
            answer++;
        }

        System.out.println(answer);
    }

    // 빙하 개수 세기
    public static int solution() {
        int count = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;

        visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != 0 && !visited[i][j]) {
                    bfs(j, i);
                    count++;
                }
            }
        }

        meltGlacier(); // 빙하 녹이기

        return count;
    }

    // 빙하 녹이는 메서드
    public static void meltGlacier() {
        int[][] temp = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0)
                    continue;

                int meltCount = 0;
                for (int k = 0; k < 4; k++) {
                    int newX = j + dx[k]; // ✅ `i` → `k`
                    int newY = i + dy[k]; // ✅ `i` → `k`

                    if (isValid(newX, newY) && matrix[newY][newX] == 0) {
                        meltCount++;
                    }
                }
                temp[i][j] = Math.max(matrix[i][j] - meltCount, 0);
            }
        }

        matrix = temp; // 빙하 녹인 후 새로운 배열 업데이트
    }

    // BFS 실행 메서드
    public static void bfs(int x, int y) {
        visited[y][x] = true;
        queue.offer(new Position2D(x, y));

        while (!queue.isEmpty()) {
            Position2D current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];

                if (isValid(newX, newY) && matrix[newY][newX] != 0 && !visited[newY][newX]) {
                    visited[newY][newX] = true;
                    queue.offer(new Position2D(newX, newY));
                }
            }
        }
    }

    // 배열 범위 확인 메서드
    public static boolean isValid(int x, int y) {
        return 0 <= x && x < matrix[0].length
                && 0 <= y && y < matrix.length;
    }

    public static class Position2D {
        public int x, y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
