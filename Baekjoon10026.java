import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon10026 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            char[][] matrix = new char[N][N];

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = str.charAt(j);
                }
            }

            System.out.println(getPartition(matrix) + " " + getPartitionByRedGreenColorBlindness(matrix));
        } catch (IOException e) {
            System.out.println("임채륜");
        }
    }

    public static int[] dirX = { 1, -1, 0, 0 };
    public static int[] dirY = { 0, 0, 1, -1 };
    public static Boolean[][] visited;

    // 일반인의 보이는 부분의 개수
    public static int getPartition(char[][] matrix) {
        int answer = 0;
        visited = new Boolean[matrix.length][matrix.length];
        int N = matrix.length;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == null) {
                    bfs(matrix, i, j);
                    answer++;
                }
            }
        }

        return answer;
    }
    
    public static void bfs(char[][] matrix, int i, int j) {
        Queue<Position2D> queue = new LinkedList<>();
        char color = matrix[i][j];

        queue.offer(new Position2D(i, j, color));
        
        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int index = 0; index < 4; index++) {
                int newX = curr.x + dirX[index];
                int newY = curr.y + dirY[index];

                if (isInBound(matrix, newX, newY) && matrix[newX][newY] == color) {
                    queue.offer(new Position2D(newX, newY, color));
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

    public static void bfsByRedGreenColorBlindness(char[][] matrix, int i, int j) {
        Queue<Position2D> queue = new LinkedList<>();
        char color = matrix[i][j];

        queue.offer(new Position2D(i, j, color));

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int index = 0; index < 4; index++) {
                int newX = curr.x + dirX[index];
                int newY = curr.y + dirY[index];

                if (isInBound(matrix, newX, newY) && (matrix[newX][newY] == 'R' || matrix[newX][newY] == 'G')) {
                    queue.offer(new Position2D(newX, newY, color));
                }
            }
        }
    }

    // 적록색약인이 보이는 부분의 개수
    public static int getPartitionByRedGreenColorBlindness(char[][] matrix) {
        int answer = 0;
        visited = new Boolean[matrix.length][matrix.length];
        int N = matrix.length;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == null) {
                    bfsByRedGreenColorBlindness(matrix, i, j);
                    answer++;
                }
            }
        }

        return answer;
    }
}
