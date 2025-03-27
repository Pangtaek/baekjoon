import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Baekjoon1260 {

    public static int N, M, V;
    public static boolean[][] edge;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        N = input[0];
        M = input[1];
        V = input[2];

        edge = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            input = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = input[0];
            int to = input[1];

            // 양방향 처리
            edge[from][to] = true;
            edge[to][from] = true;
        }

        String answer = dfsHandler() + "\n" + bfsHandler();
        System.out.println(answer);
    }

    public static String dfsHandler() {
        Arrays.fill(visited, false);
        StringBuilder sb = new StringBuilder();
        dfs(V, sb);
        return sb.toString().trim(); // 공백 제거
    }

    public static void dfs(int node, StringBuilder sb) {
        visited[node] = true;
        sb.append(node).append(" ");
        for (int next = 1; next <= N; next++) {
            if (edge[node][next] && !visited[next]) {
                dfs(next, sb);
            }
        }
    }

    public static String bfsHandler() {
        Arrays.fill(visited, false);
        StringBuilder sb = new StringBuilder();
        bfs(V, sb);
        return sb.toString().trim(); // 공백 제거
    }

    public static void bfs(int node, StringBuilder sb) {
        Queue<Integer> queue = new ArrayDeque<>();
        visited[node] = true;
        queue.offer(node);
        sb.append(node).append(" ");

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int to = 1; to <= N; to++) {
                if (!visited[to] && edge[curr][to]) {
                    visited[to] = true;
                    sb.append(to).append(" ");
                    queue.offer(to);
                }
            }
        }
    }
}
