import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Baekjoon11724 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int N = tokens[0]; // 점점의 개수
        int M = tokens[1]; // 간선의 개수

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokens = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = tokens[0];
            int to = tokens[1];

            // 무방향 그래프
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        int result = solution(N, graph);
        bw.write(Integer.toString(result));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }

    static int solution(int N, List<List<Integer>> graph) {
        int count = 0;
        boolean[] visited = new boolean[N + 1]; // 1-based indexing

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                bfs(i, graph, visited);
                count++;
            }
        }

        return count;
    }

    static void bfs(int start, List<List<Integer>> graph, boolean[] visited) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(start);
        visited[start] = true;

        while (!dq.isEmpty()) {
            int curr = dq.poll();
            for (int next : graph.get(curr)) {
                if (!visited[next]) { 
                    visited[next] = true;
                    dq.offer(next);
                }
            }
        }
    }    
}
