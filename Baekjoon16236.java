import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon16236 {

    public static int N;
    public static int[][] matrix;
    public static int[][] visited; // 방문 여부를 BFS 실행 횟수로 추적
    public static Queue<Position2D> queue;
    public static int[] dx = { 0, -1, 1, 0 }; // 우, 상, 하, 좌
    public static int[] dy = { 1, 0, 0, -1 };

    public static int weightOfBabyShark = 2; // 아기 상어 크기
    public static int eatCount = 0; // 먹은 물고기 개수
    public static int answer = 0; // 이동 거리(시간)
    public static int sharkX, sharkY; // 아기 상어 위치 저장
    public static int bfsRunCount = 0; // BFS 실행 횟수 추적

    public static void main(String[] args) throws IOException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            N = Integer.parseInt(br.readLine());

            matrix = new int[N][N];
            visited = new int[N][N]; // 방문 배열을 0으로 초기화 (BFS 실행 횟수로 활용)
            queue = new ArrayDeque<>(); // `LinkedList` → `ArrayDeque` 변경 (더 빠름)

            StringTokenizer st;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                    if (matrix[i][j] == 9) {
                        sharkX = j;
                        sharkY = i;
                        matrix[i][j] = 0; // 상어 초기 위치를 0으로 설정
                    }
                }
            }

            // BFS 실행하여 더 이상 먹을 물고기가 없을 때까지 반복
            while (solution()) {
            }

            System.out.println(answer);
        }
    }

    public static boolean solution() {
        queue.clear();
        bfsRunCount++; // BFS 실행 횟수 증가
        queue.add(new Position2D(sharkX, sharkY, 0));
        visited[sharkY][sharkX] = bfsRunCount;

        boolean fishEaten = false;
        int targetX = -1, targetY = -1, minDist = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            if (curr.dept > minDist)
                break; // 이미 최단 거리의 물고기를 찾은 경우 탐색 중단

            for (int i = 0; i < 4; i++) {
                int newX = curr.x + dx[i];
                int newY = curr.y + dy[i];
                int newDept = curr.dept + 1;

                if (isInBound(newX, newY) && visited[newY][newX] != bfsRunCount
                        && weightOfBabyShark >= matrix[newY][newX]) {
                    visited[newY][newX] = bfsRunCount;
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
        // 아기 상어 이동
        sharkX = x;
        sharkY = y;
        matrix[y][x] = 0;
        eatCount++;

        // 크기 증가 체크
        if (eatCount == weightOfBabyShark) {
            weightOfBabyShark++;
            eatCount = 0;
        }
    }

    public static boolean isInBound(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
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
