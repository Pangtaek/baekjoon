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
import java.util.stream.IntStream;

public class Baekjoon2606 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 컴퓨터의 수 (1 <= N <= 100)
        int M = Integer.parseInt(br.readLine()); // 네트워크 상에서 직접 연결되어 있는 컴퓨터의 쌍의 수

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int[] tokens = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int from = tokens[0];
            int to = tokens[1];

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
        boolean[] visited = new boolean[N + 1]; // 1-based indexing
        Deque<Integer> dq = new ArrayDeque<>();

        visited[1] = true;
        dq.offer(1);

        while (!dq.isEmpty()) {
            int curr = dq.pollFirst();

            for (int next : graph.get(curr)) {
                if (!visited[next]) {
                    visited[next] = true;
                    dq.offerLast(next);
                }
            }
        }

        return (int) IntStream.range(2, visited.length) // 1번 컴퓨터는 카운팅에서 제외하기 위해서 2번 인덱스부터 시작
                .filter(i -> visited[i])
                .count();
    }

}