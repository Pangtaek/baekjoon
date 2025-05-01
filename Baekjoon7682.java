import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon7682 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        String input;
        while (!(input = br.readLine()).equals("end")) {
            if (isValidGame(input.toCharArray())) {
                answer.append("valid").append('\n');
            } else {
                answer.append("invalid").append('\n');
            }
        }

        System.out.print(answer);
    }

    // 게임 종료 조건과 X/O 수 조건을 함께 검증
    public static boolean isValidGame(char[] board) {
        int xCount = 0;
        int oCount = 0;

        for (char c : board) {
            if (c == 'X')
                xCount++;
            else if (c == 'O')
                oCount++;
        }

        boolean xWin = isTicTacToe(board, 'X');
        boolean oWin = isTicTacToe(board, 'O');

        // 둘 다 승리했으면 무효
        if (xWin && oWin)
            return false;

        // X가 이겼으면 X가 O보다 정확히 하나 많아야 함
        if (xWin)
            return xCount == oCount + 1;

        // O가 이겼으면 X와 O 개수가 같아야 함
        if (oWin)
            return xCount == oCount;

        // 아무도 이기지 않았으면 칸이 전부 채워졌고 X가 선공이므로 1개 더 많아야 함
        return xCount + oCount == 9 && xCount == oCount + 1;
    }

    // 승리 조건 (행, 열, 대각선)
    public static boolean isTicTacToe(char[] b, char c) {
        return (b[0] == c && b[1] == c && b[2] == c) || // 1행
                (b[3] == c && b[4] == c && b[5] == c) || // 2행
                (b[6] == c && b[7] == c && b[8] == c) || // 3행
                (b[0] == c && b[3] == c && b[6] == c) || // 1열
                (b[1] == c && b[4] == c && b[7] == c) || // 2열
                (b[2] == c && b[5] == c && b[8] == c) || // 3열
                (b[0] == c && b[4] == c && b[8] == c) || // \ 대각선
                (b[2] == c && b[4] == c && b[6] == c); // / 대각선
    }
}
