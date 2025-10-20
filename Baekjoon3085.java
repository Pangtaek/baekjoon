import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 백준 3085번: 사탕 게임
 * https://www.acmicpc.net/problem/3085
 * 분류: 구현, 브루트포스 알고리즘
 * 
 * 풀이)
 * 1. 보드판의 모든 칸을 순회하며 오른쪽과 아래
 *    칸과 사탕을 교환해본다.
 * 2. 교환 후, 가로와 세로로 연속된 사탕의 최대 개수를
 *   구해 답을 갱신한다.
 * 3. 교환한 사탕을 다시 원래대로 되돌린다.
 * 4. 모든 칸에 대해 1~3을 반복한 후, 답
 *  출력한다.
 * 시간 복잡도: O(N^3)
 * 공간 복잡도: O(N^2)
 */

public class Baekjoon3085 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int N = Integer.parseInt(br.readLine());

            char[][] board = new char[N][N];

            for (int i = 0; i < N; i++)
                board[i] = br.readLine().toCharArray();

            int answer = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i + 1 < N) {
                        swap(board, i, j, i + 1, j);
                        answer = Math.max(answer, check(board, N));
                        swap(board, i, j, i + 1, j);
                    }

                    if (j + 1 < N) {
                        swap(board, i, j, i, j + 1);
                        answer = Math.max(answer, check(board, N));
                        swap(board, i, j, i, j + 1);
                    }
                }

                bw.write(String.valueOf(answer));
                bw.flush();
            }

        } catch (IOException e) {
            e.getMessage();
        }
    }

    private static int check(char[][] board, int n) {
        int max = 1;

        for (int i = 0; i < n; i++) {
            int count = 1;
            for (int j = 1; j < n; j++) {
                if (board[i][j] == board[i][j - 1]) {
                    count++;
                } else {
                    max = Math.max(max, count);
                    count = 1;
                }
            }
            max = Math.max(max, count);
        }

        for (int j = 0; j < n; j++) {
            int count = 1;
            for (int i = 1; i < n; i++) {
                if (board[i][j] == board[i - 1][j]) {
                    count++;
                } else {
                    max = Math.max(max, count);
                    count = 1;
                }
            }
            max = Math.max(max, count);
        }

        return max;
    }

    private static void swap(char[][] board, int i, int j, int k, int j2) {
        char temp = board[i][j];
        board[i][j] = board[k][j2];
        board[k][j2] = temp;
    }
}