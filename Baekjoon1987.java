import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon1987 {

    private static Character[][] map;
    private static int maxCount = 0; // 최대 경로 길이
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int height = Integer.parseInt(st.nextToken()); // 세로
            int weight = Integer.parseInt(st.nextToken()); // 가로

            map = new Character[height][weight];

            for (int i = 0; i < height; i++) {
                char[] line = br.readLine().toCharArray();
                for (int j = 0; j < line.length; j++) {
                    map[i][j] = line[j];
                }
            }

            // DFS 시작
            Set<Character> used = new HashSet<>();
            used.add(map[0][0]); // 시작 알파벳 추가
            List<String> path = new ArrayList<>(); // 이동 경로를 저장할 리스트
            path.add(String.valueOf(map[0][0])); // 시작 알파벳 추가
            dfs(0, 0, 1, used, path); // 시작 위치와 초기 경로 길이

            System.out.println(maxCount);

        } catch (IOException e) {
            System.out.println("유근웅");
        }
    }

    private static void dfs(int x, int y, int count, Set<Character> used, List<String> path) {
        maxCount = Math.max(maxCount, count); // 최대 경로 길이 업데이트

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (checkBound(nx, ny) && !used.contains(map[nx][ny])) {
                // 다음 위치로 이동
                used.add(map[nx][ny]); // 현재 알파벳 추가
                path.add(String.valueOf(map[nx][ny])); // 이동 경로에 추가
                dfs(nx, ny, count + 1, used, path); // 재귀 호출
                // 백트래킹
                used.remove(map[nx][ny]); // 알파벳 제거
                path.remove(path.size() - 1); // 경로에서 마지막 알파벳 제거
            }
        }

        // 현재 경로를 출력
//        System.out.println("현재 경로: " + path);
    }

    private static boolean checkBound(int i, int j) {
        return i >= 0 && i < map.length && j >= 0 && j < map[i].length;
    }
}
