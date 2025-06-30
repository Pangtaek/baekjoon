import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Baekjoon4991 {

    private static final int[][] DIRECTIONS = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } }; // 상하좌우

    private static class Position2D {
        int x, y;

        Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            int[] tokens = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int W = tokens[0];
            int H = tokens[1];
            if (W == 0 && H == 0)
                break;

            char[][] room = new char[H][W];
            List<Position2D> pointList = new ArrayList<>(); // 시작점과 더러운 칸들을 저장

            for (int i = 0; i < H; i++) {
                String line = br.readLine();
                for (int j = 0; j < W; j++) {
                    room[i][j] = line.charAt(j);
                    if (room[i][j] == 'o')
                        pointList.add(0, new Position2D(j, i)); // 시작점을 index 0에 저장
                    else if (room[i][j] == '*')
                        pointList.add(new Position2D(j, i)); // 더러운 칸 저장
                }
            }

            int n = pointList.size();
            int[][] dist = new int[n][n]; // 각 포인트 간 거리 저장 배열
            boolean possible = true;

            // 각 지점 간 최단 거리 계산
            for (int from = 0; from < n; from++) {
                int[][] d = bfs(H, W, room, pointList.get(from));
                for (int to = 0; to < n; to++) {
                    Position2D target = pointList.get(to);
                    dist[from][to] = d[target.y][target.x];
                    if (from != to && dist[from][to] == -1)
                        possible = false; // 도달 불가능한 경우
                }
            }

            if (!possible) {
                bw.write("-1\n");
                continue;
            }

            int[] perm = new int[n - 1]; // 시작점을 제외한 더러운 칸들에 대한 순열
            for (int i = 0; i < n - 1; i++)
                perm[i] = i + 1; // 인덱스 1부터 시작

            int min = Integer.MAX_VALUE;
            // 모든 순열에 대해 이동 거리의 최소값 계산
            do {
                int sum = dist[0][perm[0]]; // 시작점 → 첫 지점 거리
                for (int i = 0; i < perm.length - 1; i++) {
                    sum += dist[perm[i]][perm[i + 1]]; // 지점 간 거리 누적
                }
                min = Math.min(min, sum);
            } while (nextPermutation(perm));

            bw.write(min + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    // BFS로 start로부터 모든 위치까지의 최단 거리 구함
    private static int[][] bfs(int H, int W, char[][] room, Position2D start) {
        int[][] dist = new int[H][W];
        for (int[] row : dist)
            Arrays.fill(row, -1);

        Queue<Position2D> queue = new ArrayDeque<>();
        queue.offer(start);
        dist[start.y][start.x] = 0;

        while (!queue.isEmpty()) {
            Position2D cur = queue.poll();
            for (int[] d : DIRECTIONS) {
                int nx = cur.x + d[0];
                int ny = cur.y + d[1];

                if (nx < 0 || ny < 0 || nx >= W || ny >= H)
                    continue;
                if (room[ny][nx] == 'x' || dist[ny][nx] != -1)
                    continue;

                dist[ny][nx] = dist[cur.y][cur.x] + 1;
                queue.offer(new Position2D(nx, ny));
            }
        }
        return dist;
    }

    // 다음 순열 생성 : 내림차순이 되면 false를 반환
    private static boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i])
            i--;
        if (i == 0)
            return false;

        int j = arr.length - 1;
        while (arr[i - 1] >= arr[j])
            j--;

        int temp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = temp;

        Arrays.sort(arr, i, arr.length);
        return true;
    }
}
