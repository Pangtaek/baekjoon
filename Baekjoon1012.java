import java.util.*;
import java.io.*;

public class Baekjoon1012 {
    private static final int[] dx = { 0, 0, -1, 1 }; // 상, 하, 좌, 우
    private static final int[] dy = { -1, 1, 0, 0 };

    private static int[][] map;
    private static boolean[][] visited;
    private static int M, N, K; // 가로(M), 세로(N), 배추 개수(K)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            initializeMap(br);
            int wormCount = countConnectedComponents();
            sb.append(wormCount).append('\n');
        }

        System.out.println(sb);
    }

    // 🌱 입력값을 받아서 지도 초기화
    private static void initializeMap(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[y][x] = 1; // 배추 위치 저장
        }
    }

    // 🐛 DFS 탐색을 통해 연결된 배추 개수를 세는 메서드
    private static int countConnectedComponents() {
        int count = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == 1 && !visited[y][x]) {
                    dfs(x, y);
                    count++;
                }
            }
        }
        return count;
    }

    // 🔎 DFS 탐색 수행 (재귀)
    private static void dfs(int x, int y) {
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (isValidPosition(nextX, nextY) && !visited[nextY][nextX] && map[nextY][nextX] == 1) {
                dfs(nextX, nextY);
            }
        }
    }

    // ✅ 좌표가 유효한 범위인지 확인하는 메서드
    private static boolean isValidPosition(int x, int y) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}
