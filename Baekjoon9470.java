import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Baekjoon9470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            int K = input[0];
            int M = input[1];
            int P = input[2];

            List<List<Integer>> graph = new ArrayList<>();
            int[] indegrees = new int[M + 1];
            int[] order = new int[M + 1]; // Strahler 순서
            int[] maxCount = new int[M + 1]; // 해당 노드로 들어오는 최대 순서의 개수

            for (int i = 0; i <= M; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < P; i++) {
                int[] edge = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
                int from = edge[0];
                int to = edge[1];
                graph.get(from).add(to);
                indegrees[to]++;
            }

            Queue<Integer> queue = new ArrayDeque<>();

            for (int i = 1; i <= M; i++) {
                if (indegrees[i] == 0) {
                    queue.offer(i);
                    order[i] = 1; // 시작 노드는 순서 1
                }
            }

            while (!queue.isEmpty()) {
                int current = queue.poll();

                for (int next : graph.get(current)) {
                    if (order[current] > order[next]) {
                        order[next] = order[current];
                        maxCount[next] = 1;
                    } else if (order[current] == order[next]) {
                        maxCount[next]++;
                    }

                    indegrees[next]--;
                    if (indegrees[next] == 0) {
                        // 같은 최대 순서가 2개 이상이면 순서를 +1
                        if (maxCount[next] >= 2) {
                            order[next]++;
                        }
                        queue.offer(next);
                    }
                }
            }

            sb.append(K).append(" ").append(order[M]).append("\n");
        }

        System.out.print(sb);
    }
}
