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
    public static int[] dx = { 0, -1, 1, 0 };
    public static int[] dy = { 1, 0, 0, -1 };
    public static int weightOfBabyShark = 2;
    public static int answer = 0;

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            N = Integer.parseInt(br.readLine());

            matrix = new int[N][N];
            visited = new boolean[N][N];

            StringTokenizer st;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < st.countTokens(); j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            while (true) {
                solution();
            }

            System.out.println(answer);
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public static void solution() {
        queue = new LinkedList<>();
        Position2D currentPosition = getCurrentPosition();
        visited[currentPosition.y][currentPosition.x] = true;
        queue.add(currentPosition);

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = curr.x + dx[i];
                int newY = curr.y + dy[i];
                int newDept = curr.dept + 1;

                boolean flag = isInBound(newX, newY) && !visited[newY][newX] && weightOfBabyShark <= matrix[newY][newX];
                if (flag) {
                    if (weightOfBabyShark > matrix[newY][newX]) {
                        answer += newDept;
                        eatFish(newX, newY);
                        break;
                    } else if (weightOfBabyShark == matrix[newY][newX]) {
                        visited[newY][newX] = true;
                        queue.offer(new Position2D(newX, newY, newDept));
                    }
                }
            }
        }
    }

    public static void eatFish(int x, int y) {
        // 이전 위치 0
        Position2D curr = getCurrentPosition();
        matrix[curr.y][curr.x] = 0;

        // 먹은 물고기 위치로 이동
        matrix[y][x] = 9;

        // 방문 여부 초기화
        visited = new boolean[N][N];
    }

    public static boolean isInBound(int newX, int newY) {
        return 0 <= newX && newX < N && 0 <= newY && newY < N;
    }₩₩

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
        int x;
        int y;
        int dept;

        public Position2D(int x, int y, int dept) {
            this.x = x;
            this.y = y;
            this.dept = dept;
        }
    }
}