import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon3184 {

    public static char[][] field;
    public static boolean[][] visited;
    public static int[] dx = { 1, -1, 0, 0 };
    public static int[] dy = { 0, 0, 1, -1 };

    public static int R;
    public static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        field = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < C; j++) {
                field[i][j] = st.nextToken().charAt(0);
            }
        }

        int[] answer = solution();

        System.out.println(answer[0] + " " + answer[1]);
    }

    public static int[] solution() {
        int[] answer = { 0, 0 }; // 양, 늑대

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && (field[i][j] == 'o' || field[i][j] == 'v')) {
                    bfs(j, i);
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                switch (field[i][j]) {
                    case 'o':
                        answer[0]++;
                        break;
                    case 'v':
                        answer[1]++;
                        break;
                    default:
                        break;
                }
            }
        }

        return answer;
    }

    public static void bfs(int x, int y) {
        Queue<Position2D> queue = new ArrayDeque<>();
        List<Position2D> positionOfSheep = new ArrayList<>();
        List<Position2D> positionOfWolf = new ArrayList<>();

        visited[y][x] = true;
        queue.offer(new Position2D(x, y));

        while (!queue.isEmpty()) {
            Position2D curr = queue.poll();

            switch (whatAniaml(field[curr.y][curr.x])) {
                case 1:
                    positionOfSheep.add(curr);
                    break;
                case 2:
                    positionOfWolf.add(curr);
                    break;
                default:
                    break;
            }

            for (int i = 0; i < 4; i++) {
                int newX = curr.x + dx[i];
                int newY = curr.y + dy[i];

                boolean flag = isInBound(newX, newY) && visited[newY][newX] && field[newY][newX] != '#';

                if (flag) {
                    visited[newY][newX] = true;
                    queue.offer(new Position2D(newX, newY));
                }
            }
        }

        if (positionOfSheep.size() > positionOfWolf.size()) {
            for (Position2D position : positionOfWolf) {
                field[position.y][position.x] = '.';
            }
        } else {
            for (Position2D position : positionOfSheep) {
                field[position.y][position.x] = '.';
            }
        }
    }

    public static int whatAniaml(char c) {
        switch (c) {
            case 'o':
                return 1;
            case 'v':
                return 2;
            default:
                return 0;
        }
    }

    public static boolean isInBound(int newX, int newY) {
        return 0 <= newX && newX < C && 0 <= newY && newY < R;
    }

    public static class Position2D {
        int x;
        int y;

        public Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
