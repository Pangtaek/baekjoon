import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon16236 {

    public static int N;
    public static int[][] matrix;
    public static boolean[][] visited;
    public static Queue<Position2D> queue;
    public static int[] dx = { 0, -1, 1, 0 }; // 우, 상, 하, 좌 (순서 중요!)
    public static int[] dy = { 1, 0, 0, -1 };

    public static int weightOfBabyShark = 2; // 아기 상어 크기
    public static int eatCount = 0; // 먹은 물고기 개수
    public static int answer = 0; // 이동 거리(시간)

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            N = Integer.parseInt(br.readLine());

            matrix = new int[N][N];
            visited = new boolean[N][N];

            StringTokenizer st;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // BFS 실행하여 더 이상 먹을 물고기가 없을 때까지 반복
            while (solution()) {
            }

            System.out.println(answer);
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public static boolean solution() {
        queue = new LinkedList<>();
        Position2D currentPosition = getCurrentPosition();
        if (currentPosition == null)
            return false; // 아기 상어가 없으면 종료

        visited[currentPosition.y][currentPosition.x] = true;
        queue.add(currentPosition);

        boolean fishEaten = false;
        int targetX = -1, targetY = -1, minDist = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = curr.x + dx[i];
                int newY = curr.y + dy[i];
                int newDept = curr.dept + 1;

                if (isInBound(newX, newY) && !visited[newY][newX] && weightOfBabyShark >= matrix[newY][newX]) {
                    visited[newY][newX] = true;
                    queue.offer(new Position2D(newX, newY, newDept));

                    if (weightOfBabyShark > matrix[newY][newX] && matrix[newY][newX] > 0) {
                        if (newDept < minDist ||
                                (newDept == minDist && newY < targetY) ||
                                (newDept == minDist && newY == targetY && newX < targetX)) {
                            minDist = newDept;
                            targetX = newX;
                            targetY = newY;
                            fishEaten = true;
                        }
                    }
                }
            }
        }

        if (fishEaten) {
            answer += minDist;
            eatFish(targetX, targetY);
            return true;
        }

        return false;
    }

    public static void eatFish(int x, int y) {
        Position2D curr = getCurrentPosition();
        matrix[curr.y][curr.x] = 0;

        matrix[y][x] = 9;
        eatCount++;

        if (eatCount == weightOfBabyShark) {
            weightOfBabyShark++;
            eatCount = 0;
        }

        visited = new boolean[N][N];
    }

    public static boolean isInBound(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public static Position2D getCurrentPosition() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 9) {
                    return new Position2D(j, i, 0);
                }
            }
        }
        return null;
    }

    public static class Position2D {
        int x, y, dept;

        public Position2D(int x, int y, int dept) {
            this.x = x;
            this.y = y;
            this.dept = dept;
        }
    }
}
