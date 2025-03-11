import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon15649 {

    public static boolean[] visited;
    public static int[] memory;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        memory = new int[M];

        solution(N, M, 0);
    }

    public static void solution(int N, int M, int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                System.out.print(memory[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                memory[depth] = i;
                solution(N, M, depth + 1);
                visited[i] = false;
            }
        }
    }
}
