import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Baekjoon2533 {

    public static List<List<Integer>> tree = new ArrayList<>();
    public static int[][] dp;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][2]; // [노드번호][0:얼리어답터X, 1:얼리어답터O]
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int u = tokens[0];
            int v = tokens[1];

            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0; // node가 얼리어답터가 아닐 때
        dp[node][1] = 1; // node가 얼리어답터일 때

        for (int child : tree.get(node)) {
            if (!visited[child]) {
                dfs(child);
                dp[node][0] += dp[child][1]; // 내가 얼리어답터가 아니면 자식은 무조건 얼리어답터
                dp[node][1] += Math.min(dp[child][0], dp[child][1]); // 내가 얼리어답터면 자식은 선택 자유
            }
        }
    }
}
