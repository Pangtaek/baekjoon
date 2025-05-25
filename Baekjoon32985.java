import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Baekjoon32985 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 노드의 개수
        boolean[][] graph = new boolean[N][N]; // 그래프
        int answer = 0;

        for (int i = 0; i < N - 1; i++) {
            int[] tokens = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = tokens[0];
            int to = tokens[1];

            graph[from][to] = true;
        }

        for (int from = 1; from < N; from++) {
            int result = 1;
            boolean[][] clone = clone(graph);

            for (int to = 0; to < N; to++) {
                clone[from][to] = false;
            }

            if (!dfsHandler(from, clone)) {
                result = 0;
            }

            answer = (answer << 1) | result;
        }

        bw.write(Integer.toBinaryString(answer));
        bw.newLine();        

        bw.flush();
        bw.close();
        br.close();
    }

    static boolean dfsHandler(int target, boolean[][] graph) {
        boolean[] visited = new boolean[graph.length];

        dfs(0, graph, visited);

        if(visited[target]) {
            return true;
        }

        return false;
    }

    static void dfs(int start, boolean[][] graph, boolean[] visited) {
        visited[start] = true;

        int[] nexts = IntStream.range(0, graph[start].length)
                .filter(i -> graph[start][i])
                .toArray();

        for (int next : nexts) {
            if (!visited[next]) {
                dfs(next, graph, visited);
            }
        }
    }

    static boolean[][] clone(boolean[][] origin) {
        int len = origin.length;
        boolean[][] clone = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            clone[i] = origin[i].clone();
        }

        return clone;
    }
}