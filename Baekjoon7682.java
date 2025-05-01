import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon7682 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        StringBuilder answer = new StringBuilder();

        while (!input.equals("end")) {
            if (isValidCount(input.toCharArray()) && isValidGameOver(input.toCharArray())) {
                answer.append("valid");
            } else {
                answer.append("invalid");
            }
            answer.append("\n");
            input = br.readLine();
        }

        System.out.println(answer);
    }

    // X가 O보다 1개 더 많은 상태이거나 무승부(9칸 모두 채움) 여부 확인
    public static boolean isValidCount(char[] input) {
        int xCount = 0;
        int oCount = 0;

        for (char c : input) {
            xCount += (c == 'X') ? 1 : 0;
            oCount += (c == 'O') ? 1 : 0;
        }

        if ((xCount + oCount) == 9) {
            return xCount == oCount + 1; // 무승부 시에도 선공이 마지막에 두어야 함
        }

        return xCount == oCount + 1 || xCount == oCount;
    }

    // 게임 종료 조건의 타당성 판단
    public static boolean isValidGameOver(char[] input) {
        int xCount = 0;
        int oCount = 0;

        for (char c : input) {
            xCount += (c == 'X') ? 1 : 0;
            oCount += (c == 'O') ? 1 : 0;
        }

        boolean xWin = isTicTacToe(input, 'X');
        boolean oWin = isTicTacToe(input, 'O');

        // 둘 다 이긴 경우는 불가능
        if (xWin && oWin)
            return false;

        // X가 이겼으면 X가 한 수 더 많아야 함
        if (xWin)
            return xCount == oCount + 1;

        // O가 이겼으면 수가 같아야 함
        if (oWin)
            return xCount == oCount;

        // 아무도 이기지 않았으면 모든 칸이 채워졌는지 확인 (무승부)
        return (xCount + oCount == 9);
    }

    // 틱택토 완성 여부 확인
    public static boolean isTicTacToe(char[] input, char c) {
        return (input[0] == c && input[1] == c && input[2] == c) || // 1행
                (input[3] == c && input[4] == c && input[5] == c) || // 2행
                (input[6] == c && input[7] == c && input[8] == c) || // 3행
                (input[0] == c && input[3] == c && input[6] == c) || // 1열
                (input[1] == c && input[4] == c && input[7] == c) || // 2열
                (input[2] == c && input[5] == c && input[8] == c) || // 3열
                (input[0] == c && input[4] == c && input[8] == c) || // \ 대각선
                (input[2] == c && input[4] == c && input[6] == c); // / 대각선
    }
}
