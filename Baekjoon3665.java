import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Baekjoon3665 {
    public static int[][] graph;
    public static int[] inDegree;
    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());
            int[] lastYear = new int[n + 1];
            inDegree = new int[n + 1];
            graph = new int[n + 1][n + 1];

            String[] input = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                lastYear[i + 1] = Integer.parseInt(input[i]);
            }

            // 작년 순위로 그래프 구성
            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    int higher = lastYear[i];
                    int lower = lastYear[j];
                    graph[higher][lower] = 1;
                    inDegree[lower]++;
                }
            }

            int m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                input = br.readLine().split(" ");
                int a = Integer.parseInt(input[0]);
                int b = Integer.parseInt(input[1]);

                // 방향 뒤집기
                if (graph[a][b] == 1) {
                    graph[a][b] = 0;
                    graph[b][a] = 1;
                    inDegree[b]--;
                    inDegree[a]++;
                } else {
                    graph[b][a] = 0;
                    graph[a][b] = 1;
                    inDegree[a]--;
                    inDegree[b]++;
                }
            }

            List<Integer> result = topologySort();
            if (result == null) {
                System.out.println("IMPOSSIBLE");
            } else if (result.isEmpty()) {
                System.out.println("?");
            } else {
                for (int team : result) {
                    System.out.print(team + " ");
                }
                System.out.println();
            }
        }
    }

    public static List<Integer> topologySort() {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        for (int i = 0; i < n; i++) {
            if (q.isEmpty())
                return null; // 사이클 발생
            if (q.size() > 1)
                return new ArrayList<>(); // 불확정 순위

            int now = q.poll();
            result.add(now);

            for (int j = 1; j <= n; j++) {
                if (graph[now][j] == 1) {
                    inDegree[j]--;
                    if (inDegree[j] == 0) {
                        q.offer(j);
                    }
                }
            }
        }

        return result;
    }
}
