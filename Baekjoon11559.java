import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Baekjoon11559 {

    static final int[][] DIRECTIONS = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } }; // 상, 하, 좌, 우
    static final int HEIGHT = 12;
    static final int WIDTH = 6;

    static class Position2D {
        int x;
        int y;

        Position2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[][] map = new char[12][6];

        for (int row = 0; row < HEIGHT; row++) {
            String line = br.readLine();

            for (int col = 0; col < WIDTH; col++) {
                map[row][col] = line.charAt(col);
            }
        }

        int result = solution(map);
        bw.write(Integer.toString(result));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }

    static int solution(char[][] map) {
        int answer = 0;

        while (true) {
            List<Position2D> puyoList = isGameOver(map);

            if (puyoList == null || puyoList.isEmpty())
                break;

            map = doPuyo(map, puyoList);
            answer++;
        }

        return answer;
    }

    // 연결된 뿌요 위치 리스트를 반환하는 메서드
    static List<Position2D> getConnectedPuyos(char[][] map, Position2D pos) {
        boolean[][] visited = new boolean[HEIGHT][WIDTH];
        Deque<Position2D> dq = new ArrayDeque<>();
        List<Position2D> positionList = new ArrayList<>();
        char color = map[pos.y][pos.x];

        visited[pos.y][pos.x] = true;
        dq.offer(pos);
        positionList.add(pos);

        while (!dq.isEmpty()) {
            Position2D curr = dq.poll();

            for (int[] d : DIRECTIONS) {
                int nx = curr.x + d[0];
                int ny = curr.y + d[1];

                if (nx < 0 || ny < 0 || nx >= WIDTH || ny >= HEIGHT)
                    continue;
                if (visited[ny][nx])
                    continue;
                if (map[ny][nx] == color) {
                    visited[ny][nx] = true;
                    Position2D next = new Position2D(nx, ny);
                    dq.offer(next);
                    positionList.add(next);
                }
            }
        }

        return positionList;
    }

    static char[][] doPuyo(char[][] map, List<Position2D> puyoList) {
        char[][] newMap = new char[HEIGHT][WIDTH];

        // 1. 원본 맵 복사
        for (int row = 0; row < HEIGHT; row++) {
            newMap[row] = map[row].clone();
        }

        // 2. 터진 뿌요를 'X'로 표시
        for (Position2D pos : puyoList) {
            newMap[pos.y][pos.x] = 'X';
        }

        // 3. 각 열마다 위에서부터 아래로 스캔하여 뿌요를 아래로 내리기
        for (int col = 0; col < WIDTH; col++) {
            Deque<Character> dq = new ArrayDeque<>();

            // 밑에서부터 뿌요를 수집
            for (int row = HEIGHT - 1; row >= 0; row--) {
                if (newMap[row][col] != '.' && newMap[row][col] != 'X') {
                    dq.offer(newMap[row][col]);
                }
            }

            // 뿌요를 당기기
            for (int row = HEIGHT - 1; row >= 0; row--) {
                if (!dq.isEmpty()) {
                    newMap[row][col] = dq.getFirst();
                } else {
                    newMap[row][col] = '.';
                }
            }
        }

        return newMap;
    }    

    // 터트릴 뿌요가 없는 경우 -> 게임오버
    static List<Position2D> isGameOver(char[][] map) {
        for (int row = HEIGHT - 1; row >= 0; row--) {
            for (int col = WIDTH - 1; col >= 0; col--) {
                if (map[row][col] != '.') {
                    List<Position2D> puyoList = getConnectedPuyos(map, new Position2D(col, row));
                    if (puyoList.size() >= 4) {
                        return puyoList;
                    }
                }
            }
        }

        return null; // 게임오버
    }
}
