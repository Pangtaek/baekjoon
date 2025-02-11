import java.util.*;
import java.io.*;

public class Baekjoon4963 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;

        while (true) {
            // input: width height
            int[] size = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int width = size[0], height = size[1];

            // 입력의 마지막 줄에는 0이 두개 주어진다.
            if (width == 0 && height == 0) {
                break;
            }

            matrix = new int[height][width];
            visited = new boolean[height][width];
            stack = new Stack<>();

            // matrix 초기화
            for (int i = 0; i < height; i++) {
                int[] input = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                for (int j = 0; j < width; j++) {
                    matrix[i][j] = input[j];
                }
            }

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (!visited[i][j]) {
                        stack.push(new Position2D(i, j));
                        answer++;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    public static boolean[][] visited;
    public static Stack<Position2D> stack;
    public static int[] dirX = { 1, 1, -1, -1, 0, 0, 1, -1 };
    public static int[] dirY = { 0, 1, 0, -1, 1, -1, -1, 1 };
    public static int[][] matrix;

    public static void dfs(int height, int width) {
        visited[height][width] = true;

        while (!stack.isEmpty()) {
            Position2D curr = stack.pop();

            for (int i = 0; i < 8; i++) {
                int nextX = curr.width + dirX[i];
                int nextY = curr.width + dirY[i];

                if (isIn(nextX, nextY, matrix) && !visited[nextY][nextX] && matrix[nextY][nextX] == 1) {
                    stack.push(new Position2D(nextY, nextX));
                }
            }
        }
    }

    public static boolean isIn(int x, int y, int[][] matrix) {
        return 0 <= x && x < matrix[0].length && 0 <= y && y < matrix.length;
    }

    public static class Position2D {
        int width, height;

        public Position2D(int height, int width) {
            this.height = height;
            this.width = width;
        }
    }
}