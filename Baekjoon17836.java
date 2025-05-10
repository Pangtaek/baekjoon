import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Baekjoon17836 {

    public static int N, M, T;
    public static int[][] map;

    // 상, 하, 좌, 우
    public static final int[][] dxdy = {
            { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 }
    };

    public static class Node {
        public int x;
        public int y;
        public int time;
        public boolean hasGram;

        public Node(
                int x,
                int y,
                int time,
                boolean hasGram) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.hasGram = hasGram;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        N = tokens[0];
        M = tokens[1];
        T = tokens[2];

        map = new int[N][M];
        for (int y = 0; y < N; y++) {
            String[] line = br.readLine().split(" ");
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(line[x]);
            }
        }

        int result = bfsWithStateTracking();
        System.out.println(result != -1 ? result : "Fail");
    }

    public static int bfsWithStateTracking() {
        int[][][] visited = new int[N][M][2]; // [y][x][0]: 무검, [y][x][1]: 검 있음
        for (int[][] layer : visited)
            for (int[] row : layer)
                Arrays.fill(row, -1);

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0, 0, false));
        visited[0][0][0] = 0;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            // 시간 초과
            if (curr.time > T)
                continue;

            // 도착지 도달
            if (curr.x == M - 1 && curr.y == N - 1) {
                return curr.time;
            }

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dxdy[d][0];
                int ny = curr.y + dxdy[d][1];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N)
                    continue;

                int nextHasGram = curr.hasGram ? 1 : 0;
                if (visited[ny][nx][nextHasGram] != -1)
                    continue;

                int nextPos = map[ny][nx];

                if (nextPos == 1 && !curr.hasGram)
                    continue; // 벽 + 무검 → 통과 불가

                boolean nextHasGramState = curr.hasGram || (nextPos == 2);

                visited[ny][nx][nextHasGramState ? 1 : 0] = curr.time + 1;
                queue.offer(new Node(nx, ny, curr.time + 1, nextHasGramState));
            }
        }

        return -1; // 도달 실패
    }
}
