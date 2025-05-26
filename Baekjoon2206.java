import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Baekjoon2206 {
    static final int[][] dxdy = {
            { 0, 1 }, // 상
            { 0, -1 }, // 하
            { -1, 0 }, // 좌
            { 1, 0 } // 우
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int N = tokens[0];
        int M = tokens[1];
        int[][] map = new int[N][M];

        for (int row = 0; row < N; row++) {
            String input = br.readLine();

            for (int col = 0; col < M; col++) {
                map[row][col] = input.charAt(col);
            }
        }

        bw.write(solution(N, M, map) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static int solution(int N, int M, int[][] map) {
        Deque<Player> dq = new ArrayDeque<>();
        Player start = new Player(0, 0, 0, true);
    
        dq.offer(start);

        while (dq.isEmpty()) {
            Player curr = dq.poll();

            if (curr.x == M && curr.y == N) {
                return curr.distance;
            }

            for (int[] d : dxdy) {
                int nx = curr.x + d[0]; // 다음 x 좌표
                int ny = curr.y + d[1]; // 다음 y 좌표

                if (nx < 0 || nx >= M || ny < 0 || ny >= N) {
                    continue; // 배열 범위 위반
                }

                if (map[ny][nx] == 0) {
                    // 이동할 수 있는 곳
                    Player next = new Player(nx, ny, curr.distance + 1, curr.isBreak);
                    dq.offer(next);
                } else if (map[ny][nx] == 1) {
                    // 벽
                    if (curr.isBreak) {
                        // 벽을 부수고 이동
                        Player next = new Player(nx, ny, curr.distance + 1, false);
                        dq.offerLast(next);
                    } else {
                        continue;
                    }
                }
            }
        }

        return -1; // 도착 실패
    }
    
    static class Player extends Position2D {
        public boolean isBreak;
        public int distance;

        public Player(int x, int y, int distance, boolean isBreak) {
            super(x, y);
            this.isBreak = true;
            this.distance = distance;
        }
    }

    static class Position2D {
        public int x;
        public int y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
