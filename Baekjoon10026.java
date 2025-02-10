import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon10026 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] matrix = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                matrix[i][j] = str.charAt(j);
            }
        }

        System.out.println(getPartition(matrix) + " " + getPartitionByRedGreenColorBlindness(matrix));
    }

    public static int[] dirX = { 1, -1, 0, 0 };
    public static int[] dirY = { 0, 0, 1, -1 };
    public static boolean[][] visited;

    // ✅ 일반인의 구역 개수 계산
    public static int getPartition(char[][] matrix) {
        int N = matrix.length;
        visited = new boolean[N][N]; // ✅ boolean 초기화
        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) { // ✅ 방문하지 않았을 때만 BFS 실행
                    bfs(matrix, i, j);
                    answer++;
                }
            }
        }

        return answer;
    }

    // ✅ 일반 BFS (색 구분)
    public static void bfs(char[][] matrix, int i, int j) {
        Queue<Position2D> queue = new LinkedList<>();
        char color = matrix[i][j];
        visited[i][j] = true; // ✅ 방문 처리
        queue.offer(new Position2D(i, j, color));

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int index = 0; index < 4; index++) {
                int newX = curr.x + dirX[index];
                int newY = curr.y + dirY[index];

                if (isInBound(matrix, newX, newY) && !visited[newX][newY] && matrix[newX][newY] == color) {
                    visited[newX][newY] = true; // ✅ 방문 처리
                    queue.offer(new Position2D(newX, newY, color));
                }
            }
        }
    }

    // ✅ 적록색약인이 보는 구역 개수 계산
    public static int getPartitionByRedGreenColorBlindness(char[][] matrix) {
        int N = matrix.length;
        visited = new boolean[N][N]; // ✅ boolean 초기화
        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) { // ✅ 방문하지 않았을 때만 BFS 실행
                    bfsByRedGreenColorBlindness(matrix, i, j);
                    answer++;
                }
            }
        }

        return answer;
    }

    // ✅ 적록색약 BFS (R == G 동일 처리)
    public static void bfsByRedGreenColorBlindness(char[][] matrix, int i, int j) {
        Queue<Position2D> queue = new LinkedList<>();
        char color = matrix[i][j];
        visited[i][j] = true; // ✅ 방문 처리
        queue.offer(new Position2D(i, j, color));

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int index = 0; index < 4; index++) {
                int newX = curr.x + dirX[index];
                int newY = curr.y + dirY[index];

                if (isInBound(matrix, newX, newY) && !visited[newX][newY]) {
                    if (color == 'B') { // ✅ 'B'는 같은 'B'끼리만 연결
                        if (matrix[newX][newY] == 'B') {
                            visited[newX][newY] = true;
                            queue.offer(new Position2D(newX, newY, 'B'));
                        }
                    } else { // ✅ 'R' 또는 'G'는 동일한 색으로 취급
                        if (matrix[newX][newY] == 'R' || matrix[newX][newY] == 'G') {
                            visited[newX][newY] = true;
                            queue.offer(new Position2D(newX, newY, color));
                        }
                    }
                }
            }
        }
    }

    public static boolean isInBound(char[][] matrix, int x, int y) {
        return 0 <= x && x < matrix.length && 0 <= y && y < matrix.length;
    }

    public static class Position2D {
        public int x, y;
        public char color;

        Position2D(int x, int y, char color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
}
