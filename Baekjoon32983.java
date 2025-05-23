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
        int x, y;

        Position2D(int x, int y) {
            this.x = x;
            this.y = y;
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
        int C = tokens[2]; // 빛의 세기당 비용

        tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int sr = tokens[0]; // 행 (y)
        int sc = tokens[1]; // 열 (x)

        int[][] cave = new int[N][M];
        for (int i = 0; i < N; i++) {
            cave[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[][] distance = getDistance(cave, new Position2D(sc - 1, sr - 1));

        // 최대 도달 거리 계산
        int maxReachableDistance = 0;
        for (int[] row : distance) {
            for (int dist : row) {
                if (dist != INF) {
                    maxReachableDistance = Math.max(maxReachableDistance, dist);
                }
            }
        }

        int[] lightGain = new int[maxReachableDistance + 1]; // 거리 d의 루피 합

        // 시작 위치는 제외하고, 거리별 루피 누적
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                int d = distance[row][col];
                if (d > 0 && d != INF && cave[row][col] >= 0) {
                    lightGain[d] += cave[row][col];
                }
            }
        }

        // 누적 수익 계산
        int result = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int i = 1; i <= maxReachableDistance; i++) {
            currentSum += lightGain[i]; // 누적 합
            int profit = currentSum - i * C;
            if (profit > result) {
                result = profit;
            }
        }

        result = result > 0 ? result : 0;

        // 출력
        bw.write(Integer.toString(result));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    static int[][] getDistance(int[][] cave, Position2D start) {
        int N = cave.length;
        int M = cave[0].length;
        int[][] visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], INF);
        }

        Queue<Position2D> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.y][start.x] = 0;

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dxdy[d][0];
                int ny = curr.y + dxdy[d][1];
                int nd = visited[curr.y][curr.x] + 1;

                if (nx < 0 || nx >= M || ny < 0 || ny >= N)
                    continue;
                if (cave[ny][nx] == -1)
                    continue;

                if (nd < visited[ny][nx]) {
                    visited[ny][nx] = nd;
                    queue.offer(new Position2D(nx, ny));
                }
            }
        }

        return visited;
    }
}
