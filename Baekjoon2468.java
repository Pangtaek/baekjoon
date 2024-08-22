import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 문제: 안전 영역
 * 링크: https://www.acmicpc.net/problem/2468
 * 평가: 내 생각에 비가 안올때는 지역이 1개라고 생각한다. 하지만, bfs를 0부터해야 문제를 성공한다. 이것이 의문이다. 추후에 알게되면 내용을 업데이트 하겠다.*/

public class Baekjoon2468 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st;

            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            visit = new boolean[N][N];
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            // 입력받아 맵을 초기화하고 최소, 최대 수치를 찾음
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    min = Math.min(min, map[i][j]); // 최소 수치 갱신
                    max = Math.max(max, map[i][j]); // 최대 수치 갱신
                }
            }

            // 수위에 따라 최대 안전 구역 수를 출력
            System.out.println(solution(map, min, max));

        } catch (IOException e) {
            System.out.println("유근웅");
        }
    }

    private static int solution(int[][] map, int min, int max) {
        int maxCount = 0; // 최대 안전 구역 수를 저장

        // 수위를 기준으로 안전 구역 개수를 계산
        for (int precipitation = 0; precipitation <= max - 1; precipitation++) { // max까지 포함
            visit = new boolean[map.length][map[0].length]; // visit 배열 초기화
            int count = 0; // 현재 수위에서 안전 구역 수를 카운트

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    // 해당 위치가 수위보다 높고 방문하지 않았다면
                    if (map[i][j] > precipitation && !visit[i][j]) {
                        bfs(map, i, j, precipitation); // BFS 수행
                        count++; // 새로운 안전 구역 발견
                    }
                }
            }

            maxCount = Math.max(maxCount, count); // 최대 안전 구역 수 갱신
        }

        return maxCount; // 최대 안전 구역 수 반환
    }

    private static Queue<Position2D> queue = new LinkedList<>();
    private static boolean[][] visit; // 방문 여부 배열
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    private static void bfs(int[][] map, int i, int j, int precipitation) {
        queue.add(new Position2D(i, j)); // 시작 위치 추가
        visit[i][j] = true; // 방문 처리

        while (!queue.isEmpty()) {
            Position2D currPos = queue.poll(); // 현재 위치 가져오기

            // 상하좌우 탐색
            for (int k = 0; k < 4; k++) {
                int nx = currPos.x + dx[k]; // 새로운 x 좌표
                int ny = currPos.y + dy[k]; // 새로운 y 좌표

                // 경계 체크 후, 방문하지 않았고 수위보다 높은 경우
                if (checkBound(map, nx, ny) && !visit[nx][ny] && map[nx][ny] > precipitation) {
                    queue.add(new Position2D(nx, ny)); // 큐에 추가
                    visit[nx][ny] = true; // 방문 처리
                }
            }
        }
    }

    private static boolean checkBound(int[][] map, int x, int y) {
        // 경계 체크: 유효한 인덱스인지 확인
        return x >= 0 && x < map.length && y >= 0 && y < map[x].length;
    }

    public static class Position2D {
        int x; // x 좌표
        int y; // y 좌표

        Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
