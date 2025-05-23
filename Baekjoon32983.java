import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon32983 {
    static final int INF = Integer.MAX_VALUE;
    static final int[][] dxdy = {
            { 0, 1 }, // 상
            { 0, -1 }, // 하
            { -1, 0 }, // 좌
            { 1, 0 } // 우
    };

    static class Position2D {
        public int x;
        public int y;
        public int level;

        Position2D(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int N = tokens[0]; // 행
        int M = tokens[1]; // 열
        int C = tokens[2]; // 램프의 빛의 세기 당 비용

        tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int sr = tokens[0] - 1; // 시작 위치 (y)
        int sc = tokens[1] - 1; // 시작 위치 (x)

        int[][] cave = new int[N][M];
        for (int i = 0; i < N; i++) {
            cave[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        boolean[][] visited = new boolean[N][M];
        int[] lightGain = new int[N * M + 1]; // 거리별 루피 누적
        Queue<Position2D> queue = new LinkedList<>();
        queue.offer(new Position2D(sc, sr, 0));

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();
            int x = curr.x;
            int y = curr.y;
            int level = curr.level;

            if (x < 0 || x >= M || y < 0 || y >= N)
                continue;
            if (visited[y][x])
                continue;
            if (cave[y][x] == -1)
                continue;

            visited[y][x] = true;
            lightGain[level] += cave[y][x];

            for (int[] d : dxdy) {
                queue.offer(new Position2D(x + d[0], y + d[1], level + 1));
            }
        }

        int result = 0;
        int currentSum = 0;
        for (int i = 0; i < lightGain.length; i++) {
            currentSum += lightGain[i];
            int profit = currentSum - i * C;
            result = Math.max(result, profit);
        }

        // 출력
        bw.write(Integer.toString(result));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
