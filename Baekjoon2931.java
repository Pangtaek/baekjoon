import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Baekjoon2931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int R = tokens[0]; // 행
        int C = tokens[1]; // 열
        char[][] map = new char[R][C];
        boolean[] visited = new boolean[4];
        int[][] dydx = {
                { 0, -1 }, // 좌
                { -1, 0 }, // 상
                { 0, 1 }, // 우
                { 1, 0 } // 하
        };

        // 초기 입력값 받는 코드
        for (int i = 0; i < R; i++) {
            char[] charArr = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (charArr[j] == 'M' || charArr[j] == 'Z') {
                    map[i][j] = '*';
                } else
                    map[i][j] = charArr[j];
            }
        }

        // 규칙 추가, 좌 상 우 하 기준 0~4, 피해야하는 값 추가
        @SuppressWarnings("unchecked")
        HashSet<Character>[] setArr = new HashSet[4];
        for (int i = 0; i < 4; i++) {
            HashSet<Character> set = new HashSet<>();
            set.add('.');
            set.add('*');
            if (i % 2 == 0)
                set.add('|');
            else
                set.add('-');

            if (i == 0) {
                set.add('3');
                set.add('4');
            } else if (i == 1) {
                set.add('2');
                set.add('3');
            } else if (i == 2) {
                set.add('1');
                set.add('2');
            } else if (i == 3) {
                set.add('1');
                set.add('4');
            }

            setArr[i] = set;
        }

        loop: for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 빈칸에 대해서만 로직 실행
                if (map[i][j] != '.')
                    continue;

                Arrays.fill(visited, false);
                int cnt = 0;

                for (int k = 0; k < 4; k++) {
                    int nx = j + dydx[k][1];
                    int ny = i + dydx[k][0];

                    if (nx < 0 || nx >= C || ny < 0 || ny >= R)
                        continue;

                    // set에 없는 값이라면 연결되야하는 통로!
                    if (!setArr[k].contains(map[ny][nx])) {
                        visited[k] = true;
                        cnt++;
                    }
                }

                if (cnt == 0)
                    continue;

                else if (cnt == 1 || cnt == 3) {
                    for (int k = 0; k < 4; k++) {
                        int nx = j + dydx[k][1];
                        int ny = i + dydx[k][0];

                        if (nx < 0 || nx >= C || ny < 0 || ny >= R)
                            continue;

                        // set에 없는 값이라면 연결되야하는 통로!
                        if (map[ny][nx] == '*') {
                            visited[k] = true;
                            cnt++;
                            break;
                        }
                    }
                }

                // 맞는 통로 찾기
                sb.append(i + 1).append(" ").append(j + 1).append(" ");
                if (cnt == 4)
                    sb.append('+');
                else if (visited[1] && visited[3])
                    sb.append('|');
                else if (visited[0] && visited[2])
                    sb.append('-');
                else if (visited[2] && visited[3])
                    sb.append('1');
                else if (visited[1] && visited[2])
                    sb.append('2');
                else if (visited[0] && visited[1])
                    sb.append('3');
                else if (visited[0] && visited[3])
                    sb.append('4');
                sb.append("\n");
                break loop;
            }
        }

        System.out.println(sb);
    }
}