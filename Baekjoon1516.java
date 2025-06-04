import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Baekjoon1516 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] buildTime = new int[N + 1];
        int[] dp = new int[N + 1];
        int[] indegree = new int[N + 1];

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            int[] tokens = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            buildTime[i] = tokens[0];

            for (int j = 1; j < tokens.length - 1; j++) {
                int prev = tokens[j];
                graph.get(prev).add(i);
                indegree[i]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                dp[i] = buildTime[i];
            }
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int next : graph.get(curr)) {
                indegree[next]--;
                dp[next] = Math.max(dp[next], dp[curr] + buildTime[next]);
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            bw.write(Integer.toString(dp[i]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
