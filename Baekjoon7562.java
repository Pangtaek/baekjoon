import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Baekjoon7562 {
    static final int[][] DIRECTIONS = {
            { 1, -2 },
            { 2, -1 },
            { 2, 1 },
            { 1, 2 },
            { -1, 2 },
            { -2, 1 },
            { -2, -1 },
            { -1, -2 }
    };

    static class Position2D {
        int x;
        int y;

        Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(br.readLine()); // 테스트 케이스의 수
        for (int testcase = 0; testcase < TC; testcase++) {
            int L = Integer.parseInt(br.readLine()); // 체스판의 한 변의 길이
            Position2D start = new Position2D(0, 0); // 나이트가 현재 있는 칸
            Position2D target = new Position2D(0, 0); // 나이트가 이동하려고 하는 칸
            for (int i = 0; i < 2; i++) {
                int[] tokens = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                if (i == 0) {
                    start.x = tokens[0];
                    start.y = tokens[1];
                } else {
                    target.x = tokens[0];
                    target.y = tokens[1];
                }
            }

            bw.write(Integer.toString(
                    solution(L, start, target)));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int solution(int L, Position2D start, Position2D target) {
        int[][] chessBoard = new int[L][L];
        for (int i = 0; i < L; i++) {
            Arrays.fill(chessBoard[i], -1);
        }

        Deque<Position2D> queue = new ArrayDeque<>();
        queue.offer(start);
        chessBoard[start.y][start.x] = 0;

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            if (curr.x == target.x && curr.y == target.y) {
                break;
            }

            for (int[] d : DIRECTIONS) {
                int nx = curr.x + d[0];
                int ny = curr.y + d[1];

                if (nx < 0 || nx >= L || ny < 0 || ny >= L) {
                    continue;
                }

                if (chessBoard[ny][nx] == -1) {
                    chessBoard[ny][nx] = chessBoard[curr.y][curr.x] + 1;
                    queue.offer(new Position2D(nx, ny));
                }
            }
        }

        return chessBoard[target.y][target.x];
    }
}
