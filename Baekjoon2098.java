import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon2098 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] W = new int[N][N];

        for (int i = 0; i < N; i++) {
            W[i] = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        }

        int answer = solution(N, W);
        bw.write(Integer.toString(answer));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
    
    private static int solution(int N, int[][] W) {
        final int INF = 1_000_000_000;
        int[][] dp = new int[N][1 << N];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        // 시작은 도시 0번
        return tsp(0, 1, N, W, dp, INF);
    }

    private static int tsp(int cur, int visited, int N, int[][] W, int[][] dp, final int INF) {
        // 모든 도시를 방문했을 경우 → 출발 도시(0)로 복귀
        if (visited == (1 << N) - 1) {
            return W[cur][0] == 0 ? INF : W[cur][0];
        }

        // 이미 계산된 경우 반환
        if (dp[cur][visited] != -1)
            return dp[cur][visited];

        dp[cur][visited] = INF;

        for (int next = 0; next < N; next++) {
            // 이미 방문했거나, 갈 수 없는 경우는 제외
            if ((visited & (1 << next)) != 0 || W[cur][next] == 0)
                continue;

            int cost = W[cur][next] + tsp(next, visited | (1 << next), N, W, dp, INF);
            dp[cur][visited] = Math.min(dp[cur][visited], cost);
        }

        return dp[cur][visited];
    }
    
}