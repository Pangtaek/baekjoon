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

    static final int[][] DIRECTIONS = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
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

        char[][] map = new char[HEIGHT][WIDTH];

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
            List<Position2D> puyoList = findAllPuyosToBurst(map);

            if (puyoList.isEmpty())
                break;

            map = doPuyo(map, puyoList);
            answer++;
        }

        return answer;
    }

    // 여러 그룹을 한 번에 찾아서 터뜨릴 리스트 생성
    static List<Position2D> findAllPuyosToBurst(char[][] map) {
        boolean[][] visited = new boolean[HEIGHT][WIDTH];
        List<Position2D> list = new ArrayList<>();

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                if (map[row][col] != '.' && !visited[row][col]) {
                    List<Position2D> puyoPositionList = getConnectedPuyos(map, new Position2D(col, row), visited);
                    if (puyoPositionList.size() >= 4) {
                        list.addAll(puyoPositionList);
                    }
                }
            }
        }

        return list;
    }

    // 연결된 뿌요를 BFS로 탐색
    static List<Position2D> getConnectedPuyos(char[][] map, Position2D start, boolean[][] visited) {
        Deque<Position2D> dq = new ArrayDeque<>();
        List<Position2D> positionList = new ArrayList<>();
        char color = map[start.y][start.x];

        visited[start.y][start.x] = true;
        dq.offer(start);
        positionList.add(start);

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

    // 뿌요 터뜨리고 아래로 떨어뜨림
    static char[][] doPuyo(char[][] map, List<Position2D> puyoPositionList) {
        char[][] newMap = new char[HEIGHT][WIDTH];

        // 원본 복사
        for (int row = 0; row < HEIGHT; row++) {
            newMap[row] = map[row].clone();
        }

        // 터진 뿌요 제거
        for (Position2D pos : puyoPositionList) {
            newMap[pos.y][pos.x] = '.';
        }

        // 각 열마다 처리
        for (int col = 0; col < WIDTH; col++) {
            Deque<Character> dq = new ArrayDeque<>();

            // 밑에서부터 위로 뿌요 수집
            for (int row = HEIGHT - 1; row >= 0; row--) {
                if (newMap[row][col] != '.') {
                    dq.offer(newMap[row][col]);
                }
            }

            // 아래부터 위로 채우기
            for (int row = HEIGHT - 1; row >= 0; row--) {
                if (!dq.isEmpty()) {
                    newMap[row][col] = dq.pollFirst();
                } else {
                    newMap[row][col] = '.';
                }
            }
        }

        return newMap;
    }
}
