import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Baekjoon1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int N = tokens[0];
            int K = tokens[1];

            int[] times = new int[N + 1];
            tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int i = 1; i <= N; i++) {
                times[i] = tokens[i - 1];
            }

            List<List<Integer>> graph = new ArrayList<>();
            int[] indegrees = new int[N + 1];
            int[] dp = new int[N + 1];

            for (int i = 0; i <= N; i++)
                graph.add(new ArrayList<>());

            for (int i = 0; i < K; i++) {
                tokens = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int from = tokens[0];
                int to = tokens[1];
                graph.get(from).add(to);
                indegrees[to]++;
            }

            Queue<Integer> queue = new ArrayDeque<>();
            for (int i = 1; i <= N; i++) {
                dp[i] = times[i]; // 초기값: 자기 자신 건물 짓는 시간
                if (indegrees[i] == 0)
                    queue.offer(i);
            }

            while (!queue.isEmpty()) {
                int current = queue.poll();

                for (int next : graph.get(current)) {
                    dp[next] = Math.max(dp[next], dp[current] + times[next]);
                    indegrees[next]--;
                    if (indegrees[next] == 0)
                        queue.offer(next);
                }
            }

            int W = Integer.parseInt(br.readLine());
            System.out.println(dp[W]);
        }
    }
}
