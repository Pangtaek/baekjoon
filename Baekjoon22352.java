import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon22352 {

    public static int[][] origin;
    public static int[][] mutation;
    public static boolean[][] visited;
    public static int[] dx = { 1, -1, 0, 0 };
    public static int[] dy = { 0, 0, -1, 1 };
    public static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        origin = new int[N][M];
        mutation = new int[N][M];
        visited = new boolean[N][M];

        Position2D diffPosition = null;
        int originalValue = -1, newValue = -1;

        // 원본 배열 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 변형된 배열 입력 및 차이 찾기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                mutation[i][j] = Integer.parseInt(st.nextToken());

                if (diffPosition == null && mutation[i][j] != origin[i][j]) {
                    diffPosition = new Position2D(j, i);
                    originalValue = origin[i][j];
                    newValue = mutation[i][j];
                }
            }
        }

        // 변형된 부분이 있다면 BFS 탐색 수행
        if (diffPosition != null) {
            solution(diffPosition, originalValue, newValue);
        }

        // 최종 비교 후 결과 출력
        System.out.println(Arrays.deepEquals(origin, mutation) ? "YES" : "NO");
    }

    public static void solution(Position2D start, int originalValue, int newValue) {
        Queue<Position2D> queue = new LinkedList<>();
        List<Position2D> changedPositions = new ArrayList<>();

        queue.offer(start);
        visited[start.y][start.x] = true;
        changedPositions.add(start);

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = curr.x + dx[i];
                int newY = curr.y + dy[i];

                if (isInBound(newX, newY) && !visited[newY][newX] && origin[newY][newX] == originalValue) {
                    visited[newY][newX] = true;
                    queue.offer(new Position2D(newX, newY));
                    changedPositions.add(new Position2D(newX, newY));
                }
            }
        }

        // 탐색이 끝난 후 변경 적용
        for (Position2D pos : changedPositions) {
            origin[pos.y][pos.x] = newValue;
        }
    }

    public static boolean isInBound(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }

    public static class Position2D {
        int x, y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
