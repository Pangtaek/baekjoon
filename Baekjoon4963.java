import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon4963 {

    public static int[][] map;             // 지도 배열
    public static boolean[][] visited;      // 방문 여부를 기록하는 배열
    public static int w;                    // 가로 크기
    public static int h;                    // 세로 크기

    public static class Position2D {
        int x; // x 좌표
        int y; // y 좌표

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                w = Integer.parseInt(st.nextToken()); // 가로 크기 입력
                h = Integer.parseInt(st.nextToken()); // 세로 크기 입력

                if (w == 0 && h == 0) {
                    break;
                }

                map = new int[h][w];
                visited = new boolean[h][w];

                // 지도 입력
                for (int i = 0; i < h; i++) {
                    st = new StringTokenizer(br.readLine());
                    for (int j = 0; j < w; j++) {
                        map[i][j] = Integer.parseInt(st.nextToken());
                    }
                }

                int answer = 0; // 섬의 개수를 세기 위한 변수

                // 모든 위치를 검사하여 섬의 개수 세기
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        // 땅(1)이고 방문하지 않았다면 BFS 시작
                        if (map[i][j] == 1 && !visited[i][j]) {
                            bfs(i, j); // BFS 호출
                            answer++; // 섬 개수 증가
                        }
                    }
                }
                System.out.println(answer); // 결과 출력
            }

        } catch (IOException e) {
            System.out.println("이창윤윤윤");
        }
    }

    public static boolean checkBound(int i, int j) {
        // 배열의 범위를 체크하는 함수
        return 0 <= i && i < h && 0 <= j && j < w;
    }

    public static void bfs(int i, int j) {
        Queue<Position2D> queue = new LinkedList<>(); // BFS를 위한 큐
        visited[i][j] = true; // 현재 위치를 방문 처리
        queue.offer(new Position2D(i, j)); // 큐에 현재 위치 추가

        // 8 방향 탐색을 위한 배열
        int[] dirX = {1, -1, 0, 0, 1, 1, -1, -1};
        int[] dirY = {0, 0, -1, 1, 1, -1, 1, -1};

        while (!queue.isEmpty()) { // 큐가 빌 때까지 반복
            Position2D currentPos = queue.poll(); // 큐에서 현재 위치 꺼내기

            // 8 방향으로 탐색
            for (int x = 0; x < 8; x++) {
                int nextX = currentPos.x + dirX[x]; // 다음 x 좌표
                int nextY = currentPos.y + dirY[x]; // 다음 y 좌표

                // 배열의 범위 체크, 땅인지 여부, 방문 여부 체크
                if (checkBound(nextX, nextY)             // 배열의 범위 안에 값 여부
                        && map[nextX][nextY] == 1        // 땅인지 여부
                        && !visited[nextX][nextY]) {     // 방문 여부
                    visited[nextX][nextY] = true; // 방문 처리
                    queue.offer(new Position2D(nextX, nextY)); // 큐에 다음 위치 추가
                }
            }
        }
    }
}
