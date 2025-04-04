import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Baekjoon1446 {
    public static int N, D;
    public static List<List<Edge>> graph = new ArrayList<>();
    public static int[] dp;

    public static final int INF = Integer.MAX_VALUE;

    public static class Edge {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        N = tokens[0];
        D = tokens[1];

        dp = new int[D + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        // 그래프 초기화
        for (int i = 0; i <= 10000; i++) {
            graph.add(new ArrayList<>());
        }

        // 지름길 입력
        for (int i = 0; i < N; i++) {
            tokens = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            int from = tokens[0];
            int to = tokens[1];
            int weight = tokens[2];

            if (to > D)
                continue; // 도착지가 D 초과면 무시
            graph.get(from).add(new Edge(to, weight));
        }

        // DP 수행
        for (int curr = 0; curr <= D; curr++) {
            // 이전 거리 + 1로 갱신
            if (curr > 0) {
                dp[curr] = Math.min(dp[curr], dp[curr - 1] + 1);
            }

            // 지름길 처리
            for (Edge edge : graph.get(curr)) {
                if (edge.to <= D) {
                    dp[edge.to] = Math.min(dp[edge.to], dp[curr] + edge.weight);
                }
            }
        }

        System.out.println(dp[D]);
    }
}
