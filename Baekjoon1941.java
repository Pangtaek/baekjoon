import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Baekjoon1941 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] matrix = new char[5][5];

        for (int row = 0; row < 5; row++) {
            String line = br.readLine();
            for (int col = 0; col < 5; col++) {
                matrix[row][col] = line.charAt(col);
            }
        }

        int answer = solution(matrix);
        System.out.println(answer);
    }

    public static int solution(char[][] matrix) {
        return combination(0, new ArrayList<>(), matrix);
    }

    public static int combination(int index, List<Position2D> selected, char[][] matrix) {
        if (selected.size() == 7) {
            return isValid(selected, matrix) ? 1 : 0;
        }

        int count = 0;
        for (int i = index; i < 25; i++) {
            int y = i / 5;
            int x = i % 5;

            selected.add(new Position2D(x, y));
            count += combination(i + 1, selected, matrix);
            selected.remove(selected.size() - 1);
        }

        return count;
    }

    public static boolean isValid(List<Position2D> selected, char[][] matrix) {
        int s = 0;

        for (Position2D pos : selected) {
            if (matrix[pos.y][pos.x] == 'S')
                s++;
        }

        return s >= 4 && isConnected(selected);
    }

    public static boolean isConnected(List<Position2D> selected) {
        boolean[][] isSelected = new boolean[5][5];
        for (Position2D pos : selected) {
            isSelected[pos.y][pos.x] = true;
        }

        boolean[][] visited = new boolean[5][5];
        Queue<Position2D> queue = new ArrayDeque<>();
        queue.offer(selected.get(0));
        visited[selected.get(0).y][selected.get(0).x] = true;

        int count = 1;
        int[] dx = { 1, -1, 0, 0 };
        int[] dy = { 0, 0, 1, -1 };

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if (0 <= nx && nx < 5 && 0 <= ny && ny < 5) {
                    if (!visited[ny][nx] && isSelected[ny][nx]) {
                        visited[ny][nx] = true;
                        queue.offer(new Position2D(nx, ny));
                        count++;
                    }
                }
            }
        }

        return count == 7;
    }

    public static class Position2D {
        int x, y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
