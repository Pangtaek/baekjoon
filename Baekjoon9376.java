import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Baekjoon9376 {
    public static int[] dx = { -1, 1, 0, 0 };
    public static int[] dy = { 0, 0, -1, 1 };

    public static int H, W;
    public static char[][] map;
    public static List<Point> prisonerList;

    public static class Point {
        public int x;
        public int y;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] tokens = br.readLine().split("\\s+");
            H = Integer.parseInt(tokens[0]);
            W = Integer.parseInt(tokens[1]);

            // 맵 크기를 (H+2)x(W+2)로 확장하고, 외곽은 전부 '.'
            map = new char[H + 2][W + 2];
            for (int i = 0; i < H + 2; i++) {
                Arrays.fill(map[i], '.');
            }

            prisonerList = new ArrayList<>();

            for (int i = 1; i <= H; i++) {
                String line = br.readLine();
                for (int j = 1; j <= W; j++) {
                    map[i][j] = line.charAt(j - 1);
                    if (map[i][j] == '$') {
                        prisonerList.add(new Point(i, j));
                    }
                }
            }

            // 0: 바깥, 1: 첫번째 죄수, 2: 두번째 죄수
            int[][][] dist = new int[3][H + 2][W + 2];
            for (int k = 0; k < 3; k++) {
                for (int i = 0; i < H + 2; i++) {
                    Arrays.fill(dist[k][i], -1);
                }
            }

            bfs(dist[0], new Point(0, 0)); // 바깥에서 시작
            bfs(dist[1], prisonerList.get(0)); // 첫 번째 죄수
            bfs(dist[2], prisonerList.get(1)); // 두 번째 죄수

            int min = Integer.MAX_VALUE;

            for (int i = 0; i < H + 2; i++) {
                for (int j = 0; j < W + 2; j++) {
                    // 세 명 모두 이 칸에 도달할 수 없는 경우는 무시
                    if (dist[0][i][j] == -1 || dist[1][i][j] == -1 || dist[2][i][j] == -1)
                        continue;

                    // 벽(*)은 지나갈 수 없으므로 무시
                    if (map[i][j] == '*')
                        continue;

                    // 현재 칸까지 오기 위한 세 사람의 거리 합을 구함
                    int sum = dist[0][i][j] + dist[1][i][j] + dist[2][i][j];

                    // 만약 이 칸이 문(#)이면
                    // 세 사람 모두 문을 열고 들어오면서 각각 +1 비용이 추가되는데,
                    // 실제로는 한 번만 열면 되므로, 불필요하게 중복된 +2 비용을 빼줌
                    if (map[i][j] == '#')
                        sum -= 2;

                    // 현재까지 최소 비용 갱신
                    min = Math.min(min, sum);
                }
            }

            answer.append(min).append("\n");
        }

        System.out.print(answer);
    }

    // 0-1 BFS
    public static void bfs(int[][] dist, Point start) {
        Deque<Point> deque = new ArrayDeque<>();
        dist[start.y][start.x] = 0;
        deque.offer(start);

        while (!deque.isEmpty()) {
            Point curr = deque.poll();

            for (int d = 0; d < 4; d++) {
                int ny = curr.y + dy[d];
                int nx = curr.x + dx[d];

                if (ny < 0 || ny >= H + 2 || nx < 0 || nx >= W + 2)
                    continue;
                if (map[ny][nx] == '*')
                    continue;
                if (dist[ny][nx] != -1)
                    continue;

                // 문을 열면 비용 1 추가
                if (map[ny][nx] == '#') {
                    dist[ny][nx] = dist[curr.y][curr.x] + 1;
                    deque.offerLast(new Point(ny, nx));
                } else { // 빈 공간은 비용 없이 이동
                    dist[ny][nx] = dist[curr.y][curr.x];
                    deque.offerFirst(new Point(ny, nx));
                }
            }
        }
    }
}
