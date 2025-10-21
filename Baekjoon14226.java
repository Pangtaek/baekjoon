import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

/* 
 * 백준 14226번: 이모티콘
 * BFS를 활용한 상태 탐색 문제
 * 
 * 풀이:
 * 1. 상태를 화면에 있는 이모티콘 개수, 클립보
 * 드에 있는 이모티콘 개수, 현재까지 걸린 시간으로 정의
 * 2. BFS를 통해 세 가지 연산(복사, 붙여넣
 * 기, 삭제)을 수행하며 최소 시간을 탐색
 * 3. 방문 체크 배열을 사용하여 중복 상태 방문 방지
 * 4. 목표 이모티콘 개수에 도달하면 현재 시간 반환
 * 5. 모든 상태를 탐색했음에도 목표에 도달하지 못하면 -1 반환
 * 
 * 시간 복잡도: O(N^2) - 최대 화면과 클립보드 크기가 N일 때
 * 공간 복잡도: O(N^2) - 방문 체크 배열의 크기
 */

public class Baekjoon14226 {

    // 상태를 나타내는 클래스
    private static class State {
        int display; // 화면의 이모티콘 개수
        int clipboard; // 클립보드의 이모티콘 개수
        int time; // 현재까지 걸린 시간

        public State(int display, int clipboard, int time) {
            this.display = display;
            this.clipboard = clipboard;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int S = Integer.parseInt(br.readLine());

            int result = bfs(S);

            bw.write(String.valueOf(result));
            bw.flush();

        } catch (Exception e) {
            e.getMessage();
        }
    }

    private static int bfs(int target) {
        // 방문 체크 배열: visited[화면 개수][클립보드 개수]
        boolean[][] visited = new boolean[2001][2001];
        Deque<State> queue = new ArrayDeque<>();

        // 초기 상태: 화면 1개, 클립보드 0개, 시간 0초
        queue.offer(new State(1, 0, 0));
        visited[1][0] = true;

        while (!queue.isEmpty()) {
            State current = queue.poll();

            // 목표 달성
            if (current.display == target) {
                return current.time;
            }

            // 연산 1: 복사 (화면의 모든 이모티콘을 클립보드에 복사)
            if (!visited[current.display][current.display]) {
                visited[current.display][current.display] = true;
                queue.offer(new State(current.display, current.display, current.time + 1));
            }

            // 연산 2: 붙여넣기 (클립보드의 이모티콘을 화면에 추가)
            if (current.clipboard > 0 && current.display + current.clipboard <= 2000) {
                int newDisplay = current.display + current.clipboard;
                if (!visited[newDisplay][current.clipboard]) {
                    visited[newDisplay][current.clipboard] = true;
                    queue.offer(new State(newDisplay, current.clipboard, current.time + 1));
                }
            }

            // 연산 3: 삭제 (화면의 이모티콘 1개 삭제)
            if (current.display > 0) {
                int newDisplay = current.display - 1;
                if (!visited[newDisplay][current.clipboard]) {
                    visited[newDisplay][current.clipboard] = true;
                    queue.offer(new State(newDisplay, current.clipboard, current.time + 1));
                }
            }
        }

        return -1; // 도달 불가능 (실제로는 발생하지 않음)
    }
}
