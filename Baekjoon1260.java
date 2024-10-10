import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon1260 {

    public static boolean[] visited;
    public static ArrayList<Integer>[] graph;
    public static StringBuilder answer;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] tokens = br.readLine().split(" ");
            int vertex = Integer.parseInt(tokens[0]);
            int edge = Integer.parseInt(tokens[1]);
            int startVertex = Integer.parseInt(tokens[2]);

            // 그래프 초기화
            graph = new ArrayList[vertex + 1];
            for (int i = 1; i <= vertex; i++) {
                graph[i] = new ArrayList<>();
            }

            // 간선 정보 입력
            for (int i = 0; i < edge; i++) {
                tokens = br.readLine().split(" ");
                int u = Integer.parseInt(tokens[0]);
                int v = Integer.parseInt(tokens[1]);
                graph[u].add(v);
                graph[v].add(u); // 무향 그래프이므로 양쪽 모두 추가
            }

            // 각 리스트를 정렬
            for (int i = 1; i <= vertex; i++) {
                graph[i].sort((a, b) -> a - b);
            }

            // 방문 배열 초기화
            visited = new boolean[vertex + 1];
            answer = new StringBuilder();
            dfs(startVertex);
            System.out.println(answer.toString().trim());

            // 방문 배열 초기화
            visited = new boolean[vertex + 1];
            answer = new StringBuilder();
            bfs(startVertex);
            System.out.println(answer.toString().trim());

        } catch (IOException e) {
            System.out.println("유근웅");
        }
    }

    public static void dfs(int index) {
        visited[index] = true; // 현재 정점 방문 처리
        answer.append(index).append(" "); // 방문한 정점을 결과에 추가

        // 인접한 정점들에 대해 DFS 수행
        for (int neighbor : graph[index]) {
            if (!visited[neighbor]) { // 아직 방문하지 않은 정점인 경우
                dfs(neighbor);
            }
        }
    }

    public static void bfs(int index) {
        Queue<Integer> queue = new LinkedList<>(); // BFS를 위한 큐 초기화
        queue.add(index); // 시작 정점을 큐에 추가
        visited[index] = true; // 시작 정점 방문 처리
        answer.append(index).append(" "); // 결과에 추가

        while (!queue.isEmpty()) {
            Integer vertex = queue.poll(); // 큐에서 정점 꺼내기

            for (int neighbor : graph[vertex]) {
                if (!visited[neighbor]) { // 아직 방문하지 않은 정점인 경우
                    visited[neighbor] = true; // 방문 처리
                    queue.add(neighbor); // 큐에 추가
                    answer.append(neighbor).append(" "); // 결과에 추가
                }
            }
        }
    }
}
