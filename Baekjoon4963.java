import java.util.*;
import java.io.*;

public class Baekjoon4963 {
    private static int[][] matrix;
    private static boolean[][] visited;
    private static Stack<Position2D> stack;
    private static final int[] dx = { 1, 1, -1, -1, 0, 0, 1, -1 };
    private static final int[] dy = { 0, 1, 0, -1, 1, -1, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            int[] size = parseInput(br);
            int width = size[0], height = size[1];

            if (width == 0 && height == 0)
                break; // 종료 조건

            matrix = new int[height][width];
            visited = new boolean[height][width];
            stack = new Stack<>();
            int islandCount = 0;

            // 지도 입력 받기
            for (int i = 0; i < height; i++) {
                matrix[i] = parseInput(br);
            }

            // DFS로 섬 개수 탐색
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (!visited[i][j] && matrix[i][j] == 1) {
                        exploreIsland(i, j);
                        islandCount++;
                    }
                }
            }

            System.out.println(islandCount);
        }
    }

    // DFS 탐색 수행 (스택 사용)
    private static void exploreIsland(int y, int x) {
        stack.push(new Position2D(y, x));
        visited[y][x] = true;

        while (!stack.isEmpty()) {
            Position2D current = stack.pop();

            for (int i = 0; i < 8; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];

                if (isValid(newY, newX) && !visited[newY][newX] && matrix[newY][newX] == 1) {
                    visited[newY][newX] = true;
                    stack.push(new Position2D(newY, newX));
                }
            }
        }
    }

    // 입력값을 정수 배열로 변환하는 메서드
    private static int[] parseInput(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    // 유효한 좌표인지 확인하는 메서드
    private static boolean isValid(int y, int x) {
        return y >= 0 && y < matrix.length && x >= 0 && x < matrix[0].length;
    }

    // 2차원 좌표를 나타내는 클래스
    private static class Position2D {
        int y, x;

        public Position2D(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
