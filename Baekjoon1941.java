import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Baekjoon1941 {
    static char[][] matrix = new char[5][5];
    static int[] selected = new int[7];
    static boolean[] visited = new boolean[25];
    static int answer = 0;

    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = line.charAt(j);
            }
        }

        combination(0, 0);
        System.out.println(answer);
    }

    static void combination(int start, int depth) {
        if (depth == 7) {
            if (isValid()) {
                answer++;
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            selected[depth] = i;
            combination(i + 1, depth + 1);
        }
    }

    static boolean isValid() {
        boolean[] check = new boolean[25];
        for (int i : selected)
            check[i] = true;

        int sCount = 0;
        for (int i : selected) {
            int x = i / 5;
            int y = i % 5;
            if (matrix[x][y] == 'S')
                sCount++;
        }
        if (sCount < 4)
            return false;

        return isConnected(check);
    }

    static boolean isConnected(boolean[] check) {
        boolean[] visited = new boolean[25];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(selected[0]);
        visited[selected[0]] = true;

        int count = 1;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int x = curr / 5;
            int y = curr % 5;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                int next = nx * 5 + ny;

                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5)
                    continue;
                if (check[next] && !visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                    count++;
                }
            }
        }

        return count == 7;
    }
}
