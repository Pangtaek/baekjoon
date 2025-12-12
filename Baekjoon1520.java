import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Baekjoon1520 {

    private static final int[][] DIRECTIONS = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    private static int M;
    private static int N;
    private static int[][] map;
    private static int[][] dp;

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            M = tokens[0]; // 세로의 크기(500 이하)
            N = tokens[1]; // 가로의 크기(500 이하)

            map = new int[M][N]; // 지도(각 지점의 높이는 10_000이하)
            dp = new int[M][N];

            for (int i = 0; i < M; i++) {
                map[i] = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            for (int i = 0; i < M; i++) {
                Arrays.fill(dp[i], -1);
            }

            int answer = dfs(0, 0);
            bw.write("출력값: " + String.valueOf(answer) + "\n");
            bw.write("DP 배열에 저장된 값\n");
            Arrays.stream(dp)
                    .map(row -> Arrays.stream(row)
                            .mapToObj(String::valueOf)
                            .collect(Collectors.joining("\t")))
                    .forEach(rowStr -> {
                        try {
                            bw.write(rowStr + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int dfs(int x, int y) {
        // 목적지 도착
        if (x == N - 1 && y == M - 1) {
            return 1;
        }

        // 이미 확인한 경로인 경우
        if (dp[y][x] != -1) {
            return dp[y][x];
        }

        int count = 0;
        for (int[] d : DIRECTIONS) {
            int nextX = x + d[0];
            int nextY = y + d[1];

            // 경계값 확인
            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                continue;
            }

            // 산의 높이 확인(현재 위치보다 낮은 경우에만 이동)
            if (map[y][x] > map[nextY][nextX]) {
                count += dfs(nextX, nextY);
            }
        }

        dp[y][x] = count;
        return dp[y][x];
    }
}
