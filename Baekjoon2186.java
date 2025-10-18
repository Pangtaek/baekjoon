import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * 백준 2186 - 문자판
 * DFS + 메모이제이션
 * https://www.acmicpc.net/problem/2186
 * 
 * 풀이법:
 * 1. DFS를 사용하여 현재 위치에서 단어의 다음 문자를 찾기
 * 2. K칸까지 이동할 수 있으므로 4방향으로 K칸씩 이동하며 다음 문자를 찾음
 * 3. 메모이제이션을 사용하여 이미 계산된 위치와 단어 인덱스에 대한 결과를 저장
 * 4. 모든 시작 위치에서 첫 번째 문자로 시작하는 경우를 탐색하여 총 경로 수 계산
 * 5. 최종 결과 출력
 * 
 * 시간 복잡도: O(N * M * L * K) (N: 행 수, M: 열 수, L: 단어 길이, K: 최대 이동 칸 수)
 * 공간 복잡도: O(N * M * L) (메모이제이션 배열)
 */

public class Baekjoon2186 {

    private static int N, M, K;
    private static char[][] board;
    private static String targetWord;
    private static int[][][] dp; // 메모이제이션을 위한 3차원 배열
    private static boolean[][][] visited; // 방문 체크

    // 상하좌우 이동을 위한 방향 배열
    private static int[] dx = { -1, 1, 0, 0 }; // 위, 아래, 왼쪽, 오른쪽
    private static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] inputs = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            N = inputs[0];
            M = inputs[1];
            K = inputs[2];
            board = new char[N][M];

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    board[i][j] = line.charAt(j);
                }
            }

            targetWord = br.readLine().trim();

            // DP 배열 초기화 (-1로 초기화하여 아직 계산되지 않음을 표시)
            dp = new int[N][M][targetWord.length()];
            visited = new boolean[N][M][targetWord.length()];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    Arrays.fill(dp[i][j], -1);
                }
            }

            int totalPaths = 0;

            // 모든 위치에서 첫 번째 문자로 시작하는 경우를 탐색
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == targetWord.charAt(0)) {
                        totalPaths += dfs(i, j, 0);
                    }
                }
            }

            bw.write(String.valueOf(totalPaths));
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // DFS + 메모이제이션
    private static int dfs(int x, int y, int wordIndex) {
        // 단어를 모두 만든 경우
        if (wordIndex == targetWord.length() - 1) {
            return 1;
        }

        // 이미 계산된 결과가 있는 경우
        if (visited[x][y][wordIndex]) {
            return dp[x][y][wordIndex];
        }

        visited[x][y][wordIndex] = true;
        dp[x][y][wordIndex] = 0;

        // 다음 문자 찾기
        char nextChar = targetWord.charAt(wordIndex + 1);

        // 4방향으로 K칸까지 이동
        for (int direction = 0; direction < 4; direction++) {
            for (int step = 1; step <= K; step++) {
                int nextX = x + dx[direction] * step;
                int nextY = y + dy[direction] * step;

                // 경계 체크
                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                    break; // 더 이상 이 방향으로 갈 수 없음
                }

                // 다음 문자와 일치하는 경우
                if (board[nextX][nextY] == nextChar) {
                    dp[x][y][wordIndex] += dfs(nextX, nextY, wordIndex + 1);
                }
            }
        }

        return dp[x][y][wordIndex];
    }
}
