import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1613 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        boolean[][] graph = new boolean[n + 1][n + 1];

        // 관계 입력: before → after
        for (int i = 0; i < k; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            graph[a][b] = true;
        }

        // 플로이드-워셜: 모든 쌍 순서 관계 구하기
        for (int mid = 1; mid <= n; mid++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    if (graph[from][mid] && graph[mid][to]) {
                        graph[from][to] = true;
                    }
                }
            }
        }

        // 질의 처리
        int s = Integer.parseInt(br.readLine());
        for (int i = 0; i < s; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);

            if (graph[a][b]) {
                answer.append("-1\n"); // a가 b보다 먼저
            } else if (graph[b][a]) {
                answer.append("1\n"); // b가 a보다 먼저
            } else {
                answer.append("0\n"); // 관계 없음
            }
        }

        System.out.print(answer);
    }
}
