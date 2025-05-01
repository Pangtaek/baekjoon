import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon7682 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        while (true) {
            String input = br.readLine();
            if (input.equals("end"))
                break;

            char[] board = input.toCharArray();

            if (isValid(board)) {
                answer.append("valid\n");
            } else {
                answer.append("invalid\n");
            }
        }

        System.out.print(answer);
    }

    public static boolean isValid(char[] board) {
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

        // 1. X와 O가 동시에 이김 → 불가능
        if (xWin && oWin)
            return false;

        // 2. X가 이겼으면 X는 O보다 1개 더 많아야 함
        if (xWin)
            return xCount == oCount + 1;

        // 3. O가 이겼으면 X와 O의 개수는 같아야 함
        if (oWin)
            return xCount == oCount;

        // 4. 누가 이기지 않았으면 칸이 모두 채워져야 함 (무승부)
        return xCount + oCount == 9;
    }

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
