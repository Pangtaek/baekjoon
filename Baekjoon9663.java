import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon9663 {

    public static int N;
    public static int answer = 0;
    public static int[] queens; // 각 행(row)마다 배치된 퀸의 열(col) 값 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        queens = new int[N]; // 퀸의 열 위치를 저장하는 배열
        solution(0); // 0번째 행부터 시작

        System.out.println(answer);
    }

    public static void solution(int row) {
        if (row == N) { // 모든 행에 퀸을 배치 완료
            answer++;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isValid(row, col)) {
                queens[row] = col; // 현재 행(row)에 퀸 배치
                solution(row + 1); // 다음 행으로 이동 (재귀 호출)
            }
        }
    }

    public static boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            // 같은 열이거나 || 같은 대각선에 있으면 배치 불가능
            if (queens[i] == col || Math.abs(queens[i] - col) == row - i) {
                return false;
            }
        }
        return true; // 해당 위치에 퀸 배치 가능
    }
}
