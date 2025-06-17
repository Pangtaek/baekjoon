import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Baekjoon2580 {

    private static final int BOARD_SIZE = 9;
    private static int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    private static List<Position2D> blankPosList = new ArrayList<>();

    private static class Position2D {
        private int x;
        private int y;

        private Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 스도쿠 입력 및 빈 칸 수집
        for (int row = 0; row < BOARD_SIZE; row++) {
            board[row] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (board[row][column] == 0) {
                    blankPosList.add(new Position2D(column, row));
                }
            }
        }

        // 2. 백트래킹 시작
        solve(0);

        // 3. 결과 출력
        for (int[] row : board) {
            for (int n : row) {
                bw.write(n + " ");
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean solve(int depth) {
        if (depth == blankPosList.size()) {
            return true; // 모든 빈칸 채웠으면 종료
        }

        Position2D pos = blankPosList.get(depth);
        for (int num = 1; num <= 9; num++) {
            if (isValid(pos, num)) {
                board[pos.y][pos.x] = num;

                if (solve(depth + 1)) {
                    return true; // 정답 찾음
                }

                board[pos.y][pos.x] = 0; // 백트래킹
            }
        }

        return false; // 어떤 숫자도 안 맞으면 false
    }

    private static boolean isValid(Position2D pos, int num) {
        // 1. 행 검사
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[pos.y][i] == num)
                return false;
        }

        // 2. 열 검사
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][pos.x] == num)
                return false;
        }

        // 3. 3x3 박스 검사
        int startX = (pos.x / 3) * 3;
        int startY = (pos.y / 3) * 3;

        for (int y = startY; y < startY + 3; y++) {
            for (int x = startX; x < startX + 3; x++) {
                if (board[y][x] == num)
                    return false;
            }
        }

        return true;
    }
}
