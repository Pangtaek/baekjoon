import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon1303 {

    public static char[][] matrix;
    public static boolean[][] visited;
    public static Queue<Position2D> queue;
    public static int[] dirX = {-1, 1, 0, 0};
    public static int[] dirY = {0, 0, -1, 1};

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            String[] tokens = br.readLine().split(" ");

            matrix = new char[Integer.parseInt(tokens[1])][Integer.parseInt(tokens[0])];

            for (int i = 0; i < matrix.length; i++) {
                String input = br.readLine();
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = input.charAt(j);
                }
            }

            // white 팀 전력분석
            int teamWhite = 0;
            visited = new boolean[Integer.parseInt(tokens[1])][Integer.parseInt(tokens[0])];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (!visited[i][j] && matrix[i][j] == 'W') {
                        teamWhite += bfs('W', i, j);
                    }
                }
            }

            // blue 팀 전력분석
            int teamBlue = 0;
            visited = new boolean[Integer.parseInt(tokens[1])][Integer.parseInt(tokens[0])];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (!visited[i][j] && matrix[i][j] == 'B') {
                        teamBlue += bfs('B', i, j);
                    }
                }
            }

            System.out.println(teamWhite + " " + teamBlue);

        } catch (IOException e) {
            System.out.println("유근웅");
        }
    }

    public static int bfs(char teamColor, int x, int y) {
        queue = new LinkedList<>();
        queue.add(new Position2D(x, y));
        visited[x][y] = true;

        int count = 1; // 시작 지점 포함

        while (!queue.isEmpty()) {
            Position2D current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current.getX() + dirX[i];
                int nextY = current.getY() + dirY[i];

                if (inBound(nextX, nextY) && matrix[nextX][nextY] == teamColor && !visited[nextX][nextY]) {
                    queue.add(new Position2D(nextX, nextY));
                    visited[nextX][nextY] = true;
                    count++; // 같은 팀의 연속된 위치를 발견할 때마다 카운트 증가
                }
            }
        }

        return (int) Math.pow(count, 2);
    }

    public static boolean inBound(int x, int y) {
        return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length;
    }

    public static class Position2D {
        private int x;
        private int y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
