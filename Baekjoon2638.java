import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Baekjoon2638 {
    public static int N, M;
    public static int[][] cheeseBoard;
    public static boolean[][] visited;

    public static final int[] dx = { 0, 0, 1, -1 };
    public static final int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        getInput();

        System.out.println(solution());
    }

    public static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = input[0];
        M = input[1];
        cheeseBoard = new int[N][M];

        for (int col = 0; col < N; col++) {
            input = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            cheeseBoard[col] = input;
        }
    }

    public static int solution() {
        int answer = 0;

        while (true) {
            if (getChessSize() == 0)
                break;

            // 1. 외각 치즈 중 녹는 부분 설정
            for (int col = 0; col < N; col++) {
                for (int row = 0; row < M; row++) {
                    if (cheeseBoard[col][row] == 1)
                        bfs(new Position2D(row, col));
                }
            }

            // 2. 치즈 녹임
            meltCheese();

            // 3. 시간 추가
            answer++;
        }

        return answer;
    }

    public static void meltCheese() {
        for (int col = 0; col < N; col++) {
            for (int row = 0; row < M; row++) {
                if (cheeseBoard[col][row] == 2) {
                    cheeseBoard[col][row] = 0;
                }
            }
        }
    }

    public static int getChessSize() {
        int count = 0;

        for (int col = 0; col < N; col++) {
            for (int row = 0; row < M; row++) {
                if (cheeseBoard[col][row] == 1)
                    count++;
            }
        }

        return count;
    }

    public static void bfs(Position2D position) {
        Queue<Position2D> queue = new ArrayDeque<>();
        int[][] count = new int[N][M];

        queue.offer(position);

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = curr.x = dx[d];
                int ny = curr.y = dy[d];

                boolean isAir = isInBounds(nx, ny) && cheeseBoard[ny][nx] == 0;
                if (isAir) {
                    count[curr.y][curr.x]++;
                }
            }
            
            if (count[curr.y][curr.x] == 2) {
                cheeseBoard[curr.y][curr.x] = 2;
            }
        }
    }

    public static boolean isInBounds(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }

    public static class Position2D {
        public int x;
        public int y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
