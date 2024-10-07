import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1058 {

    public static int[][] graph;
    public static int N;

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());
            graph = new int[N][N];

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    if (line.charAt(j) == 'Y') {
                        graph[i][j] = 1; // 친구 관계
                    } else {
                        graph[i][j] = 0; // 친구가 아님
                    }
                }
            }

            int maxFriends = 0;
            for (int i = 0; i < N; i++) {
                maxFriends = Math.max(maxFriends, countFriends(i));
            }

            System.out.println(maxFriends);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int countFriends(int idx) {
        boolean[] visited = new boolean[N];
        int friendCount = 0;

        // 직접 친구 세기
        for (int j = 0; j < N; j++) {
            if (graph[idx][j] == 1) {
                friendCount++;
                visited[j] = true; // 친구로 방문 처리
            }
        }

        // 친구의 친구 세기
        for (int j = 0; j < N; j++) {
            if (graph[idx][j] == 1) { // 친구인 경우
                for (int k = 0; k < N; k++) {
                    if (graph[j][k] == 1 && !visited[k] && k != idx) {
                        friendCount++;
                        visited[k] = true; // 친구의 친구로 방문 처리
                    }
                }
            }
        }

        return friendCount;
    }
}
